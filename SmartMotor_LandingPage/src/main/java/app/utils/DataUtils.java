package vn.viettel.app.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.google.common.collect.MapDifference;
import com.google.common.collect.Maps;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.web.multipart.MultipartFile;
import vn.viettel.app.config.exception.ValidateException;

import javax.servlet.http.HttpServletRequest;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.Clob;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.Normalizer;
import java.text.NumberFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class DataUtils {

  private static final Logger log = LoggerFactory.getLogger(DataUtils.class);

  private DataUtils() {
  }

  public static final String KHONG = "không";
  public static final String MOT = "một";
  public static final String HAI = "hai";
  public static final String BA = "ba";
  public static final String BON = "bốn";
  public static final String NAM = "năm";
  public static final String SAU = "sáu";
  public static final String BAY = "bảy";
  public static final String TAM = "tám";
  public static final String CHIN = "chín";
  public static final String LAM = "lăm";
  public static final String LE = "linh";
  public static final String MUOI = "mươi";
  public static final String MUOIF = "mười";
  public static final String MOTS = "mốt";
  public static final String TRAM = "trăm";
  public static final String NGHIN = "nghìn";
  public static final String TRIEU = "triệu";
  public static final String TY = "tỷ";
  public static final String[] number = {KHONG, MOT, HAI, BA, BON, NAM, SAU, BAY, TAM, CHIN};
  public static final String MONEY_FORMAT_DEFAULT = "###,###.###";
  public static final String MONEY_FORMAT_WITH_UNIT = MONEY_FORMAT_DEFAULT + " VND";

//  public static final String REGEX_PHONE_VN = "(03|05|07|08|09|01[2|6|8|9])+([0-9]{8})\\b";
  public static final String REGEX_PHONE_VN = "(03|05|07|08|09|01[2|6|8|9])+([0-9]{8})\\b";
  // +84 otp phone
  public static final String REGEX_TBL_PHONE_VN = "^0[0-9]{10}$";
  public static final String REGEX_PHONE_10 = "^[0-9]{10}$";
  public static final String REGEX_PHONE = "^[0-9]{9,12}$";
  public static final String REGEX_EMAIL = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";
  public static final String PLUS_84 = "84";


  public static char toLower(char var0) {
    return var0 |= ' ';
  }

  public static char toUpper(char var0) {
    return var0 &= ' ';
  }

  public static void checkNull(Object data, String message) throws ValidateException {
    if(data == null) {
      throw new ValidateException(message);
    }
  }

  public static void checkStringLength(String data, int length, String message) throws ValidateException {
    if(data != null && data.length() > length) {
      throw new ValidateException(message);
    }
  }

  public static void checkIsPhone10(String data, String message) throws ValidateException {
    if(!isPhone10(data)) {
      throw new ValidateException(message);
    }
  }

  public static void checkIsPhone(String data, String message) throws ValidateException {
    if(!isPhone(data)) {
      throw new ValidateException(message);
    }
  }

  public static void checkStringEmpty(String data, String message) throws ValidateException {
    if(data == null || "".equals(data.trim())) {
      throw new ValidateException(message);
    }
  }

  public static String getClobString(Clob clob) {
    if (!DataUtils.isNull(clob)) {
      StringBuilder data = new StringBuilder();
      try {
        BufferedReader stringReader = new BufferedReader(clob.getCharacterStream());
        String singleLine = null;
        while ((singleLine = stringReader.readLine()) != null) {
          data.append(singleLine);
        }
      } catch (IOException | SQLException e) {
        e.printStackTrace();
      }
      return data.toString();
    } else {
      return "";
    }
  }

  public static Long safeToLong(Object obj, Long defaultValue) {
    if (obj == null) {
      return defaultValue;
    }
    if (obj instanceof Long) {
      return (Long) obj;
    }
    try {
      return Long.valueOf(obj.toString());
    } catch (NumberFormatException ex) {
      return defaultValue;
    }
  }

  public static Long safeToLong(Object obj) {
    return safeToLong(obj, null);
  }

  public static Integer safeToInteger(Object obj, Integer defaultValue) {
    if (obj == null) {
      return defaultValue;
    }
    if (obj instanceof Integer) {
      return (Integer) obj;
    }
    try {
      return Integer.valueOf(obj.toString());
    } catch (Exception ex) {
      return defaultValue;
    }
  }

  public static BigInteger safeToBigInteger(Object obj, BigInteger defaultValue) {
    if (obj == null) {
      return defaultValue;
    }
    if (obj instanceof BigInteger) {
      return (BigInteger) obj;
    }
    try {
      return BigInteger.valueOf(Long.parseLong(obj.toString()));
    } catch (Exception ex) {
      return defaultValue;
    }
  }

  public static BigInteger safeToBigInteger(Object obj) {
    return safeToBigInteger(obj, BigInteger.ZERO);
  }


//  public static String deAccent(String str) {
//    String nfdNormalizedString = Normalizer.normalize(str, Normalizer.Form.NFD);
//    Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
//    return pattern
//        .matcher(nfdNormalizedString)
//        .replaceAll("")
//        .replaceAll("\\s+", "");
//  }

  public static String randomName(String name) {
    return String.format("%s%s_%s", RandomStringUtils.randomAlphabetic(8), System.currentTimeMillis(), name);
  }

  public static Integer safeToInteger(Object obj) {
    return safeToInteger(obj, null);
  }

  public static Float safeToFloat(Object obj, Float defaultValue) {
    if (obj instanceof Float) {
      return (Float) obj;
    }
    try {
      return Float.valueOf(obj.toString());
    } catch (NumberFormatException ex) {
      return defaultValue;
    }
  }

  public static Float safeToFloat(Object obj) {
    return safeToFloat(obj, null);
  }

  public static Double safeToDouble(Object obj, Double defaultValue) {
    if (obj instanceof Double) {
      return (Double) obj;
    }
    try {
      return Double.valueOf(obj.toString());
    } catch (NumberFormatException ex) {
      return defaultValue;
    }
  }

  public static Double safeToDouble(Object obj) {
    return safeToDouble(obj, null);
  }

  public static String safeToString(Object obj, String defaultValue) {
    if (obj == null) {
      return defaultValue;
    }
    if (obj instanceof String) {
      return (String) obj;
    }
    if (obj != null) {
      return obj.toString();
    }
    return defaultValue;
  }

  public static String safeToString(Object obj) {
    return safeToString(obj, null);
  }

  public static boolean isNull(Object obj) {
    return obj == null;
  }


  public static boolean isNullOrEmpty(String str) {
    if (str == null || "".equalsIgnoreCase(str)) {
      return true;
    }
    return false;
  }

  public static boolean isNullOrEmpty(Collection collection) {
    if (collection == null || collection.isEmpty()) {
      return true;
    }
    return false;
  }

  public static boolean isBlank(String str) {
    if (str == null) {
      return true;
    }
    return "".equals(str.trim());
  }

  public static boolean isEmpty(String str) {
    if (str == null) {
      return true;
    }
    return "".equals(str);
  }

  public static String toUpperEveryChar(String str) {
    if (isBlank(str)) {
      return null;
    }
    StringBuilder result = new StringBuilder();
    String[] chars = str.trim().split("\\s+");
    for (String ele : chars) {
      result.append((ele.charAt(0) + "").toUpperCase()).append(ele.substring(1).toLowerCase()).append(" ");
    }
    return result.toString().trim();
  }

  public static String toUpperFirstChar(String str) {
    if (isBlank(str)) {
      return null;
    }
    StringBuilder result = new StringBuilder();
    String[] chars = str.trim().split("\\s+");
    String firstChar = chars[0];
    result.append((firstChar.charAt(0) + "").toUpperCase()).append(firstChar.substring(1).toLowerCase()).append(" ");
    for (int i = 1; i < chars.length; i++) {
      result.append((chars[i].charAt(0) + "").toLowerCase()).append(chars[i].substring(1).toLowerCase()).append(" ");
    }
    return result.toString().trim();
  }

  public static String convertNumberToSpell(String number) {
    return convertNumberToSpell(number, "");
  }

  public static String convertNumberToSpell(String number, String suffix) {
    StringBuilder money = new StringBuilder();
    List<String> result = readNum(number);
    for (String s : result) {
      money.append(s).append(" ");
    }
    money.append(suffix);
    if (money.length() != 0) {
      return toUpperEveryChar(money.toString());
    }
    return null;
  }

  public static List<String> readNum(String a) {
    List<String> result = new ArrayList<>();
    List<String> listNum = split(a, 3);
    while (!listNum.isEmpty()) {
      switch (listNum.size() % 3) {
        case 1:
          result.addAll(readThreeNum(listNum.get(0)));
          break;
        case 2:
          List<String> thousands = readThreeNum(listNum.get(0));
          if (!thousands.isEmpty()) {
            result.addAll(thousands);
            result.add(NGHIN);
          }
          break;
        case 0:
          List<String> million = readThreeNum(listNum.get(0));
          if (!million.isEmpty()) {
            result.addAll(million);
            result.add(TRIEU);
          }
          break;
        default:
          break;
      }
      if (listNum.size() == (listNum.size() / 3) * 3 + 1 && listNum.size() != 1) {
        result.add(TY);
      }
      listNum.remove(0);
    }
    return result;
  }

  public static List<String> readThreeNum(String a) {
    List<String> result = new ArrayList<>();
    int num = -1;
    try {
      num = Integer.parseInt(a);
    } catch (Exception ex) {
    }
    if (num == 0) {
      return result;
    }
    int hundred = -1;
    try {
      hundred = Integer.parseInt(a.substring(0, 1));
    } catch (Exception ex) {
    }
    int ty = -1;
    try {
      ty = Integer.parseInt(a.substring(1, 2));
    } catch (Exception ex) {
    }
    int unit = -1;
    try {
      unit = Integer.parseInt(a.substring(2, 3));
    } catch (Exception ex) {
    }
    if (hundred != -1) {
      result.add(number[hundred]);
      result.add(TRAM);
    }
    switch (ty) {
      case -1:
        break;
      case 1:
        result.add(MUOIF);
        break;
      case 0:
        if (unit != 0) {
          result.add(LE);
        }
        break;
      default:
        result.add(number[ty]);
        result.add(MUOI);
        break;
    }

    switch (unit) {
      case -1:
        break;
      case 1:
        if ((ty != 0) && (ty != 1) && (ty != -1)) {
          result.add(MOTS);
        } else {
          result.add(number[unit]);
        }
        break;
      case 5:
        if ((ty != 0) && (ty != -1)) {
          result.add(LAM);
        } else {
          result.add(number[unit]);
        }
        break;
      case 0:
        if (result.isEmpty()) {
          result.add(number[unit]);
        }
        break;
      default:
        result.add(number[unit]);
        break;
    }
    return result;
  }

  public static List<String> split(String str, int chunkSize) {
    int du = str.length() % chunkSize;
    if (du != 0) {
      for (int i = 0; i < (chunkSize - du); i++) {
        str = "#" + str;
      }
    }
    return splitStringEvery(str, chunkSize);
  }

  public static List<String> splitStringEvery(String s, int interval) {
    List<String> arrList = new ArrayList<>();
    int arrayLength = (int) Math.ceil(s.length() / (double) interval);
    String[] result = new String[arrayLength];
    int j = 0;
    int lastIndex = result.length - 1;
    for (int i = 0; i < lastIndex; i++) {
      result[i] = s.substring(j, j + interval);
      j += interval;
    }
    result[lastIndex] = s.substring(j);
    //result = s.split("(?<=\\G.{" + interval + "})");
    arrList.addAll(Arrays.asList(result));
    return arrList;
  }

  public static boolean isPhoneNumber(String str) {
    if (StringUtils.isBlank(str)) {
      return false;
    }
    return str.matches(REGEX_PHONE_VN);
  }

  public static boolean isPhone10(String str) {
    if (StringUtils.isBlank(str)) {
      return false;
    }
    return str.matches(REGEX_PHONE_10);
  }

  public static boolean isPhone(String str) {
    if (StringUtils.isBlank(str)) {
      return false;
    }
    return str.matches(REGEX_PHONE);
  }

  public static boolean isOtpPhoneNumber(String str) {
    if (StringUtils.isBlank(str)) {
      return false;
    }
    return str.matches(REGEX_TBL_PHONE_VN);
  }

  public static boolean isTime(String str, String format) {
    return true;
  }

  public static boolean isDdMmYyyyHhMmSs(String str) {
    return isTime(str, "dd/MM/yyyy HH:mm:ss");
  }

  public static boolean isEmail(String str) {
    if (isBlank(str)) {
      return false;
    }
    Matcher matcher = Pattern.compile(REGEX_EMAIL, Pattern.CASE_INSENSITIVE).matcher(str);
    return matcher.find();
  }

  public static <T> Optional<T> jsonToObj(String json, Class<T> clazz) {
    ObjectMapper mapper = new ObjectMapper();
    if (json == null || json.isEmpty()) {
      return Optional.empty();
    }
    try {
      return Optional.ofNullable(mapper.readValue(json, clazz));
    } catch (IOException exception) {
      log.error(exception.getMessage(), exception);
      return Optional.empty();
    }
  }

  public static <T> String objToJson(T t) {
    ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
    try {
      return ow.writeValueAsString(t);
    } catch (JsonProcessingException e) {
      log.error(e.getMessage(), e);
      return null;
    }
  }

  public static <T> T xmlToObj(String str) {
    return null;
  }

  public static <T> String objToXml(T t) {
    return null;
  }

  public static Date safeToDate(Object obj, Date defaultValue) {
    return null;
  }

  public static Date safeToDate(Object obj) {
    return safeToDate(obj, null);
  }

  public static LocalDate safeToLocalDate(Object obj, LocalDate defaultValue) {
    return null;
  }

  public static LocalDate safeToLocalDate(Object obj) {
    return safeToLocalDate(obj, null);
  }

  public static Date dateNow() {
    return new Date();
  }

  public static LocalDate localDateNow() {
    return LocalDate.now();
  }

  public static int getDayNow() {
    return LocalDate.now().getDayOfMonth();
  }

  public static int getMonthNow() {
    return LocalDate.now().getMonthValue();
  }

  public static int getYearNow() {
    return LocalDate.now().getYear();
  }

  public static Date toDate(LocalDate localDate) {
    return Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
  }

  public static Date toDate(LocalDateTime localDateTime) {
    return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
  }

  public static LocalDate toLocalDate(Date date) {
    return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
  }

  public static LocalDateTime toLocalDateTime(Date date) {
    return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDateTime();
  }

  public static java.sql.Date toUtilDate(Date date) {
    return new java.sql.Date(date.getTime());
  }

  public static Date toSqlDate(java.sql.Date date) {
    return new Date(date.getTime());
  }

  public static boolean isEmptyObj(Object obj) {
    return true;
  }

  public static String toNumberLocale(long var, String s, String s1) {
    NumberFormat vn = NumberFormat.getInstance(new Locale(s, s1));
    return vn.format(var);
  }

  public static String toNumberLocale(long var) {
    return toNumberLocale(var, "vi", "VN");
  }

  public static String toNumberLocale(String var, String s, String s1) {
    BigInteger var1 = new BigInteger(var);
    NumberFormat vn = NumberFormat.getInstance(new Locale(s, s1));
    return vn.format(var1);
  }

  public static String toNumberLocale(String var) {
    return toNumberLocale(var, "vi", "VN");
  }

  public static String getCurrencyInstance(String var, String s, String s1) {
    BigInteger var1 = new BigInteger(var);
    NumberFormat vn = NumberFormat.getCurrencyInstance(new Locale(s, s1));
    return vn.format(var1);
  }

  public static String getCurrencyInstance(String var) {
    return getCurrencyInstance(var, "vi", "VN");
  }

  public static <T> T clone(T source) {
    try {
      if (source == null) {
        return null;
      }
      T dto = (T) source.getClass().getConstructor().newInstance();
      BeanUtils.copyProperties(source, dto);
      return dto;
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      return null;
    }
  }

  public static <T> T cloneEntity(T entity) {
    return null;
  }

  public static BigDecimal sum(BigDecimal var1, BigDecimal var2) {
    return var1.add(var2);
  }

  public static BigDecimal sum(double var1, double var2) {
    return sum(toBigDecimal(var1), toBigDecimal(var2));
  }

  public static BigDecimal minus(BigDecimal var1, BigDecimal var2) {
    return var1.subtract(var2);
  }

  public static BigDecimal minus(double var1, double var2) {
    return minus(toBigDecimal(var1), toBigDecimal(var2));
  }

  public static BigDecimal mul(BigDecimal var1, BigDecimal var2) {
    return var1.multiply(var2);
  }

  public static BigDecimal mul(double var1, double var2) {
    return mul(toBigDecimal(var1), toBigDecimal(var2));
  }

  public static BigDecimal div(BigDecimal var1, BigDecimal var2) {
    return var1.divide(var2);
  }

  public static BigDecimal div(double var1, double var2) {
    return div(toBigDecimal(var1), toBigDecimal(var2));
  }

  public static int compare(BigDecimal var1, BigDecimal var2) {
    return var1.compareTo(var2);
  }

  public static int compare(Double var1, Double var2) {
    if (var1 == null || var2 == null) {
      return -1;
    }
    return compare(BigDecimal.valueOf(var1), BigDecimal.valueOf(var2));
  }

  public static int compare(Float var1, Float var2) {
    if (var1 == null || var2 == null) {
      return -1;
    }
    return compare(new BigDecimal(var1.toString()), new BigDecimal(var2.toString()));
  }

  public static int compare(Float var1, Double var2) {
    if (var1 == null || var2 == null) {
      return -1;
    }
    return compare(new BigDecimal(var1.toString()), new BigDecimal(var2.toString()));
  }

  public static int compare(Double var1, Float var2) {
    if (var1 == null || var2 == null) {
      return -1;
    }
    return compare(new BigDecimal(var1.toString()), new BigDecimal(var2.toString()));
  }

  public static BigDecimal toBigDecimal(double var1) {
    return BigDecimal.valueOf(var1);
  }

  public static BigDecimal trailingZeros(BigDecimal var1) {
    return var1.stripTrailingZeros();
  }

  public static String trailingZeros(String amount) {
    if (isBlank(amount)) {
      return "";
    }
    return trailingZeros(new BigDecimal(amount)).toPlainString();
  }

  public static String getFormatMoney(String money, String format, char decimalSeparator, char groupingSeparator) {
    try {
      money = trailingZeros(money);
      DecimalFormatSymbols symbols = new DecimalFormatSymbols();
      symbols.setDecimalSeparator(decimalSeparator);
      symbols.setGroupingSeparator(groupingSeparator);
      DecimalFormat decimalFormat = new DecimalFormat(format, symbols);
      BigDecimal bigDecimal = new BigDecimal(money);
      return decimalFormat.format(bigDecimal.doubleValue());
    } catch (NumberFormatException ex) {
      log.error("Error cast string to number method getFormatMoney");
    }
    return "";
  }

  public static String getFormatMoney(String money, String format) {
    return getFormatMoney(money, format, '.', ',');
  }

  public static String getFormatMoney(String money) {
    return getFormatMoney(money, MONEY_FORMAT_DEFAULT);
  }

  private static final int ROUND_DEFAULT = 3;

  public static BigDecimal round(BigDecimal var1, int i, RoundingMode mode) {
    return var1.setScale(i, mode);
  }

  public static BigDecimal roundCeil(BigDecimal var1, int i) {
    return round(var1, i, RoundingMode.CEILING);
  }

  public static BigDecimal roundCeil(BigDecimal var1) {
    return roundCeil(var1, ROUND_DEFAULT);
  }

  public static BigDecimal roundFloor(BigDecimal var1, int i) {
    return round(var1, i, RoundingMode.FLOOR);
  }

  public static BigDecimal roundFloor(BigDecimal var1) {
    return roundFloor(var1, ROUND_DEFAULT);
  }

  public static BigDecimal roundAuto(BigDecimal var1, int i) {
    return round(var1, i, RoundingMode.HALF_UP);
  }

  public static BigDecimal roundAuto(BigDecimal var1) {
    return roundAuto(var1, ROUND_DEFAULT);
  }

  public static boolean isFullZeroAlterDot(BigDecimal var1) {
    return compare(var1.doubleValue(), var1.toBigInteger().doubleValue()) == 0;
  }

  /**
   * @param var1 : input is BigDecimal
   * @return remove all zero alter dot 0.1200 -> 0.12, and alter remove all dot 012 -> 12
   */
  public static BigInteger removeDot(BigDecimal var1) {
    return trailingZeros(var1).unscaledValue();
  }

  /**
   * @param var1 : input is BigDecimal
   * @param i    : 10^i
   * @return value = var1 * 10^i;
   */
  public static BigDecimal mul10(BigDecimal var1, int i) {
    return var1.movePointRight(i);
  }

  /**
   * @param var1 : input is BigDecimal
   * @param i    : 10^i
   * @return value = var1 / 10^i;
   */
  public static BigDecimal div10(BigDecimal var1, int i) {
    return var1.movePointLeft(i);
  }

  public static final char KEY_ESCAPE = '!';

  /**
   * @param str : input is String
   * @return replace all input "_" -> "KEY_ESCAPE_" and "%" -> "KEY_ESCAPE%"
   */
  public static String likeSpecialToStr(String str) {
    str = str.replaceAll("_", KEY_ESCAPE + "_");
    str = str.replaceAll("%", KEY_ESCAPE + "%");
    return str;
  }

  /**
   * @param min : start range
   * @param max : end range
   * @return random value between [min;max]
   * @throws NoSuchAlgorithmException : random value = -1 if exceptions
   */
  public static int getRandom(int min, int max) {
    Random random;
    try {
      random = SecureRandom.getInstanceStrong();
      return random.nextInt(max - min + 1) + min;
    } catch (NoSuchAlgorithmException e) {
      log.error("Error getRandom {}", e.getMessage());
    }
    return -1;
  }

  /**
   * @param var1 : Array1 Object not primitive
   * @param var2 : Array2 Object not primitive
   * @return concat arr1 and arr2
   */
  public static <T> T[] concatArray(T[] var1, T[] var2) {
    return ArrayUtils.addAll(var1, var2);
  }


  public static boolean isXml(String xml) {
    try {
      DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
      dbf.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
      dbf.setFeature("http://xml.org/sax/features/external-general-entities", false);
      dbf.setFeature("http://xml.org/sax/features/external-parameter-entities", false);
      dbf.setXIncludeAware(false);
      dbf.setExpandEntityReferences(false);
      dbf.setValidating(false);
      DocumentBuilder dBuilder = dbf.newDocumentBuilder();
      ByteArrayInputStream input = new ByteArrayInputStream(xml.getBytes(StandardCharsets.UTF_8));
      return dBuilder.parse(input) != null;
    } catch (Exception ex) {
      return false;
    }
  }

  public static String getIpAddress(HttpServletRequest request) {
    if (request != null) {
      String remoteAddr = request.getHeader("X-FORWARDED-FOR");
      if (remoteAddr == null || StringUtils.EMPTY.equals(remoteAddr)) {
        remoteAddr = request.getRemoteAddr();
      }
      return remoteAddr;
    }
    return StringUtils.EMPTY;
  }

  public static boolean isNumberStr(String str) {
    return str.matches("\\d+");
  }

  public static <T> List<List<T>> subList(List<T> totalList, int limit) {
    List<List<T>> result = new ArrayList<>();
    int size = totalList.size();
    int soPhan = (int) Math.ceil((double) size / limit);
    for (int i = 0; i < soPhan; i++) {
      int start = i * limit;
      int end = i * limit + limit;
      if (end >= size) {
        end = size;
      }
      result.add(totalList.subList(start, end));
    }
    return result;
  }

  public static String getClientIp(HttpServletRequest request) {
    String ipAddress = request.getHeader("X-Forwarded-For");

    if (StringUtils.isEmpty(ipAddress) || "unknown".equalsIgnoreCase(ipAddress)) {
      ipAddress = request.getHeader("Proxy-Client-IP");
    }

    if (StringUtils.isEmpty(ipAddress) || "unknown".equalsIgnoreCase(ipAddress)) {
      ipAddress = request.getHeader("WL-Proxy-Client-IP");
    }

    if (StringUtils.isEmpty(ipAddress) || "unknown".equalsIgnoreCase(ipAddress)) {
      ipAddress = request.getRemoteAddr();
      if (Constants.LOCAL_IP.LOCALHOST_IPV4.equals(ipAddress) || Constants.LOCAL_IP.LOCALHOST_IPV6.equals(ipAddress)) {
        try {
          InetAddress inetAddress = InetAddress.getLocalHost();
          ipAddress = inetAddress.getHostAddress();
        } catch (UnknownHostException e) {
          log.error(e.getMessage(), e);
        }
      }
    }
    if (!StringUtils.isEmpty(ipAddress)
        && ipAddress.length() > 15
        && ipAddress.indexOf(",") > 0) {
      ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
    }

    return ipAddress;
  }

  public static String toLikeAndLowerCaseString(String content) {
    return "%" + DataUtils.escapeSql(content.toLowerCase().trim()) + "%";
  }

  public static String escapeSql(String input) {
    String result = input.trim().replace("/", "//").replace("_", "/_").replace("%", "/%");
    return result;
  }

  public static Boolean validateFileType(MultipartFile file, String fileType) {
    String fileName = file.getOriginalFilename();
    String extension = "";
    int i = fileName.lastIndexOf('.');
    if (i >= 0) {
      extension = "." + fileName.substring(i + 1);
    }

    return  extension.equalsIgnoreCase(fileType);
  }

  public static String changePhoneToPlus84(String phoneNumber) throws ValidateException {

    if(isNullOrEmpty(phoneNumber)) return null;

    if(phoneNumber.startsWith("84"))
      return phoneNumber;

    if (!DataUtils.isNullOrEmpty(phoneNumber) && phoneNumber.startsWith("0")){
    DataUtils.checkIsPhone(phoneNumber, phoneNumber + ": Số thuê bao không đúng");
      List<String> changePhone = Arrays.stream(phoneNumber.split("")).collect(Collectors.toList());
      changePhone.remove(0);
      return PLUS_84 + String.join("",changePhone);
    }else {
      return PLUS_84 + phoneNumber;
    }
  }

  public static String changePhoneCut084(String phoneNumber) {
    if(phoneNumber == null) return "";

//    String phone = phoneNumber;

    if(phoneNumber.startsWith("0") && phoneNumber.length() == 10) {
      return  phoneNumber.substring(1);
    }

    if(phoneNumber.startsWith("84") && phoneNumber.length() == 11) {
      return phoneNumber.substring(2);
    }

    // case +840....
//    if(phone.startsWith("0")) {
//      phone = phone.substring(1);
//    }
    return phoneNumber;
  }

  public static String reverse(String str) {
    if(isNullOrEmpty(str)) return null;
    return new StringBuilder(str).reverse().toString();
  }


  public static String toCouponCode(String str) {
    str = str.trim();
    str = str.toLowerCase();
    str = str.replaceAll(" +", " ");
    str = str.replaceAll("\\s", "");
    str = str.replace("à|á|ạ|ả|ã|â|ầ|ấ|ậ|ẩ|ẫ|ă|ằ|ắ|ặ|ẳ|ẵ", "a");
    str = str.replace("è|é|ẹ|ẻ|ẽ|ê|ề|ế|ệ|ể|ễ", "e");
    str = str.replace("ì|í|ị|ỉ|ĩ", "i");
    str = str.replace("ò|ó|ọ|ỏ|õ|ô|ồ|ố|ộ|ổ|ỗ|ơ|ờ|ớ|ợ|ở|ỡ", "o");
    str = str.replace("ù|ú|ụ|ủ|ũ|ư|ừ|ứ|ự|ử|ữ", "u");
    str = str.replace("ỳ|ý|ỵ|ỷ|ỹ", "y");
    str = str.replace("đ", "d");
    String nfdNormalizedString = Normalizer.normalize(str, Normalizer.Form.NFD);
    Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
    str = pattern.matcher(nfdNormalizedString).replaceAll("");
    return str;
  }

  public static String deAccent(String str) {
    str = str.trim();
//    str = str.toLowerCase();
    str = str.replaceAll(" +", " ");
//    str = str.replaceAll("\\s", "");
    str = str.replace("à|á|ạ|ả|ã|â|ầ|ấ|ậ|ẩ|ẫ|ă|ằ|ắ|ặ|ẳ|ẵ", "a");
    str = str.replace("À|Á|Ạ|Ả|Ã|Â|Ầ|Ấ|Ậ|Ẩ|Ẫ|Ă|Ằ|Ắ|Ặ|Ẳ|Ẵ", "A");
    str = str.replace("è|é|ẹ|ẻ|ẽ|ê|ề|ế|ệ|ể|ễ", "e");
    str = str.replace("È|É|Ẹ|Ẻ|Ẽ|Ê|Ề|Ế|Ệ|Ể|Ễ", "E");
    str = str.replace("ì|í|ị|ỉ|ĩ", "i");
    str = str.replace("Ì|Í|Ị|Ỉ|Ĩ", "I");
    str = str.replace("ò|ó|ọ|ỏ|õ|ô|ồ|ố|ộ|ổ|ỗ|ơ|ờ|ớ|ợ|ở|ỡ", "o");
    str = str.replace("Ò|Ó|Ọ|Ỏ|Õ|Ô|Ồ|Ố|Ộ|Ổ|Ỗ|Ơ|Ờ|Ớ|Ợ|Ở|Ỡ", "O");
    str = str.replace("ù|ú|ụ|ủ|ũ|ư|ừ|ứ|ự|ử|ữ", "u");
    str = str.replace("Ù|Ú|Ụ|Ủ|Ũ|Ư|Ừ|Ứ|Ự|Ử|Ữ", "U");
    str = str.replace("ỳ|ý|ỵ|ỷ|ỹ", "y");
    str = str.replace("Ỳ|Ý|Ỵ|Ỷ|Ỹ", "Y");
    str = str.replace("đ", "d");
    str = str.replace("Đ", "D");
    String nfdNormalizedString = Normalizer.normalize(str, Normalizer.Form.NFD);
    Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
    str = pattern.matcher(nfdNormalizedString).replaceAll("");
    return str;
  }

  public static String subKeyPhoenix(String key) {
    try {
      return key.substring(0, 11);
    } catch (Exception ex) {
      log.error(ex.getMessage(), ex);
    }
    return null;
  }

  public static String normalString(Object obj) {
    return obj == null ? "" : String.valueOf(obj);
  }

  public static String buildKeyPhoenix(String hash, Date startTime) {
    return reverse(hash) + DateUtil.toMillisecond(startTime);
  }

  public static boolean isJSONValid(String test) {
    try {
      if(isNullOrEmpty(test)){
        return false;
      }

      final ObjectMapper mapper = new ObjectMapper();
      Map<String, String> map = mapper.readValue(test, Map.class);
    } catch (JsonProcessingException ex) {
      return false;
    }
    return true;
  }

  public static <T> T cloneObject(T source) {
    if (source == null)
      return null;

    Class clzz = source.getClass();
    T target = null;
    try {
      target = (T) clzz.newInstance();
    } catch (InstantiationException | IllegalAccessException e) {
      log.error(e.getMessage(), e);
    }

    BeanUtils.copyProperties(source, target);
    return target;
  }

  public static MapDifference<String, Object> jsonDifference(String leftJson,String rightJson){
    if(isNullOrEmpty(leftJson) || isNullOrEmpty(rightJson)){
      return null;
    }
    Gson gson = new Gson();
    Type type = new TypeToken<Map<String, Object>>(){}.getType();

    Map<String, Object> leftMap = gson.fromJson(leftJson, type);
    Map<String, Object> rightMap = gson.fromJson(rightJson, type);

    Map<String, Object> leftFlatMap = FlatMapUtil.flatten(leftMap);
    Map<String, Object> rightFlatMap = FlatMapUtil.flatten(rightMap);

    MapDifference<String, Object> difference = Maps.difference(leftFlatMap, rightFlatMap);

    System.out.println("Entries only on the left\n--------------------------");
    difference.entriesOnlyOnLeft()
        .forEach((key, value) -> System.out.println(key.substring(1) + ": " + value));

    System.out.println("\n\nEntries only on the right\n--------------------------");
    difference.entriesOnlyOnRight()
        .forEach((key, value) -> System.out.println(key.substring(1) + ": " + value));

    System.out.println("\n\nEntries differing\n--------------------------");
    difference.entriesDiffering()
        .forEach((key, value) -> System.out.println(key + ": " + value));
    return difference;
  }

  public static String parseToString(Object obj) {
    if (isNull(obj)) {
      return null;
    }
    return String.valueOf(obj);
  }
  public static Long parseToLong(Object obj) {
    if (isNull(obj)) {
      return null;
    }
    return Long.valueOf(obj.toString());
  }

  public static String subString(String input, int lengh) {
    if (input == null) {
      return null;
    }
    if (lengh <= 0) {
      return input;
    }

    int strlengh = input.length();
    if (strlengh > lengh) {
      return input.substring(0, lengh);
    }
    return input;
  }

  public static boolean nullOrZero(Long value) {
    return (value == null || value.equals(0L));
  }

  public static boolean equalsObject(Object obj1, Object obj2) {
    if (obj1 == null || obj2 == null) return false;
    return EqualsBuilder.reflectionEquals(obj1, obj2);
  }

  public static String objectToJson(Object data, String defaultValue) {
    if (isNull(data)) {
      return defaultValue;
    }
    try {
      ObjectMapper mapper = new ObjectMapper();
      mapper.registerModule(new JavaTimeModule());
      mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
      return mapper.writeValueAsString(data);
    } catch (Exception ex) {
      log.warn(ex.getMessage(), ex);
      return "";
    }
  }

  public static String objectToJson(Object data) {
    return objectToJson(data, "");
  }

  public static String trimZero(String trim) {
    if(trim == null)
      return "";
    StringBuffer sb = new StringBuffer();
    boolean isStart = true;
    for(int i = 0; i < trim.length(); i++) {
      if(trim.charAt(i) == '0' && isStart) {
        continue;
      }
      isStart = false;
      sb.append(trim.charAt(i));
    }
    return sb.toString();
  }

  public static <T> T safeEval(Supplier<T> supplier) {
    try {
      return supplier.get();
    } catch (NullPointerException e) {
      return null;
    }
  }
  public static <T> boolean nonNullSafeEval(Supplier<T> supplier) {
    try {
      return supplier.get() != null;
    } catch (NullPointerException e) {
      return false;
    }
  }

  public static <T> Predicate<T> distinctByKey(
      Function<? super T, ?> keyExtractor) {

    Map<Object, Boolean> seen = new ConcurrentHashMap<>();
    return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
  }
}

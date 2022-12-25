/*******************************************************************************
 * (C) Copyright Global CyberSoft (GCS) 2019. All rights reserved. Proprietary
 * and confidential.
 ******************************************************************************/
package com.example.demo.comon;

import java.security.SecureRandom;
import java.text.Normalizer;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.lang3.RandomStringUtils;
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.security.crypto.bcrypt.BCrypt;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * The Class StringHelper.
 *
 * @author <a href="mailto:huykq.hq@hitachiconsulting.com">huyquach</a>
 */
@Slf4j
public final class StringHelper {

  public static final String EMPTY = "";

  /**
   * The Constant UNICODE_EXTRACT_PATTERN.
   */
  private static final Pattern UNICODE_EXTRACT_PATTERN = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");

  /**
   * The Constant SPACE_EXTRACT_PATTERN.
   */
  private static final Pattern SPACE_EXTRACT_PATTERN = Pattern.compile(" ");

  /**
   * The Constant DD_EXTRACT_PATTERN.
   */
  private static final Pattern DD_EXTRACT_PATTERN = Pattern.compile("Đ");

  /**
   * The Constant DD_LOWER_EXTRACT_PATTERN.
   */
  private static final Pattern DD_LOWER_EXTRACT_PATTERN = Pattern.compile("đ");

  /**
   * The Constant PHONE_NUMBER_PATTERN.
   */
  private static final Pattern PHONE_NUMBER_PATTERN = Pattern.compile("^0[0-9]{9,19}$");

  private static final Pattern BANK_ACCOUNT_NUMBER = Pattern.compile("^[0-9]{1,20}$");

  private static final Pattern EMAIL = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");

  private static final Pattern FAX = Pattern.compile("^[0-9]{1,20}$");

  private static final Pattern FILE_NAME = Pattern.compile("^[A-Za-z0-9_.]{1,255}$");

  private static final Pattern CERTIFICATE_NO_PATTERN = Pattern.compile("[a-z0-9A-ZÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòó" +
      "ôõùúăđĩũơƯĂẠ" +
      "ẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂưăạảấầẩẫậắằẳẵặẹẻẽềềểỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ]{2,100}$");

  /**
   * The Constant metaCharacters.
   */
  private static final String[] METACHARACTERS = {"\\", "^", "$", "{", "}", "[", "]"
      , "(", ")", ".", "*", "+", "?", "|", "<", ">", "-", "&", "%"};

  /**
   * SecureRandom is preferred to Random.
   */
  private static SecureRandom random = new SecureRandom();

  /**
   * The Constant passwordEncoder.
   */
//  private static final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

  /**
   * The Constant SALT_LENGTH.
   */
  private static final Integer SALT_LENGTH = 12;

  /**
   * The Constant CODE_65279.
   */
  private static final Integer CODE_65279 = 65279;

  /**
   * The Constant CODE_8234.
   */
  private static final Integer CODE_8234 = 8234;

  /**
   * The Constant UPPER_CASE_LETTERS_COUNT.
   */
  private static final int UPPER_CASE_LETTERS_COUNT = 2;

  /**
   * The Constant UPPER_CASE_LETTERS_START.
   */
  private static final int UPPER_CASE_LETTERS_START = 65;

  /**
   * The Constant SPECIAL_CHAR_START.
   */
  private static final int SPECIAL_CHAR_START = 33;

  /**
   * The Constant SPECIAL_CHAR_END.
   */
  private static final int SPECIAL_CHAR_END = 47;

  /**
   * The Constant CASE_LETTERS_START.
   */
  private static final int CASE_LETTERS_START = 97;

  /**
   * The Constant CASE_LETTERS_END.
   */
  private static final int CASE_LETTERS_END = 122;

  /**
   * The Constant UPPER_CASE_LETTERS_END.
   */
  private static final int UPPER_CASE_LETTERS_END = 90;

  /**
   * Instantiates a new string helper.
   */
  private StringHelper() {

  }

  /**
   * Checks if is null or empty.
   *
   * @param value the value
   * @return true, if is null or empty
   */
  /*
   * Checking string is null or empty
   */
  public static boolean isNullOrEmpty(String value) {
    return !(value != null && !value.isEmpty());
  }

  /**
   * Checks if is not null or not empty.
   *
   * @param value the value
   * @return true, if is not null or not empty
   */
  /*
   * Checking string is not null or not empty
   */
  public static boolean isNotNullOrNotEmpty(String value) {
    return !isNullOrEmpty(value);
  }

  /**
   * Equals.
   *
   * @param str1 the str 1
   * @param str2 the str 2
   * @return true, if successful
   */
  public static boolean equals(CharSequence str1, CharSequence str2) {
    return equals(str1, str2, false);
  }

  /**
   * Equals.
   *
   * @param str1       the str 1
   * @param str2       the str 2
   * @param ignoreCase the ignore case
   * @return true, if successful
   */
  public static boolean equals(CharSequence str1, CharSequence str2, boolean ignoreCase) {
    if (null == str1) {
      return str2 == null;
    } else if (null == str2) {
      return false;
    } else {
      if (ignoreCase) {
        return str1.toString().equalsIgnoreCase(str2.toString());
      } else {
        return str1.equals(str2);
      }
    }
  }

  /**
   * Checks if is blank char.
   *
   * @param c the c
   * @return true, if is blank char
   */
  public static boolean isBlankChar(char c) {
    return isBlankChar((int) c);
  }

  /**
   * Checks if is blank char.
   *
   * @param c the c
   * @return true, if is blank char
   */
  public static boolean isBlankChar(int c) {
    return Character.isWhitespace(c) || Character.isSpaceChar(c) || c == CODE_65279 || c == CODE_8234;
  }

  /**
   * Checks if is not blank.
   *
   * @param str the str
   * @return true, if is not blank
   */
  public static boolean isNotBlank(CharSequence str) {
    return !isBlank(str);
  }

  /**
   * Checks if is blank.
   *
   * @param str the str
   * @return true, if is blank
   */
  public static boolean isBlank(CharSequence str) {
    int length;

    if (str != null && (length = str.length()) != 0) {
      for (int i = 0; i < length; ++i) {
        if (!isBlankChar(str.charAt(i))) {
          return false;
        }
      }
    }

    return true;
  }

  public static String getOnlyStrings(String s) {
    Pattern pattern = Pattern.compile("[^a-z A-Z 0-9 _ \\-]");
    Matcher matcher = pattern.matcher(s);
    String number = matcher.replaceAll("");
    return number;
  }

  /**
   * Admin username extract.
   *
   * @param enterprise the enterprise
   * @return the string
   */
//  public static String adminUsernameExtract(String enterprise) {
//    if (StringUtils.isEmpty(enterprise.trim())) {
//      return "";
//    }
//    enterprise = unicodeExtract(enterprise);
//
//    String[] words = enterprise.trim().split(" ");
//    StringBuilder result = new StringBuilder();
//
//    for (int i = 0; i < words.length; i++) {
//      if (!StringUtils.isEmpty(words[i].trim())) {
//        if (i == words.length - 1) {
//          result = new StringBuilder(words[i].trim()).append(result);
//          break;
//        } else {
//          result.append(words[i].trim().charAt(0));
//        }
//      }
//    }
//    return getOnlyStrings(result.toString());
//  }

  /**
   * Unicode extract.
   *
   * @param str the str
   * @return the string
   */
  public static String unicodeExtract(String str) {
    String temp = Normalizer.normalize(str, Normalizer.Form.NFD);
    String unicodeExtract = UNICODE_EXTRACT_PATTERN.matcher(temp).replaceAll("");
    //String spaceExtract = SPACE_EXTRACT_PATTERN.matcher(unicodeExtract).replaceAll("-");
    String ddExtract = DD_EXTRACT_PATTERN.matcher(unicodeExtract).replaceAll("d");
    String ddLower = DD_LOWER_EXTRACT_PATTERN.matcher(ddExtract).replaceAll("d");
    String preResult = ddLower.toLowerCase(Locale.ENGLISH);
    return getOnlyStrings(preResult);
  }

  /**
   * Unicode extract.
   *
   * @param str the str
   * @return the string
   */
  public static String removeUnicode(String str) {
    String temp = Normalizer.normalize(str, Normalizer.Form.NFD);
    String unicodeExtract = UNICODE_EXTRACT_PATTERN.matcher(temp).replaceAll("");
    String ddExtract = DD_EXTRACT_PATTERN.matcher(unicodeExtract).replaceAll("D");
    String ddLower = DD_LOWER_EXTRACT_PATTERN.matcher(ddExtract).replaceAll("d");
    return ddLower;
  }

  /**
   * Random alpha numeric.
   *
   * @param count the count
   * @return the string
   */
  public static String randomAlphaNumeric(int count) {
    int length = count;
    String symbol = "-/.^&*_!@%=+>)";
    String capLetter = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    String smallLetter = "abcdefghijklmnopqrstuvwxyz";
    String numbers = "0123456789";

    String finalString = capLetter + smallLetter + numbers + symbol;
    char[] password = new char[length];

    for (int i = 0; i < length; i++) {
      password[i] = finalString.charAt(random.nextInt(finalString.length()));
    }

    return new String(password);
  }

  /**
   * Generate salt.
   *
   * @param prefix the prefix
   * @return the string
   */
//  public static String generateSalt(String prefix) {
//    StringBuilder stringBuilder = new StringBuilder(prefix).append(DateHelper.now());
//    return passwordEncoder.encode(stringBuilder.toString());
//  }

  /**
   * Hash.
   *
   * @param pwd  the pwd
   * @param salt the salt
   * @return the string
   */
//  public static String hash(String pwd, String salt) {
//    return passwordEncoder.encode(pwd + salt);
//  }

  /**
   * Compare hash.
   *
   * @param hashPwd the hash pwd
   * @param pwd     the pwd
   * @param salt    the salt
   * @return true, if successful
   */
//  public boolean compareHash(String hashPwd, String pwd, String salt) {
//    return hashPwd.equals(hash(pwd, salt));
//  }

  /**
   * Generate common lang password.
   *
   * @return the string
   */
//  public static String generateCommonLangPassword() {
//    int default_length = 8;
//    return PasswordUtils.getPassword(default_length);
//  }

  /**
   * Generate common lang password no specical charecter
   *
   * @return the string
   */
//  public static String generateCommonLangPasswordNoCharecter() {
//    int default_length = 8;
//    return PasswordUtils.getPasswordNoSpecialChar(default_length);
//  }

  /**
   * Generate hash.
   *
   * @param input the input
   * @return the string
   */
//  public static String generateHash(String input) {
//    return BCrypt.hashpw(input, BCrypt.gensalt(SALT_LENGTH));
//  }

  /**
   * Check pwd.
   *
   * @param input          the input
   * @param passwordHashed the password hashed
   * @return true, if successful
   */
//  public static boolean checkPwd(String input, String passwordHashed) {
//    return BCrypt.checkpw(input, passwordHashed);
//  }

  /**
   * Phone replace.
   *
   * @param value the value
   * @return the string
   */
  public String phoneReplace(String value) {
    if (isNullOrEmpty(value)) {
      return value;
    }

    return value;
  }

  /**
   * Checks if is valid phone number.
   *
   * @param phone the phone
   * @return true, if is valid phone number
   */
  public static boolean isValidPhoneNumber(String phone) {
    Matcher m = PHONE_NUMBER_PATTERN.matcher(phone);
    return (m.find() && m.group().equals(phone));
  }

  public static boolean isValidBankAccountNumber(String bankAccountNumber) {
    Matcher m = BANK_ACCOUNT_NUMBER.matcher(bankAccountNumber);
    return (m.find() && m.group().equals(bankAccountNumber));
  }

  public static boolean isValidEmail(String email) {
    Matcher m = EMAIL.matcher(email);
    return (m.find() && m.group().equals(email));
  }

  public static boolean isValidFax(String fax) {
    Matcher m = FAX.matcher(fax);
    return (m.find() && m.group().equals(fax));
  }

  public static boolean isValidFileName(String fax) {
    Matcher m = FILE_NAME.matcher(fax);
    return (m.find() && m.group().equals(fax));
  }

  /**
   * Escape meta characters.
   *
   * @param inputString the input string
   * @return the string
   */
  public static String escapeMetaCharacters(String inputString) {
    String result = inputString;
    for (int i = 0; i < METACHARACTERS.length; i++) {
      if (result.contains(METACHARACTERS[i])) {
        result = result.replace(METACHARACTERS[i], "\\" + METACHARACTERS[i]);
      }
    }
    return result;
  }

  /**
   * Check certificate no valid.
   *
   * @return true, if successful
   */
  public static boolean checkCertificateNoValid(String str) {
    if (str == null) {
      return false;
    }
    return CERTIFICATE_NO_PATTERN.matcher(str).matches();
  }

  public static String escapePath(String path) {
    if(path == null || EMPTY.equals(path)){
      return path;
    }

    String step1 = path.replace("\\", "\\\\");
    step1 = step1.replace("%", "\\%");
    return step1.replace("_", "\\_");
  }

  public static String likeSpecialToString(String text) {
    if(text == null || EMPTY.equals(text)){
      return text;
    }

//    String step1 = path.replace("\\", "\\\\");
    text = text.replace("_", "\\_");
    text = text.replace("%", "\\%");
    return text;
  }

  public static String extractFileName(String fileName) {
    String result = fileName;
    if (isNullOrEmpty(result)) {
      return result;
    }


    result = result.replace("\\", "");
    result = result.replace("/", "");
    result = result.replace("%00", "");
    result = result.replace("%", "");

    return result;
  }

  public static String covertStringVn(String str) {
    try {
        return removeUnicode(str);
    } catch (Exception e) {
      log.error(e.getMessage(), e); 
    }
    return "";
 }

    public static String catString(final String str, int max) {
        if (str == null || EMPTY.equals(str)) {
            return EMPTY;
        }

        String result = str;
        if (result.length() > max) {
            result = result.substring(0, max - 4);
            result += " ...";
        }

        return result;
    }

}

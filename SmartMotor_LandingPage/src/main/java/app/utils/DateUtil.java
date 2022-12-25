package vn.viettel.app.utils;

import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

@Slf4j
public class DateUtil {

    public static final String FORMAT_DEFAULT = "dd/MM/yyyy HH:mm:ss";
    public static final String FORMAT_DEFAULT_DD_MM_YYYY = "dd/MM/yyyy";
    public static final String PATTERN_yyyyMMdd_HHmmss = "yyyy-MM-dd HH:mm:ss";

    private DateUtil() {
        throw new RuntimeException("Class is static");
    }

    public static long toMillisecond(Date date) {
        return date.getTime();
    }

    public static String toMillisecondStr(Date date) {
        return toMillisecond(date) + "";
    }

    public static Date toDate(long millisecond) {
        return new Date(millisecond);
    }

    public static Date toDateMl(String millisecondStr) {
        long millisecond = DataUtils.safeToLong(millisecondStr, 0L);
        if (millisecond == 0) {
            return null;
        }
        return new Date(millisecond);
    }

    public static Date toDateMl(String millisecondStr, int sub) {
        try {
            return toDateMl(millisecondStr);
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
        }
        return null;
    }

    public static Date toDate(String date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        sdf.setLenient(true);
        try {
            return sdf.parse(date);
        } catch (Exception ex) {
//      log.error(ex.getMessage(), ex);
            return null;
        }
    }

    public static String toDate(Date date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        try {
            return sdf.format(date);
        } catch (Exception ex) {
            return null;
        }
    }

    public static Date toDate(String date) {
        return toDate(date, FORMAT_DEFAULT);
    }

    public static Date toDateYYYYMMDD(String date) {
        if (date != null && date.length() > 8) {
            return toDate(date, "yyyy-MM-dd HH:mm:ss.s");
        }
        return toDate(date, "yyyyMMdd");
    }

    public static Integer getYear(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.YEAR);
    }

    public static Date toDate(LocalDateTime date) {
        Instant instant = date.atZone(ZoneId.systemDefault()).toInstant();
        return Date.from(instant);
    }
}

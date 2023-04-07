package com.example.elasticsearch.ultis;

import com.example.elasticsearch.ultis._enum.DatePattern;

import java.text.SimpleDateFormat;
import java.util.Date;

public final class DateUtils {
    public static String dateFormatToString(Date date, DatePattern datePattern){
        SimpleDateFormat sdf = new SimpleDateFormat(datePattern.getCode());
        return sdf.format(date);
    }
}

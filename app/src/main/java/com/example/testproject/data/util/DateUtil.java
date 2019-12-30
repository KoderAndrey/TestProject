package com.example.testproject.data.util;

import android.annotation.SuppressLint;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateUtil {

    private static final String PATTERN_DATE_ONLY_FULL_YEAR = "dd.MM.yyyy  hh:mm";


    @SuppressLint("SimpleDateFormat")
    public static String formatLongDateMultipliedBThousandy(long dateLong) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(PATTERN_DATE_ONLY_FULL_YEAR, Locale.getDefault());
        return dateFormat.format(new Date(dateLong * 1000));
    }

    @SuppressLint("SimpleDateFormat")
    public static String formatLongDate(long dateLong) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(PATTERN_DATE_ONLY_FULL_YEAR, Locale.getDefault());
        return dateFormat.format(new Date(dateLong));
    }

}
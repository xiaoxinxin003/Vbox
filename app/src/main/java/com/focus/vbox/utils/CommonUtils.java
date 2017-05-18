package com.focus.vbox.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by yxx on 2017/5/18.
 */

public class CommonUtils {
    public static String getTimeFromMillisecond(Long millisecond) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
        Date date = new Date(millisecond);
        String timeStr = simpleDateFormat.format(date);
        return timeStr;
    }
}

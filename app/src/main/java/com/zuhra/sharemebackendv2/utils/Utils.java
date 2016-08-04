package com.zuhra.sharemebackendv2.utils;

import java.text.SimpleDateFormat;

/**
 * Created by chen on 23.01.16.
 */
public class Utils {

    public static String getDate(long date){
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MMMM.yyyy HH:mm");
        return dateFormat.format(date);
    }
}

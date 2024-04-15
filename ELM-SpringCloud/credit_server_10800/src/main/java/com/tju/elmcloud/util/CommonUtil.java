package com.tju.elmcloud.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CommonUtil {
    public static String getCurrentDate(){
        SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar calendar = Calendar.getInstance();
        return sdf.format(calendar.getTime());
    }
    public static String getEndTime(int lifeSpan){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(calendar.getTime());
        calendar.add(Calendar.DATE,lifeSpan);
        SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(calendar.getTime());
    }
}

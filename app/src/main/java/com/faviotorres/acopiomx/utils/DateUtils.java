package com.faviotorres.acopiomx.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateUtils {

    public static String formatDte(String date) {
        SimpleDateFormat sdt = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault());
        try {
            Date dt = sdt.parse(date);
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(dt.getTime());
            int y = calendar.get(Calendar.YEAR)-2000;
            int m = calendar.get(Calendar.MONTH)+1;
            int d = calendar.get(Calendar.DAY_OF_MONTH);
            int hr = calendar.get(Calendar.HOUR_OF_DAY);
            int min = calendar.get(Calendar.MINUTE);
            String time = "AM";
            if (calendar.get(Calendar.AM_PM) == Calendar.PM) { time = "PM"; }
            String day  = String.valueOf(d);
            String month = String.valueOf(m);
            String minute = String.valueOf(min);
            if (d < 10) { day  = "0"+day;  }
            if (m < 10) { month = "0"+month; }
            if (min < 10) { minute = "0"+minute; }
            return month+"/"+day+"/"+y+" "+hr+":"+minute+" "+time;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }
}

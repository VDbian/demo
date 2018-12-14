package cn.com.ssii.core.util;

import android.annotation.SuppressLint;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Description:
 * Author chencheng
 * Time 2018/12/14
 */

public class DateUtils {
    //获取当前时间
    public static String DateNow(){
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");// HH:mm:ss
        @SuppressLint("SimpleDateFormat")
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date = new Date(System.currentTimeMillis());
       return df.format(date);
    }



    //把日期转为字符串
    public static String DateToString(Date date) {
        @SuppressLint("SimpleDateFormat")
        DateFormat df = new SimpleDateFormat("yyyy.MM.dd");
        return df.format(date);
    }

    //把字符串转为日期
    public static Date StringToDate(String strDate) {
        @SuppressLint("SimpleDateFormat")
        Date date = null;
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        try {
            date = df.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
}

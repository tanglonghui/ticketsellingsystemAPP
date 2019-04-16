package org.ironman.ticketsellingsystem.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @data created on 2019/1/24.
 * @describe TODO 关于时间的工具类合集
 */

public class TimeUtil {
    /*
    * 将时间戳按指定格式转换为时间
    */
    public static String stampToDate(String stamp, String format) {
        String res;
        //SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        long lt = new Long(stamp);
        Date date = new Date(lt);
        res = simpleDateFormat.format(date);
        return res;
    }
    //转换时间格式
    public static String dateConver(String str, String format1, String format2){
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat(format1);
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat(format2);
        String result ="";
        try {
            result = simpleDateFormat2.format(simpleDateFormat1.parse(str));
            return result;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result;
    }


}

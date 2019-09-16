package com.css.aq.base.util;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Description TODO
 * @Author yuhui
 * @Date 2019/9/12 17:23
 **/
public class DateUtil {

    public static void main(String[] args) {
        parseDateLong();
    }
    /**
     * 获取现在时间
     * @return 返回长时间格式 yyyy-MM-dd HH:mm:ss
     */
    public static Date parseDateLong(){
        Date currentDate = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formaTime = sdf.format(currentDate);
        Date date = null;
        try {
            date = sdf.parse(formaTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     *  获取现在时间
     * @return 返回短时间格式 yyyy-MM-dd
     */
    public static Date parseDateShort(){
        Date currentDate = new Date();
        SimpleDateFormat spf = new SimpleDateFormat("yyyy-MM-dd");
        String formaTime = spf.format(currentDate);
        ParsePosition pos = new ParsePosition(8);
        Date date = spf.parse(formaTime,pos);
        return date;
    }


}

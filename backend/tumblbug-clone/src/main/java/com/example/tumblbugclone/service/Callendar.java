package com.example.tumblbugclone.service;

import com.example.tumblbugclone.managedconst.ProjectConst;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Callendar {
    static private Date date;
    static private SimpleDateFormat format = new SimpleDateFormat(ProjectConst.DATE_FORMAT);

    public static String getTodayString(){
        date = new Date();
        String today = Callendar.format.format(date);
        return today;
    }

    public static Date getToday(){
        return new Date();
    }

    public static Date convertDate(String dateString) throws ParseException {
        try {
            date = format.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
            throw e;
        }

        return date;
    }

    public static boolean after(String dateString1, String dateString2) throws ParseException {
        try {
            Date date1 = format.parse(dateString1);
            Date date2 = format.parse(dateString2);

            return date1.after(date2);
        } catch (ParseException e) {
            throw e;
        }
    }

    public static boolean before(String dateString1, String dateString2) throws ParseException {
        try {
            Date date1 = format.parse(dateString1);
            Date date2 = format.parse(dateString2);

            return date1.before(date2);
        } catch (ParseException e) {
            throw e;
        }
    }

    public static boolean equals(String dateString1, String dateString2) throws ParseException {
        try {
            Date date1 = format.parse(dateString1);
            Date date2 = format.parse(dateString2);

            return date1.equals(date2);
        } catch (ParseException e) {
            throw e;
        }
    }
}

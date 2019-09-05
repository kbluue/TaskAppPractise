package com.example.kbluue_.unnamedtaskapp.Models;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class Timestamp {

    static final String REGEX = "-";
    static final String CONTROL_FORMAT = "d-MM-YY";
    static final String TODAY_FORMAT = "hh:mm a";
    static final String YESTERDAY = "Yesterday";
    static final String DAY_OF_WEEK_FORMAT = "EEEE";
    static final String DAYS_AGO = " days ago";
    static final String SAME_YEAR_FORMAT = "MMM d";
    static final String DIFFERENT_DAY_FORMAT = "d-MMM-YY";

    public final Date date;

    public Timestamp(){
        date = new Date();
    }

    public Date getDate() {
        return date;
    }

    public String print() {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(getFormat(), Locale.ENGLISH);
            sdf.setTimeZone(TimeZone.getDefault());
            return sdf.format(getDate());
        } catch (IllegalArgumentException e){
            return getFormat();
        }

    }

    private String getFormat(){
        SimpleDateFormat format = new SimpleDateFormat(CONTROL_FORMAT, Locale.ENGLISH);
        format.setTimeZone(TimeZone.getDefault());
        String[] d1 = format.format(new Date()).split(REGEX),
                d2 = format.format(getDate()).split(REGEX);
        if (Arrays.equals(d1, d2)){
            return TODAY_FORMAT;
        } else if (d1[2].equals(d2[2]) && d1[1].equals(d2[1])){
            int daysApart = Integer.parseInt(d1[0]) - Integer.parseInt(d2[0]);
            if (daysApart == 1){
                return YESTERDAY;
            } else if (daysApart < 7){
                return DAY_OF_WEEK_FORMAT;
            } else {
                return daysApart + DAYS_AGO;
            }
        } else if (d1[2].equals(d2[2])) {
            return SAME_YEAR_FORMAT;
        } else {
            return DIFFERENT_DAY_FORMAT;
        }
    }
}

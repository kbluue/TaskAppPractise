package com.example.kbluue_.unnamedtaskapp.Models;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class Timestamp {

    protected static final String REGEX = "-";
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
        SimpleDateFormat format = new SimpleDateFormat("d-MM-YY", Locale.ENGLISH);
        format.setTimeZone(TimeZone.getDefault());
        String[] d1 = format.format(new Date()).split(REGEX),
                d2 = format.format(getDate()).split(REGEX);
        if (Arrays.equals(d1, d2)){
            return "hh:mm a";
        } else if (d1[2].equals(d2[2]) && d1[1].equals(d2[1])){
            int daysApart = Integer.parseInt(d1[0]) - Integer.parseInt(d2[0]);
            if (daysApart == 1){
                return "Yesterday";
            } else if (daysApart < 7){
                return "EEEE";
            } else {
                return daysApart + " days ago";
            }
        } else if (d1[2].equals(d2[2])) {
            return "MMM d";
        } else {
            return "d-MMM-YY";
        }
    }
}

package com.example.kbluue_.unnamedtaskapp.Models;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class Timestamp {

    public final String timeZone;
    public final Date date;

    public Timestamp(){
        timeZone = TimeZone.getDefault().getDisplayName();
        Date tempDate;
        try {
            tempDate = setDate();
        } catch (ParseException e) {
            tempDate = new Date();
        }
        date = tempDate;
    }

    public TimeZone getTimeZone() {
        return TimeZone.getTimeZone(timeZone);
    }

    public Date getDate() {
        return date;
    }

    private String getDateString(){
        return getDefaultSDF()
                .format(new Date());
    }

    private SimpleDateFormat getDefaultSDF(){
        return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.ENGLISH);
    }

    public Date setDate() throws ParseException {
        SimpleDateFormat sdf = getDefaultSDF();
        sdf.setTimeZone(getTimeZone());
        return sdf.parse(getDateString());
    }

    public String print(String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.ENGLISH);
        sdf.setTimeZone(TimeZone.getDefault());
        return sdf.format(getDate());
    }
}

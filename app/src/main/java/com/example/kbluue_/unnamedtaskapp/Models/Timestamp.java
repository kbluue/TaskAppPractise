package com.example.kbluue_.unnamedtaskapp.Models;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class Timestamp {

    public final Date date;

    public Timestamp(){
        date = new Date();
    }

    public Date getDate() {
        return date;
    }

    public String print(String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.ENGLISH);
        sdf.setTimeZone(TimeZone.getDefault());
        return sdf.format(getDate());
    }
}

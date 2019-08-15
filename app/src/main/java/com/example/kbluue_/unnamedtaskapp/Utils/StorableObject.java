package com.example.kbluue_.unnamedtaskapp.Utils;

import android.content.Context;

public class StorableObject {

    private String id, prefix;
    private Context context;

//    private final static Gson

    private StorableObject(String prefix, Context context) {
        this.prefix = prefix;
        this.context = context;
    }
}

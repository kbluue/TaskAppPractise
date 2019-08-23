package com.example.kbluue_.unnamedtaskapp.Models;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.kbluue_.unnamedtaskapp.R;
import com.google.gson.Gson;

import java.util.Locale;

public class StorableObject {

    private String id, prefix;

    private final static Gson GSON = new Gson();

    public StorableObject(){}

    public StorableObject(Context context, String prefix) {
        this.prefix = prefix;
        setId(String.format(Locale.ENGLISH, "%s%04d", prefix, getUID(context)));
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public static String getAppName(Context context){
        return context.getString(R.string.app_name);
    }

    public static SharedPreferences getPref(Context context){
        return context.getSharedPreferences(getAppName(context), Context.MODE_PRIVATE);
    }

    public static int getUID(Context context){
        int lastUID = getPref(context).getInt("UID", 0);
        int UID = ++lastUID;
        getPref(context).edit()
                .putInt("UID", UID)
                .apply();
        return UID;
    }

    private String getGsonValue(){
        return GSON.toJson(this);
    }

    public void save(Context context){
        getPref(context).edit()
                .putString(getId(), getGsonValue())
                .apply();
    }

    public static <T extends StorableObject> StorableObject getInstance(Context context, String id,Class<T> aClass) {
        String JsonValue =  StorableObject.getPref(context)
                .getString(id, "");
        return GSON.fromJson(JsonValue, aClass);
    }
}

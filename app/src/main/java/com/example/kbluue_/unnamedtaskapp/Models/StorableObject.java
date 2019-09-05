package com.example.kbluue_.unnamedtaskapp.Models;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.kbluue_.unnamedtaskapp.Interfaces.Observable;
import com.example.kbluue_.unnamedtaskapp.R;
import com.google.gson.Gson;

import java.util.Locale;

import static com.example.kbluue_.unnamedtaskapp.Models.StringConstants.EMPTY_STRING;
import static com.example.kbluue_.unnamedtaskapp.Models.StringConstants.ID_FORMAT;
import static com.example.kbluue_.unnamedtaskapp.Models.StringConstants.NAME_FORMAT;
import static com.example.kbluue_.unnamedtaskapp.Models.StringConstants.UID;

public class StorableObject implements Observable {

    private String id, name;
    private boolean changed;
    public transient Context context;

    private final static Gson GSON = new Gson();

    public StorableObject(){}

    public StorableObject(Context context, String name) {
        this.context = context;
        setId(String.format(Locale.ENGLISH, ID_FORMAT, name.charAt(0), getUID(context)));
        this.name = String.format(Locale.ENGLISH, NAME_FORMAT, name, getId());
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
        setChanged();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        setChanged();
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    @Override
    public boolean isChanged() {
        return changed;
    }

    @Override
    public void setChanged() {
        changed = true;
    }

    @Override
    public void clearChanged() {
        changed = false;
    }

    @Override
    public Runnable getAction() {
        return this::save;
    }

    private static String getAppName(Context context){
        return context.getString(R.string.app_name);
    }

    public static SharedPreferences getPref(Context context){
        return context.getSharedPreferences(getAppName(context), Context.MODE_PRIVATE);
    }

    private static int getUID(Context context){
        int lastUID = getPref(context).getInt(UID, 0);
        int UID = ++lastUID;
        getPref(context).edit()
                .putInt(StringConstants.UID, UID)
                .apply();
        return UID;
    }

    private String getGsonValue(){
        return GSON.toJson(this);
    }

    public void save(){
        getPref(getContext()).edit()
                .putString(getId(), getGsonValue())
                .apply();
    }

    public void delete(){
        getPref(getContext()).edit()
                .remove(getId())
                .apply();
    }

    public static <T extends StorableObject> StorableObject getInstance(Context context, String id,Class<T> aClass) {
        String JsonValue =  StorableObject.getPref(context)
                .getString(id, EMPTY_STRING);
        StorableObject storableObject =  GSON.fromJson(JsonValue, aClass);
        storableObject.setContext(context);
        return storableObject;
    }
}

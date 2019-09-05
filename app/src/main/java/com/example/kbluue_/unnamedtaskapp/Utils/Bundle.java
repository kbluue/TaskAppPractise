package com.example.kbluue_.unnamedtaskapp.Utils;

import androidx.appcompat.app.AppCompatActivity;

import com.example.kbluue_.unnamedtaskapp.Models.StorableObject;

import java.util.HashSet;
import java.util.Set;

public class Bundle {
    
    private final AppCompatActivity host;
    private Set<String> ids;

    public Bundle(AppCompatActivity host) {
        this.host = host;
        String key = host.getLocalClassName();
        ids = StorableObject.getPref(host.getApplicationContext())
                .getStringSet(key, new HashSet<>());
    }

    public void putBoolean(){}
    
    public void putInt(){}
    
    public void putString(){}
    
    public void putStorable(){}

    public void getBoolean(){}

    public void getInt(){}

    public void getString(){}

    public void getStorable(){}
}

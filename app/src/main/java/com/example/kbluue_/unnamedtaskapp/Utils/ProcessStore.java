package com.example.kbluue_.unnamedtaskapp.Utils;

import java.util.HashMap;

public class ProcessStore {

    private static HashMap<String, Object> objects = new HashMap<>();

    public static void addObject(String key, Object object){
        objects.put(key, object);
    }

    public static <T> T getObject(String key, Class<T> tClass){
        return tClass.cast(objects.get(key));
    }
}

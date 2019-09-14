package com.example.kbluue_.unnamedtaskapp.Interfaces;

import java.util.ArrayList;
import java.util.Arrays;

public interface Mockable {

    static boolean isMockable(b){
        return this in
    }

    static Object getInstance(Class aClass){
        Object instance = null;
        if (aClass == ArrayList.class){
            instance = new ArrayList<>();
        }
        return instance;
    }
}

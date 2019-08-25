package com.example.kbluue_.unnamedtaskapp.Utils;

public class ArraysUtil {

    public static <T extends Object> void add(T[] array, T obj){
        int len = 0;
        if (array != null) {
            len = array.length;
        }
        Object[] objects = new Object[len + 1];
        for (int i = 0; i < len; i++) {
            objects[i] = array[i];
        }
        objects[len] = obj;
        array = (T[]) objects;
    }
    public static <T extends Object> void remove(T[] array, T obj){
        int len = 0;
        if (array != null) {
            len = array.length;
        }
        Object[] objects = new Object[len];
        for (int i = 0; i < len; i++) {
            if (array[i].equals(obj)){
                i--;
                continue;
            }
            objects[i] = array[i];
        }
        objects[len] = obj;
        array = (T[]) objects;
    }
}

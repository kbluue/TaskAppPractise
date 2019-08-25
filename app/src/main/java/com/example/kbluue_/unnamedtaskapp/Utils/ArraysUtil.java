package com.example.kbluue_.unnamedtaskapp.Utils;

public class ArraysUtil {

    public static Object[] add(Object[] array, Object obj){
        int len = 0;
        if (array != null) {
            len = array.length;
        }
        Object[] objects = new Object[len + 1];
        for (int i = 0; i < len; i++) {
            objects[i] = array[i];
        }
        objects[len] = obj;
        return objects;
    }
    public static <T extends Object> T[] remove(T[] array, T obj){
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
        return (T[]) objects;
    }
}

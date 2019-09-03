package com.example.kbluue_.unnamedtaskapp.Utils;

import java.util.ArrayList;

public class CustomList<E> extends ArrayList<E> {

    @Override
    public E get(int index) {
        try {
            return super.get(index);
        } catch (IndexOutOfBoundsException e){
            return super.get(index < 0 ? 0 : size() - 1);
        }

    }
}

package com.example.kbluue_.unnamedtaskapp.Utils;

import androidx.recyclerview.widget.ItemTouchHelper;

import org.junit.Test;

import static org.junit.Assert.*;

public class SwipeHandlerTest {

    @Test
    public void setDefaultSwipe(){
        SwipeHandler handler = new SwipeHandler();

        handler.setDefaultSwipeDirs(ItemTouchHelper.UP);
    }

}
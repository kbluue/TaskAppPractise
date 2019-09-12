package com.example.kbluue_.unnamedtaskapp.Utils;

import com.example.kbluue_.unnamedtaskapp.Adapters.TaskAdapter;

import org.junit.Test;

public class SwipeHandlerTest {

    @Test
    public void setDefaultSwipe(){

        SwipeHandler.getInstance(new TaskAdapter())
                .setLeftSwipeAction(null)
                .setLeftSwipeAction(null)
                .setRightSwipeAction(null);
    }

}
package com.example.kbluue_.unnamedtaskapp.Models;

import android.content.Context;

import androidx.test.InstrumentationRegistry;
import androidx.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
public class TaskTest {

    private Context context;

    @Before
    public void init(){
        context = InstrumentationRegistry.getTargetContext();
    }

    @Test
    public void addChild() {

        Task task = new Task(context);
        SubTask subTask = new SubTask(context);

        task.addChild(subTask);

        assertEquals(subTask.getLastUpdated(), task.getLastUpdated());

    }
}
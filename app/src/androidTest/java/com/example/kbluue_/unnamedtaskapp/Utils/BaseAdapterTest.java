package com.example.kbluue_.unnamedtaskapp.Utils;

import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import com.example.kbluue_.unnamedtaskapp.Activities.SingleTaskActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
public class BaseAdapterTest {

    @Rule
    public ActivityTestRule<SingleTaskActivity> singleTaskActivityTestRule
            = new ActivityTestRule<>(SingleTaskActivity.class);

    @Test
    public void setChildOnClickListener() {
        assertEquals(1, 1);
    }

    @Test
    public void insertIntoDataSet() {
    }

    @Test
    public void removeFromDataSet() {
    }

    @Test
    public void onCreateViewHolder() {
    }

    @Test
    public void onBindViewHolder() {
    }

    @Test
    public void getItemCount() {
    }

    @Test
    public void notifyDataChanged() {
    }

    @Test
    public void finish() {
    }
}
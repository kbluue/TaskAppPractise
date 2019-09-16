package com.example.kbluue_.unnamedtaskapp.Utils;

import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import com.example.kbluue_.unnamedtaskapp.Activities.TaskListActivity;
import com.example.kbluue_.unnamedtaskapp.R;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;

@RunWith(AndroidJUnit4.class)
public class BaseAdapterTest {

    @Rule
    public ActivityTestRule<TaskListActivity> testRule
            = new ActivityTestRule<>(TaskListActivity.class);
    private BaseAdapter baseAdapter;

    @Before
    public void initAdapter(){
        baseAdapter = testRule.getActivity()
                .getAdapter();
    }

    @Test
    public void setChildOnClickListener() {
        final Object[] tester = {null};
        baseAdapter.setChildOnClickListener(v -> tester[0] = new Object());
        onView(ViewMatchers.withId(R.id.view_task)).perform(click());
        Assert.assertNotNull(tester[0]);
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
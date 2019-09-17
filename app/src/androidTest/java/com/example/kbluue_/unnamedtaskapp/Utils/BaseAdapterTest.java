package com.example.kbluue_.unnamedtaskapp.Utils;

import android.content.Context;

import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import com.example.kbluue_.unnamedtaskapp.Activities.TaskListActivity;
import com.example.kbluue_.unnamedtaskapp.BaseAdapters.TaskAdapter;
import com.example.kbluue_.unnamedtaskapp.Models.Task;
import com.example.kbluue_.unnamedtaskapp.R;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Arrays;
import java.util.List;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

@RunWith(AndroidJUnit4.class)
public class BaseAdapterTest {

    @Rule
    public ActivityTestRule<TaskListActivity> testRule
            = new ActivityTestRule<>(TaskListActivity.class);
    private TaskListActivity testActivity;
    private Context context;
    private Task sampleTask;

    @Before
    public void init(){
        testActivity = testRule.getActivity();
        context = testActivity.getBaseContext();
        sampleTask = new Task(context);
    }

    @Test
    public void setChildOnClickListener() {
        List<Task> dataSet = Arrays.asList(sampleTask);
        TaskAdapter adapter = new TaskAdapter(dataSet);
        final Object[] testObject = {null};
        adapter.setChildOnClickListener(v -> testObject[0] = new Object());

        testActivity.setTaskAdapter(adapter);
        testActivity.initRecyclerView();

        assertNull(testObject[0]);
        onView(withId(R.id.tasks_rv))
                .perform(actionOnItemAtPosition(0, click()));
        assertNotNull(testObject[0]);
    }

    @Test
    public void insertIntoDataSet() {
        BaseAdapter adapter = testActivity.getAdapter();
        int oldSize = adapter.getItemCount();
        safeRunOnUiThread(() -> adapter.insertIntoDataSet(sampleTask));

        assertRecyclerViewChildExists(R.id.tasks_rv, oldSize);
        assertEquals(oldSize + 1, adapter.getItemCount());
    }

    private void assertRecyclerViewChildExists(int recyclerViewId, int position) {
        onView(withId(recyclerViewId))
                .perform(actionOnItemAtPosition(position, click()));
    }

    private void safeRunOnUiThread(Runnable runnable) {
        try {
            testRule.runOnUiThread(runnable);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
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
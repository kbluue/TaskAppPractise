package com.example.kbluue_.unnamedtaskapp.Activities;

import android.content.Context;
import android.content.Intent;

import com.example.kbluue_.unnamedtaskapp.Models.Task;
import com.example.kbluue_.unnamedtaskapp.R;
import com.example.kbluue_.unnamedtaskapp.Utils.BaseActivity;

public class SingleTaskActivity extends BaseActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.layout_single_task;
    }

    @Override
    protected void init() {

    }

    public static void start(Context context, Task task) {
        Intent starter = new Intent(context, SingleTaskActivity.class);
        starter.putExtra("task", task);
        context.startActivity(starter);
    }
}

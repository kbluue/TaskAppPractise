package com.example.kbluue_.unnamedtaskapp.Activities;

import com.example.kbluue_.unnamedtaskapp.Models.Timestamp;
import com.example.kbluue_.unnamedtaskapp.R;
import com.example.kbluue_.unnamedtaskapp.Utils.BaseActivity;

public class TaskListActivity extends BaseActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.layout_task_list;
    }

    @Override
    protected void init() {
        Timestamp t = new Timestamp();
        System.out.println(t.print("MMMM, dd YY: hh:mm"));
    }
}

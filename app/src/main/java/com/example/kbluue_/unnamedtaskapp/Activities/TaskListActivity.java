package com.example.kbluue_.unnamedtaskapp.Activities;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kbluue_.unnamedtaskapp.Adapters.TaskAdapter;
import com.example.kbluue_.unnamedtaskapp.Models.Task;
import com.example.kbluue_.unnamedtaskapp.R;
import com.example.kbluue_.unnamedtaskapp.Utils.BaseActivity;

public class TaskListActivity extends BaseActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.layout_task_list;
    }

    @Override
    protected void init() {
        TaskAdapter.tasks.add(new Task(this));
        RecyclerView rv = findViewById(R.id.tasks_rv);
        rv.setHasFixedSize(true);
        rv.setAdapter(new TaskAdapter());
        rv.setLayoutManager(new LinearLayoutManager(this));
    }
}

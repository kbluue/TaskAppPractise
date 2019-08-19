package com.example.kbluue_.unnamedtaskapp.Activities;

import android.content.Context;
import android.content.Intent;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kbluue_.unnamedtaskapp.Adapters.SubTaskAdapter;
import com.example.kbluue_.unnamedtaskapp.Adapters.TaskAdapter;
import com.example.kbluue_.unnamedtaskapp.Interfaces.ClickableAction;
import com.example.kbluue_.unnamedtaskapp.Interfaces.HasMenu;
import com.example.kbluue_.unnamedtaskapp.Models.Task;
import com.example.kbluue_.unnamedtaskapp.R;
import com.example.kbluue_.unnamedtaskapp.Utils.BaseActivity;
import com.example.kbluue_.unnamedtaskapp.Utils.ViewBinder;

import java.util.Collections;
import java.util.List;

public class SingleTaskActivity extends BaseActivity implements HasMenu {

    @Override
    protected int getLayoutId() {
        return R.layout.layout_single_task;
    }

    @Override
    protected void init() {
        int taskPosition = getIntent().getIntExtra("taskPosition", -1);
        Task task;

        if (taskPosition < 0) {
            TaskAdapter.tasks.add(new Task(this));
            Collections.sort(TaskAdapter.tasks);
            taskPosition = 0;
        }

        task = TaskAdapter.tasks.get(taskPosition);

        ViewBinder.getInstance(this)
                .bind(R.id.sv_task_name, task.getName());

        RecyclerView rv = findViewById(R.id.sub_tasks_rv);
        rv.setHasFixedSize(true);
        rv.setAdapter(new SubTaskAdapter(taskPosition));
        rv.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public int setMenuId() {
        return R.menu.for_single_task;
    }

    @Override
    public List<ClickableAction> setMenuActions() {
        return null;
    }

    public static void start(Context context, int taskPosition) {
        Intent starter = new Intent(context, SingleTaskActivity.class);
        starter.putExtra("taskPosition", taskPosition);
        context.startActivity(starter);
    }
}

package com.example.kbluue_.unnamedtaskapp.Activities;

import android.content.Context;
import android.content.Intent;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kbluue_.unnamedtaskapp.Adapters.SubTaskAdapter;
import com.example.kbluue_.unnamedtaskapp.Adapters.TaskAdapter;
import com.example.kbluue_.unnamedtaskapp.Interfaces.ClickableAction;
import com.example.kbluue_.unnamedtaskapp.Interfaces.HasMenu;
import com.example.kbluue_.unnamedtaskapp.Interfaces.HasRecyclerView;
import com.example.kbluue_.unnamedtaskapp.Models.Task;
import com.example.kbluue_.unnamedtaskapp.R;
import com.example.kbluue_.unnamedtaskapp.Utils.BaseActivity;
import com.example.kbluue_.unnamedtaskapp.Utils.ViewBinder;

import java.util.Collections;
import java.util.List;

public class SingleTaskActivity extends BaseActivity implements HasMenu, HasRecyclerView {

    int taskIndex;

    @Override
    protected int getLayoutId() {
        return R.layout.layout_single_task;
    }

    @Override
    protected void init() {
        taskIndex = getIntent().getIntExtra("taskIndex", -1);

        if (taskIndex < 0) {
            TaskAdapter.tasks.add(new Task(this));
            Collections.sort(TaskAdapter.tasks);
            taskIndex = 0;
        }

        Task task = TaskAdapter.tasks.get(taskIndex);

        ViewBinder.getInstance(this)
                .bind(R.id.sv_task_name, task.getName());
    }

    @Override
    public int setMenuId() {
        return R.menu.for_single_task;
    }

    @Override
    public List<ClickableAction> setMenuActions() {
        return null;
    }

    @Override
    public int getViewRes() {
        return R.id.sub_tasks_rv;
    }

    @Override
    public RecyclerView.Adapter getAdapter() {
        return new SubTaskAdapter(taskIndex);
    }

    @Override
    public RecyclerView.LayoutManager getLayoutManager() {
        return new LinearLayoutManager(this);
    }

    public static void start(Context context, int taskPosition) {
        Intent starter = new Intent(context, SingleTaskActivity.class);
        starter.putExtra("taskIndex", taskPosition);
        context.startActivity(starter);
    }
}

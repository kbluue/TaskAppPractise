package com.example.kbluue_.unnamedtaskapp.Activities;

import android.content.Context;
import android.content.Intent;

import com.example.kbluue_.unnamedtaskapp.Interfaces.ClickableAction;
import com.example.kbluue_.unnamedtaskapp.Interfaces.HasMenu;
import com.example.kbluue_.unnamedtaskapp.Models.Task;
import com.example.kbluue_.unnamedtaskapp.R;
import com.example.kbluue_.unnamedtaskapp.Utils.BaseActivity;
import com.example.kbluue_.unnamedtaskapp.Utils.ViewBinder;

import java.util.List;

public class SingleTaskActivity extends BaseActivity implements HasMenu {

    @Override
    protected int getLayoutId() {
        return R.layout.layout_single_task;
    }

    @Override
    protected void init() {
        Task task = (Task) getIntent().getSerializableExtra("task");
        if (task == null){
            task = new Task(this);
        }

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

    public static void start(Context context, Task task) {
        Intent starter = new Intent(context, SingleTaskActivity.class);
        starter.putExtra("task", task);
        context.startActivity(starter);
    }
}

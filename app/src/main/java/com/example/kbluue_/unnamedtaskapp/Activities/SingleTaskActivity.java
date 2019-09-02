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
import com.example.kbluue_.unnamedtaskapp.Utils.ViewConfig;

import java.util.Collections;
import java.util.List;

import static android.util.Log.wtf;
import static com.example.kbluue_.unnamedtaskapp.R.string.app_name;

public class SingleTaskActivity extends BaseActivity implements HasMenu, HasRecyclerView {

    int taskIndex;
    final RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);

    public int getTaskIndex() {
        wtf(getString(app_name), "SingleTaskActivity.getTaskIndex: " + this.taskIndex );
        return taskIndex;
    }

    public void setTaskIndex(int taskIndex) {
        wtf(getString(app_name), "SingleTaskActivity.setTaskIndex: " + this.taskIndex + " vs " + taskIndex);
        this.taskIndex = taskIndex;
    }

    @Override
    protected void onPause() {
        TaskAdapter.tasks.get(getTaskIndex()).notifyAction();
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        TaskAdapter.tasks.get(getTaskIndex()).notifyAction();
        super.onDestroy();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.layout_single_task;
    }

    @Override
    protected void init() {
        init(getIntent().getIntExtra("taskIndex", -1));
    }

    @Override
    public int setMenuId() {
        return R.menu.for_single_task;
    }

    @Override
    public List<ClickableAction> setMenuActions() {
        return new ClickableAction.Factory()
                .addMember(R.id.sv_prev_menu, (Runnable) () -> {
                    init(getTaskIndex() - 1);
                })
                .addMember(R.id.sv_next_menu, (Runnable) () -> {
                    init(getTaskIndex() + 1);
                })
                .deliver();
    }

    @Override
    public int getViewRes() {
        return R.id.sub_tasks_rv;
    }

    @Override
    public RecyclerView.Adapter getAdapter() {
        return new SubTaskAdapter(this);
    }

    @Override
    public RecyclerView.LayoutManager getLayoutManager() {
        return layoutManager;
    }

    private void init(int in){
        setTaskIndex(in);
        wtf(getString(app_name), "SingleTaskActivity.init: " + getTaskIndex());

        if (getTaskIndex() < 0) {
            TaskAdapter.tasks.add(new Task(this));
            Collections.sort(TaskAdapter.tasks);
            setTaskIndex(0);
        }

        Task task = TaskAdapter.tasks.get(getTaskIndex());

        ViewConfig.getInstance(this)
                .bind(R.id.sv_task_name, task.getName());

        if (getBaseAdapter() != null) {
            wtf(getString(app_name), "SingleTaskActivity.init: @baseAdapter != null" + getTaskIndex());
            ((SubTaskAdapter) getBaseAdapter())
                    .updateSubtaskList();
        }
    }

    public static void start(Context context, int taskPosition) {
        Intent starter = new Intent(context, SingleTaskActivity.class);
        starter.putExtra("taskIndex", taskPosition);
        context.startActivity(starter);
    }
}

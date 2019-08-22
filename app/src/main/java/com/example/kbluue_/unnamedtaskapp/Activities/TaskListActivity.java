package com.example.kbluue_.unnamedtaskapp.Activities;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kbluue_.unnamedtaskapp.Adapters.TaskAdapter;
import com.example.kbluue_.unnamedtaskapp.Interfaces.ClickableAction;
import com.example.kbluue_.unnamedtaskapp.Interfaces.HasButtons;
import com.example.kbluue_.unnamedtaskapp.Interfaces.HasRecyclerView;
import com.example.kbluue_.unnamedtaskapp.Models.Task;
import com.example.kbluue_.unnamedtaskapp.R;
import com.example.kbluue_.unnamedtaskapp.Utils.BaseActivity;

import java.util.List;

public class TaskListActivity extends BaseActivity implements HasButtons, HasRecyclerView {

    @Override
    protected int getLayoutId() {
        return R.layout.layout_task_list;
    }

    @Override
    protected void init() {
        TaskAdapter.tasks.add(new Task(this));
    }

    @Override
    public List<ClickableAction> getButtonActions() {
        return new ClickableAction.Factory()
                .addMember(R.id.add_task,
                        (Runnable) () -> SingleTaskActivity.start(this,-1), false)
                .deliver();
    }

    @Override
    public int getViewRes() {
        return R.id.tasks_rv;
    }

    @Override
    public RecyclerView.Adapter getAdapter() {
        return new TaskAdapter();
    }

    @Override
    public RecyclerView.LayoutManager getLayoutManager() {
        return new LinearLayoutManager(this);
    }
}

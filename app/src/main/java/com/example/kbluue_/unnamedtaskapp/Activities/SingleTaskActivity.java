package com.example.kbluue_.unnamedtaskapp.Activities;

import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.widget.EditText;
import android.widget.TextView;

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
import com.example.kbluue_.unnamedtaskapp.Utils.ServiceUtils;
import com.example.kbluue_.unnamedtaskapp.Utils.ViewConfig;

import java.util.Collections;
import java.util.List;

public class SingleTaskActivity extends BaseActivity implements HasMenu, HasRecyclerView {

    public static int taskIndex;
    final RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);

    @Override
    protected void onPause() {
        TaskAdapter.tasks.get(taskIndex).notifyAction();
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        TaskAdapter.tasks.get(taskIndex).notifyAction();
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenu().findItem(R.id.sv_prev_menu)
                .setVisible(taskIndex != 0);
        getMenu().findItem(R.id.sv_next_menu)
                .setVisible(taskIndex != TaskAdapter.tasks.size() - 1);
        return true;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.layout_single_task;
    }

    @Override
    protected void init() {
        if (taskIndex < 0) {
            TaskAdapter.tasks.add(new Task(this));
            Collections.sort(TaskAdapter.tasks);
            taskIndex = 0;
            EditText view = findViewById(R.id.sv_task_name);
            view.postDelayed(view::requestFocus, 200);
            ServiceUtils.showKeyboard(view);
        } else if (taskIndex >= TaskAdapter.tasks.size()){
            taskIndex = TaskAdapter.tasks.size() - 1;
        }

        Task task = TaskAdapter.tasks.get(taskIndex);

        ViewConfig.getInstance(this)
                .bind(R.id.sv_task_name, task.getName())
                .addTextListener(R.id.sv_task_name, (s, start, before, count) -> task.setName(s.toString()));
    }

    @Override
    public int setMenuId() {
        return R.menu.for_single_task;
    }

    @Override
    public List<ClickableAction> setMenuActions() {
        return new ClickableAction.Factory()
                .addMember(R.id.sv_prev_menu, (Runnable) () -> start(this, --taskIndex))
                .addMember(R.id.sv_next_menu, (Runnable) () -> start(this, ++taskIndex))
                .addMember(R.id.sv_delete_menu, (Runnable) () -> {
                    TaskAdapter.tasks.get(taskIndex).delete();
                    TaskAdapter.tasks.remove(taskIndex);
                    start(this, taskIndex);
                })
                .addMember(R.id.sv_edit_menu, (Runnable) () -> {
                    TextView view = findViewById(R.id.sv_task_name);
                    view.requestFocus();
                })
                .deliver();
    }

    @Override
    public int getViewRes() {
        return R.id.sub_tasks_rv;
    }

    @Override
    public RecyclerView.Adapter getAdapter() {
        return new SubTaskAdapter(getLayoutManager());
    }

    @Override
    public RecyclerView.LayoutManager getLayoutManager() {
        return layoutManager;
    }

    public static void start(Context context, int taskPosition) {
        Intent starter = new Intent(context, SingleTaskActivity.class);
        taskIndex = taskPosition;
        context.startActivity(starter);
    }
}

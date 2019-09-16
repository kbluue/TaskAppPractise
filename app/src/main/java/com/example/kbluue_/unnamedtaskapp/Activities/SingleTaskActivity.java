package com.example.kbluue_.unnamedtaskapp.Activities;

import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.widget.EditText;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kbluue_.unnamedtaskapp.Adapters.SubTaskAdapter;
import com.example.kbluue_.unnamedtaskapp.Interfaces.ClickableAction;
import com.example.kbluue_.unnamedtaskapp.Interfaces.HasInitialState;
import com.example.kbluue_.unnamedtaskapp.Interfaces.HasMenu;
import com.example.kbluue_.unnamedtaskapp.Interfaces.HasRecyclerView;
import com.example.kbluue_.unnamedtaskapp.Models.Task;
import com.example.kbluue_.unnamedtaskapp.R;
import com.example.kbluue_.unnamedtaskapp.Utils.BaseActivity;
import com.example.kbluue_.unnamedtaskapp.Utils.ServiceUtils;
import com.example.kbluue_.unnamedtaskapp.Utils.ViewConfig;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.example.kbluue_.unnamedtaskapp.Utils.ProcessStore.putObject;

public class SingleTaskActivity extends BaseActivity implements HasInitialState, HasMenu, HasRecyclerView {

    private static final String INITIAL_TASK_STATE = "initial task state";
    public static int taskIndex;
    final RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);

    @Override
    protected void onPause() {
        getTask().notifyAction();
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        getTask().notifyAction();
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        getMenu().findItem(R.id.sv_prev_menu)
                .setVisible(taskIndex != 0);
        getMenu().findItem(R.id.sv_next_menu)
                .setVisible(taskIndex != getLastIndexOfDataSet());

        return true;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.layout_single_task;
    }

    @Override
    protected void init() {
        if (taskIndex < 0)
            prepareNewTask();
        else if (isTest())
            mockTasks();
        else if (indexOverflows()) {
            final int lastIndex = getLastIndexOfDataSet();
            setTaskIndex(lastIndex);
        }

        Task task = getTask();

        bindTaskNameView(task);
    }

    private boolean isTest() {
        return TaskListActivity.getTasks() == null;
    }

    private void mockTasks() {
        List<Task> tasks = new ArrayList<>();
        tasks.add(new Task(this));
        final String tasks1 = "tasks";
        putObject(tasks1, tasks);
    }

    private void prepareNewTask() {
        TaskListActivity.getTasks().add(new Task(this));
        Collections.sort(TaskListActivity.getTasks());
        setTaskIndex(0);
        EditText view = findViewById(R.id.sv_task_name);
        ServiceUtils.highlightEditView(view);
    }

    private static void setTaskIndex(int i) {
        taskIndex = i;
    }

    private boolean indexOverflows() {
        return taskIndex >= TaskListActivity.getTasks().size();
    }

    private int getLastIndexOfDataSet() {
        return TaskListActivity.getTasks().size() - 1;
    }

    private Task getTask() {
        return TaskListActivity.getTasks().get(taskIndex);
    }

    private void bindTaskNameView(Task task) {
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
                    getTask().delete();
                    TaskListActivity.getTasks().remove(taskIndex);
                    start(this, taskIndex);
                })
                .addMember(R.id.sv_cancel_changes, (Runnable) () -> {
                    getTask().clearChanged();
                    finish();
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
        setTaskIndex(taskPosition);
        context.startActivity(starter);
    }

    @Override
    public void saveInitialState() {
        putObject(INITIAL_TASK_STATE, getTask());
    }
}

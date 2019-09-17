package com.example.kbluue_.unnamedtaskapp.Activities;

import android.content.Context;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kbluue_.unnamedtaskapp.BaseAdapters.TaskAdapter;
import com.example.kbluue_.unnamedtaskapp.Interfaces.ClickableAction;
import com.example.kbluue_.unnamedtaskapp.Interfaces.HasButtons;
import com.example.kbluue_.unnamedtaskapp.Interfaces.HasMenu;
import com.example.kbluue_.unnamedtaskapp.Interfaces.HasRecyclerView;
import com.example.kbluue_.unnamedtaskapp.Models.StorableObject;
import com.example.kbluue_.unnamedtaskapp.Models.Task;
import com.example.kbluue_.unnamedtaskapp.R;
import com.example.kbluue_.unnamedtaskapp.Utils.BaseActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import static com.example.kbluue_.unnamedtaskapp.Utils.ProcessStore.getObject;
import static com.example.kbluue_.unnamedtaskapp.Utils.ProcessStore.putObject;

public class TaskListActivity extends BaseActivity implements HasButtons, HasMenu, HasRecyclerView {

    private static final String TASKS = "tasks";
    private static Context context;
    private TaskAdapter taskAdapter;

    @Override
    protected void onPause() {
        getAdapter().finish();
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.layout_task_list;
    }

    @Override
    protected void init() {
        context = this.getApplicationContext();
        final List<Task> preSavedTasks = loadTasks();
        putObject(TASKS, preSavedTasks);
    }

    private List<Task> loadTasks(){
        Set<String> taskIds = StorableObject.getPref(this)
                .getStringSet("taskIds", null);
        List<Task> tasks = new ArrayList<>();
        if (taskIds != null) {
            for (String id : taskIds){
                Task task = Task.getInstance(this, id);
                tasks.add(task);
            }
            Collections.sort(tasks);
        }
        return tasks;
    }

    @Override
    public List<ClickableAction> getButtonActions() {
        return new ClickableAction.Factory()
                .addMember(R.id.add_task,
                        (Runnable) () -> SingleTaskActivity.start(this,-1))
                .deliver();
    }

    @Override
    public int getViewRes() {
        return R.id.tasks_rv;
    }

    @Override
    public TaskAdapter getAdapter() {
        if (taskAdapter == null) {
            taskAdapter = new TaskAdapter(getTasks());
        }
        return taskAdapter;
    }

    public void setTaskAdapter(TaskAdapter taskAdapter) {
        this.taskAdapter = taskAdapter;
    }

    @Override
    public RecyclerView.LayoutManager getLayoutManager() {
        return new LinearLayoutManager(this);
    }

    @Override
    public int setMenuId() {
        return R.menu.for_task_list;
    }

    @Override
    public List<ClickableAction> setMenuActions() {
        return null;
    }

    public static List<Task> getTasks() {
        return getObject(TASKS, ArrayList.class);
    }
}

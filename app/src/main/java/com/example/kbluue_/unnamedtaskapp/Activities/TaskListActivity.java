package com.example.kbluue_.unnamedtaskapp.Activities;

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
import com.example.kbluue_.unnamedtaskapp.Utils.CustomList;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import static com.example.kbluue_.unnamedtaskapp.Utils.ProcessStore.getObject;
import static com.example.kbluue_.unnamedtaskapp.Utils.ProcessStore.putObject;

public class TaskListActivity extends BaseActivity implements HasButtons, HasMenu, HasRecyclerView {

    private static final String TASKS = "tasks";

    @Override
    protected void onPause() {
//        TaskAdapter.saveTask();
        super.onPause();
    }

    @Override
    protected void onDestroy() {
//        TaskAdapter.saveTask();
        super.onDestroy();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.layout_task_list;
    }

    @Override
    protected void init() {
        putObject(TASKS, loadTasks());
    }

    private CustomList<Task> loadTasks(){
        Set<String> taskIds = StorableObject.getPref(this)
                .getStringSet("taskIds", null);
        CustomList<Task> tasks = new CustomList<>();
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
    public RecyclerView.Adapter getAdapter() {
        return new TaskAdapter(getTasks());
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

    public static CustomList<Task> getTasks() {
        return getObject(TASKS, CustomList.class);
    }
}

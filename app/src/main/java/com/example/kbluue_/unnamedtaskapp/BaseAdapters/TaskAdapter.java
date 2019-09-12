package com.example.kbluue_.unnamedtaskapp.BaseAdapters;

import android.content.Context;

import androidx.annotation.NonNull;

import com.example.kbluue_.unnamedtaskapp.Activities.SingleTaskActivity;
import com.example.kbluue_.unnamedtaskapp.Models.Task;
import com.example.kbluue_.unnamedtaskapp.R;
import com.example.kbluue_.unnamedtaskapp.Utils.BaseAdapter;
import com.example.kbluue_.unnamedtaskapp.Utils.ViewConfig;

import java.util.List;

public class TaskAdapter extends BaseAdapter {

    public TaskAdapter(@NonNull List dataSet) {
        super(dataSet);
        setChildClickable(true);
        setChildOnClickListener(v -> {
            int viewPosition = (int) v.getTag();
            final Context viewContext = v.getContext();
            SingleTaskActivity.start(viewContext, viewPosition);
        });
    }

    @Override
    public int getChildViewRes() {
        return R.layout.view_task;
    }

    @Override
    public void bindChild(ViewConfig config, Object childData) {
        Task task = (Task) childData;
        config.bind(R.id.task_name, task.getName())
                .bind(R.id.task_state, task.isDone() ? R.drawable.ic_done : R.drawable.ic_not_done)
                .bind(R.id.sub_task_count, task.getCompletedCount())
                .bind(R.id.task_timestamp, task.getLastUpdated().print())
                .bind(R.id.task_delete, R.drawable.ic_delete_color)
                .addOnClickListener(R.id.task_state, v -> {
                    task.toggleDone();
                    onDataSetChanged();
                })
                .addOnClickListener(R.id.task_delete, v -> {
                    task.delete();
                    removeFromDataSet(task);
                    onDataSetChanged();
                });
    }

    @Override
    protected void onFinalize() {

    }
}

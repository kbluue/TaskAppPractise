package com.example.kbluue_.unnamedtaskapp.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kbluue_.unnamedtaskapp.Activities.SingleTaskActivity;
import com.example.kbluue_.unnamedtaskapp.Models.Task;
import com.example.kbluue_.unnamedtaskapp.R;
import com.example.kbluue_.unnamedtaskapp.Utils.ViewConfig;

import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskVH>{

    public static List<Task> tasks;

    @NonNull
    @Override
    public TaskVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_task, parent, false);
        return new TaskVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskVH holder, int position) {
        holder.bind(tasks.get(position));
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

    public class TaskVH extends RecyclerView.ViewHolder {

        public TaskVH(@NonNull View itemView) {
            super(itemView);
        }

        public void bind(Task task){
            ViewConfig.getInstance(itemView)
                    .bind(R.id.task_name, task.getName())
                    .bind(R.id.task_state, task.isDone() ? R.drawable.ic_done : R.drawable.ic_not_done)
                    .bind(R.id.sub_task_count, task.getActiveCount())
                    .bind(R.id.task_timestamp, task.getLastUpdated().print("hh:mm"))
                    .bind(R.id.task_delete, R.drawable.ic_delete_color)
                    .addOnClickListener(R.id.task_state, v -> {
                        task.toggleDone();
                        notifyDataSetChanged();
                    })
                    .addOnClickListener(R.id.task_delete, v -> {
                        tasks.remove(task);
                        notifyDataSetChanged();
                    });
            itemView.setClickable(true);
            addOnClickListener(task);
        }

        private void addOnClickListener(Task task){
            itemView.setOnClickListener(v -> SingleTaskActivity.start(v.getContext(), tasks.indexOf(task)));
        }
    }
}

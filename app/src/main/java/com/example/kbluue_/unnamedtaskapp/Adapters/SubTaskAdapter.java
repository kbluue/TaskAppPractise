package com.example.kbluue_.unnamedtaskapp.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kbluue_.unnamedtaskapp.Models.SubTask;
import com.example.kbluue_.unnamedtaskapp.Models.Task;
import com.example.kbluue_.unnamedtaskapp.R;
import com.example.kbluue_.unnamedtaskapp.Utils.ViewBinder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SubTaskAdapter extends RecyclerView.Adapter<SubTaskAdapter.SubTaskVH> {

    public static List<SubTask> subTasks;

    public SubTaskAdapter(int taskPosition){
        updateSubtaskList(taskPosition);
    }

    @NonNull
    @Override
    public SubTaskVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_sub_task, parent, false);
        return new SubTaskVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SubTaskVH holder, int position) {
        holder.bind(subTasks.get(position));
    }

    @Override
    public int getItemCount() {
        return subTasks.size();
    }

    public void updateSubtaskList(int taskPosition){
        Task task = TaskAdapter.tasks.get(taskPosition);
        subTasks = task.getChildren(SubTask.class);
        if (subTasks == null) {
            subTasks = new ArrayList<>();
        }
        subTasks.add(new SubTask());
        refresh();
    }

    private void refresh(){
        Collections.sort(subTasks);
        notifyDataSetChanged();
    }

    public class SubTaskVH extends RecyclerView.ViewHolder{

        public SubTaskVH(@NonNull View itemView) {
            super(itemView);
        }

        public void bind(SubTask subTask){
            ViewBinder binder = ViewBinder.getInstance(itemView)
                    .bind(R.id.btn_left, subTask.getId() != null
                            ? subTask.isDone() ? R.drawable.ic_done : R.drawable.ic_not_done : R.drawable.ic_add)
                    .bind(R.id.btn_right, subTask.getId() != null ? R.drawable.ic_delete_color : 0)
                    .bind(R.id.sub_task, subTask.getName())
                    .addOnClickListener(R.id.btn_left, v ->
                            Toast.makeText(itemView.getContext(), "Link", Toast.LENGTH_SHORT).show())
                    .addOnClickListener(R.id.btn_right, subTask.getId() == null
                            ? v -> {}
                            : v -> {});
        }
    }
}

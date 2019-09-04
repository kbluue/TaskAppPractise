package com.example.kbluue_.unnamedtaskapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kbluue_.unnamedtaskapp.Activities.SingleTaskActivity;
import com.example.kbluue_.unnamedtaskapp.Models.SubTask;
import com.example.kbluue_.unnamedtaskapp.Models.Task;
import com.example.kbluue_.unnamedtaskapp.R;
import com.example.kbluue_.unnamedtaskapp.Utils.ServiceUtils;
import com.example.kbluue_.unnamedtaskapp.Utils.ViewConfig;

import java.util.Arrays;

public class SubTaskAdapter extends RecyclerView.Adapter<SubTaskAdapter.SubTaskVH> {

    private static SubTask[] subTasks;
    private RecyclerView.LayoutManager manager;

    public SubTaskAdapter(RecyclerView.LayoutManager manager){
        this.manager = manager;
        updateSubtaskList();
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
        holder.setSubTask(subTasks[position]);
        holder.bind();
        if (position == 1){
            holder.itemView.setFocusable(true);
        }
    }

    @Override
    public int getItemCount() {
        return subTasks.length;
    }

    private Task getTask(){
        return TaskAdapter.tasks.get(SingleTaskActivity.taskIndex);
    }

    public void updateSubtaskList(){
        try {
            subTasks = getTask().getChildren();
        } catch (ArrayIndexOutOfBoundsException ignored){}
        refresh();
    }

    private void refresh(){
        Arrays.sort(subTasks);
        notifyDataSetChanged();
    }

    public class SubTaskVH extends RecyclerView.ViewHolder{

        Context context;
        ViewConfig config;
        private SubTask subTask;

        public SubTaskVH(@NonNull View itemView) {
            super(itemView);
            context = itemView.getContext();
            config = ViewConfig.getInstance(itemView);
        }

        public SubTask getSubTask() {
            getTask().setChanged();
            return subTask;
        }

        public void setSubTask(SubTask subTask) {
            this.subTask = subTask;
        }

        public void bind(){
            config.getView(R.id.sub_task).setVisibility(getSubTask().getId() == null ? View.INVISIBLE : View.VISIBLE);
            config.bind(R.id.btn_left, getSubTask().getId() != null
                            ? getSubTask().isDone() ? R.drawable.ic_done : R.drawable.ic_not_done : R.drawable.ic_add)
                    .bind(R.id.btn_right, getSubTask().getId() != null ? R.drawable.ic_delete_color : 0)
                    .bind(R.id.sub_task, getSubTask().getName())
                    .addOnClickListener(R.id.btn_left, getSubTask().getId() == null
                            ? v -> {
                        getTask().addChild(new SubTask(context));
                        updateSubtaskList();
                        itemView.postDelayed( () -> {
                            EditText view = manager.findViewByPosition(1)
                                .findViewById(R.id.sub_task);
                            view.requestFocus();
                            view.requestFocus();
                            ServiceUtils.showKeyboard(view);
                        },
                                200);
                    }
                            : v -> {
                        getSubTask().toggleDone();
                        refresh();
                    })
                    .addOnClickListener(R.id.btn_right, getSubTask().getId() == null
                            ? v -> {}
                            : v -> {
                        getTask().removeChild(getSubTask());
                        updateSubtaskList();})
                    .addTextListener(R.id.sub_task, (s, start, before, count) -> getSubTask().setName(s.toString()));
        }
    }
}

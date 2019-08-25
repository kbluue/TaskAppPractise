package com.example.kbluue_.unnamedtaskapp.Adapters;

import android.content.Context;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kbluue_.unnamedtaskapp.Models.SubTask;
import com.example.kbluue_.unnamedtaskapp.Models.Task;
import com.example.kbluue_.unnamedtaskapp.R;
import com.example.kbluue_.unnamedtaskapp.Utils.ArraysUtil;
import com.example.kbluue_.unnamedtaskapp.Utils.ViewConfig;

import java.util.Arrays;

public class SubTaskAdapter extends RecyclerView.Adapter<SubTaskAdapter.SubTaskVH> {

    private static SubTask[] subTasks;
    private int index;

    public SubTaskAdapter(int index){
        this.index = index;
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
    }

    @Override
    public int getItemCount() {
        return subTasks.length;
    }

    private Task getTask(){
        return TaskAdapter.tasks.get(index);
    }

    private void updateSubtaskList(){
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
            return subTask;
        }

        public void setSubTask(SubTask subTask) {
            this.subTask = subTask;
        }

        public void bind(){
            config.getView(R.id.sub_task).setVisibility(subTask.getId() == null ? View.INVISIBLE : View.VISIBLE);
            config.bind(R.id.btn_left, subTask.getId() != null
                            ? subTask.isDone() ? R.drawable.ic_done : R.drawable.ic_not_done : R.drawable.ic_add)
                    .bind(R.id.btn_right, subTask.getId() != null ? R.drawable.ic_delete_color : 0)
                    .bind(R.id.sub_task, subTask.getName())
                    .addOnClickListener(R.id.btn_left, subTask.getId() == null
                            ? v -> makeEditable()
                            : v -> {
                        subTask.toggleDone();
                        refresh();
                    })
                    .addOnClickListener(R.id.btn_right, subTask.getId() == null
                            ? v -> {}
                            : v -> {
                        ArraysUtil.remove(getTask().getChildren(), subTask);
                        updateSubtaskList();})
                    .addOnClickListener(R.id.sub_task, v -> makeEditable());
        }

        private void makeEditable(){
            config.addOnClickListener(R.id.sub_task, v -> {});
            EditText editText = (EditText) config.getView(R.id.sub_task);
            if (subTask.getId() == null) {
                subTask = new SubTask(context);
                editText.setInputType(InputType.TYPE_CLASS_TEXT);
                editText.selectAll();
                editText.setVisibility(View.VISIBLE);
            }
            editText.setFocusableInTouchMode(true);
            config.bind(R.id.btn_left, R.drawable.ic_clear)
                    .bind(R.id.btn_right, R.drawable.ic_add)
                    .bind(R.id.sub_task, subTask.getName())
                    .addOnClickListener(R.id.btn_left, v -> {
                        config.bind(R.id.sub_task, "");
                    })
                    .addOnClickListener(R.id.btn_right, v -> {
                        String newName = ((EditText) config.getView(R.id.sub_task)).getText().toString();
                        subTask.setName(newName);
                        System.out.println(getTask().getChildren().length + " : xIOx");
                        getTask().addChild(subTask);
                        System.out.println(getTask().getChildren().length + " : xIOx");
                        Toast.makeText(context, "New SubTask Created", Toast.LENGTH_SHORT).show();
                        updateSubtaskList();
                    });
        }
    }
}

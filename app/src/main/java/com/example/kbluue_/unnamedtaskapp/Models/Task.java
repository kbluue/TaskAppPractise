package com.example.kbluue_.unnamedtaskapp.Models;

import android.content.Context;

import androidx.annotation.NonNull;

import com.example.kbluue_.unnamedtaskapp.Interfaces.HasChildren;

import java.util.HashSet;
import java.util.Set;

public class Task extends Memo implements HasChildren {

    private boolean done;
    private SubTask[] children;

    public Task(){}

    public Task(Context context){
        super(context, StringConstants.TASK);
        addChild(new SubTask());
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
        setChanged();
    }

    @Override
    public SubTask[] getChildren() {
        return children;
    }

    @Override
    public void setChildren(Memo[] children) {
        this.children = (SubTask[]) children;
        setChanged();
    }

    @Override
    public SubTask getChild(int index) {
        if (children != null) {
            return getChildren()[index];
        } else {
            return null;
        }
    }

    @Override
    public void addChild(@NonNull Memo child) {
        if (child.getLastUpdated() != null) {
            setLastUpdated(child.getLastUpdated());
        }

        int len = 0;
        if (children != null) {
            len = children.length;
        }

        SubTask[] subTasks = new SubTask[len + 1];
        for (int i = 0; i < len; i++) {
            if (child.equals(getChild(i))){
                children[i] = (SubTask) child;
                return;
            }
            subTasks[i] = getChild(i);
        }
        subTasks[len] = (SubTask) child;
        setChildren(subTasks);
    }

    @Override
    public <T extends Memo> void removeChild(T child) {
        int len = 0, index = 0;
        if (children != null) {
            len = children.length - 1;
        }
        SubTask[] subTasks = new SubTask[len];
        for (SubTask subTask : getChildren()){
            if (!subTask.equals(child)){
                try {
                    subTasks[index++] = subTask;
                } catch (ArrayIndexOutOfBoundsException e){
                    return;
                }
            }
        }
        setChildren(subTasks);
    }

    @Override
    public void save() {
        updateTaskIds(true);
        super.save();
    }

    @Override
    public void delete() {
        updateTaskIds(false);
        super.delete();
    }

    @Override
    public int compareTo(Object o) {
        if (o instanceof Task) {
            Task task = (Task) o;
            if (task.isDone() ^ this.isDone()){
                return task.isDone() ? -1 : 1;
            }
        }
        return super.compareTo(o);
    }

    public static Task getInstance(Context context, String id) {
        return (Task) getInstance(context, id, Task.class);
    }

    public void toggleDone(){
        setDone(!isDone());
    }

    public String getCompletedCount(){
        if (getChildren() == null){
            return StringConstants.EMPTY_STRING;
        } else {
            int completed = 0;
            for (SubTask task : getChildren()){
                if (task.isDone()){
                    completed++;
                }
            }
            return completed + StringConstants.FORWARD_SLASH + (getChildren().length - 1);
        }
    }

    private void updateTaskIds(boolean add){
        Set<String> taskIds = StorableObject.getPref(context)
                .getStringSet(StringConstants.TASK_IDS, new HashSet<>());
        if (add) {
            taskIds.add(getId());
        } else {
            taskIds.remove(getId());
        }
        StorableObject.getPref(context)
                .edit()
                .putStringSet(StringConstants.TASK_IDS, taskIds)
                .apply();
    }
}

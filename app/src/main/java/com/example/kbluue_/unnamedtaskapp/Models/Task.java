package com.example.kbluue_.unnamedtaskapp.Models;

import android.content.Context;

import java.util.HashSet;
import java.util.Set;

public class Task extends Memo {

    private boolean done;

    public Task(){}

    public Task(Context context){
        super(context, "Task");
        setParent(true);
        addChild(new SubTask());
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    @Override
    public <T extends StorableObject> Task addChild(T child) {
        if (child instanceof SubTask) {
            SubTask subTask = (SubTask) child;
            return (Task) super.addChild(child);
        } else {
            return null;
        }
    }

    @Override
    public void save(Context context) {
        Set<String> taskIds = StorableObject.getPref(context)
                .getStringSet("taskIds", new HashSet<>());
        taskIds.add(getId());
        StorableObject.getPref(context)
                .edit()
                .putStringSet("taskIds", taskIds)
                .apply();
        super.save(context);
    }

    public static Task getInstance(Context context, String id) {
        return (Task) getInstance(context, id, Task.class);
    }

    public void toggleDone(){
        setDone(!isDone());
    }

    public String getCompletedCount(){
        if (getChildren() == null){
            return "";
        } else {
            int completed = 0;
            for (SubTask task : getChildren()){
                if (task.isDone()){
                    completed++;
                }
            }
            return completed + "/" + (getChildren().length - 1);
        }
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

    @Override
    public SubTask[] getChildren() {
        return super.getChildren();
    }
}

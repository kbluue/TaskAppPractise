package com.example.kbluue_.unnamedtaskapp.Models;

import android.content.Context;

import com.example.kbluue_.unnamedtaskapp.Interfaces.HasChildren;

import java.util.HashSet;
import java.util.Set;

public class Task extends Memo implements HasChildren {

    private boolean done;
    private SubTask[] children;

    public Task(){}

    public Task(Context context){
        super(context, "Task");
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
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
        return children;
    }

    public Task setChildren(SubTask[] children) {
        this.children = children;
        return this;
    }

    public void addChild(SubTask child) {
        int len = 0;
        if (children != null) {
            len = children.length;
        }
        SubTask[] objects = new SubTask[len + 1];
        for (int i = 0; i < len; i++) {
            objects[i] = children[i];
        }
        objects[len] = child;
        this.children = objects;
    }

    @Override
    public SubTask getChild(int index) {
        if (children != null) {
            return children[index];
        }
        return children[index];
    }
}

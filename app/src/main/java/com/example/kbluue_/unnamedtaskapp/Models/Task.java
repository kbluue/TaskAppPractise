package com.example.kbluue_.unnamedtaskapp.Models;

import android.content.Context;

import static com.example.kbluue_.unnamedtaskapp.Interfaces.Prefix.TASK;

public class Task extends Memo {

    private boolean done;

    public Task(){}

    public Task(Context context){
        super(context, TASK);
        setName("Task #" + getId());
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
    public Task addChild(Memo child) {
        if (child instanceof SubTask) {
            SubTask subTask = (SubTask) child;
            return (Task) super.addChild(subTask);
        } else {
            return this;
        }
    }

    public void toggleDone(){
        setDone(!isDone());
    }

    public String getActiveCount(){
        if (getChildren() == null || getChildren().isEmpty()){
            return "";
        } else {
            int activeCount = 0;
            for (Task task : getChildren(Task.class)){
                if (!task.isDone()){
                    activeCount++;
                }
            }
            return (activeCount - 1) + "/" + (getChildren().size() - 1);
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
}

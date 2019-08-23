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
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
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
            return activeCount + "/" + getChildren().size();
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

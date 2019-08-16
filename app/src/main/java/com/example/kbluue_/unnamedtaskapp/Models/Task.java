package com.example.kbluue_.unnamedtaskapp.Models;

import android.content.Context;

import static com.example.kbluue_.unnamedtaskapp.Interfaces.Prefix.TASK;

public class Task extends Memo {

    private boolean done;

    public Task(){}

    public Task(Context context){
        super(context, TASK);
        setParent(true);
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }
}

package com.example.kbluue_.unnamedtaskapp.Models;

import android.content.Context;

public class SubTask extends Task {

    public SubTask(){}

    public SubTask(Context context){
        super(context);
        setName("Subtask #" + getId());
        setParent(false);
    }
}

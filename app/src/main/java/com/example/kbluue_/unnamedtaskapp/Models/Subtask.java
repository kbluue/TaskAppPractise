package com.example.kbluue_.unnamedtaskapp.Models;

import android.content.Context;

public class Subtask extends Task {

    public Subtask(){}

    public Subtask(Context context){
        super(context);
        setParent(false);
    }
}

package com.example.kbluue_.unnamedtaskapp.Models;

import android.content.Context;
import android.widget.Toast;

public class SubTask extends Task {

    public SubTask(){}

    public SubTask(Context context){
        super(context);
        Toast.makeText(context, "test: " + getId(), Toast.LENGTH_SHORT).show();
    }
}

package com.example.kbluue_.unnamedtaskapp.Interfaces;

import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kbluue_.unnamedtaskapp.Utils.SwipeHandler;

public interface HasSwipeHandler {

    SwipeHandler getSwipeHandler();

    default void attachToRecylerView(RecyclerView view){
        new ItemTouchHelper(getSwipeHandler())
                .attachToRecyclerView(view);
    }
}

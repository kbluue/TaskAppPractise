package com.example.kbluue_.unnamedtaskapp.Utils;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kbluue_.unnamedtaskapp.Interfaces.ClickableAction;

import java.util.List;

public class SwipeHandler extends ItemTouchHelper.SimpleCallback {

    private static final String TAG = "SwipeHandler";

    private final RecyclerView.Adapter adapter;
    private List<ClickableAction> actions;

    public SwipeHandler(RecyclerView.Adapter adapter) {
        super(0, 0);
        this.adapter = adapter;
    }

    public SwipeHandler(RecyclerView.Adapter adapter, List<ClickableAction> actions) {
        super(0, 0);
        this.adapter = adapter;
        this.actions = actions;
    }

    public RecyclerView.Adapter getAdapter() {
        return adapter;
    }

    public void addAction(ClickableAction action){
        actions.add(action);
    }

    public void addAction(int direction, Runnable action){
        ClickableAction clickableAction = new ClickableAction(direction, action, false);
        actions.add(clickableAction);
    }

    public void remooveAction(int direction){
        actions.remove(direction);
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
        Runnable action;
        if (actions == null) {
            action = () -> Log.d(TAG, "onSwiped: Swipe actions not registered");
        } else {
            action = ClickableAction.findActionById(actions, direction);
        }
        action.run();
    }
}

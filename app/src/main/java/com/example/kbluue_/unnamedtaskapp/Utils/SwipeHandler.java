package com.example.kbluue_.unnamedtaskapp.Utils;

import android.graphics.Canvas;
import android.util.Log;
import android.util.SparseArray;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

public class SwipeHandler extends ItemTouchHelper.SimpleCallback {

    private static final String TAG = "SwipeHandler";
    private static final int swipeDirs = ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT;

    private final RecyclerView.Adapter adapter;
    private final SparseArray<Runnable> actions;

    public SwipeHandler(RecyclerView.Adapter adapter) {
        super(0, swipeDirs);
        this.adapter = adapter;
        final int maxCapacity = 2;
        actions = new SparseArray<>(maxCapacity);
    }

    public SwipeHandler setRightSwipeAction(Runnable action){
        actions.put(ItemTouchHelper.RIGHT, action);
        return this;
    }

    public SwipeHandler setLeftSwipeAction(Runnable action){
        actions.put(ItemTouchHelper.LEFT, action);
        return this;
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
            action = actions.get(direction);
        }
        action.run();
    }

    @Override
    public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
    }
}

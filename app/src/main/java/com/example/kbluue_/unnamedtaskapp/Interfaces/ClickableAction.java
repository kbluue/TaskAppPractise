package com.example.kbluue_.unnamedtaskapp.Interfaces;

import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class ClickableAction {

    private final int viewId;
    private final Runnable action;
    private final boolean isAdmin;

    private static final String TAG = "ClickableAction";

    public ClickableAction(int viewId, Runnable action, boolean isAdmin) {
        this.viewId = viewId;
        this.action = action;
        this.isAdmin = isAdmin;
    }

    public ClickableAction(int id){
        this.viewId = id;
        this.action = null;
        this.isAdmin = false;
    }

    public int getViewId() {
        return viewId;
    }

    public Runnable getAction() {
        return action;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public static Runnable findActionByView(List<ClickableAction> list, View view) {
        return findActionById(list, view.getId());
    }

    public static Runnable findActionById(List<ClickableAction> list, int id) {
        ClickableAction action = new ClickableAction(id);
        if (list.contains(action)) {
            int index = list.indexOf(action);
            action = list.get(index);
            return action.getAction();
        } else {
            return () -> Log.d(TAG, "findActionById: View (or Id) not registered");
        }
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (obj instanceof ClickableAction){
            return ((ClickableAction)obj).getViewId() == this.getViewId();
        }
        return super.equals(obj);
    }

    public static class Factory implements ListFactory {

        private static List<ClickableAction> actionList = new ArrayList<>();

        @Override
        public Factory addMember(Object... initialVariables) {
            if (getConstructorType(initialVariables) == 3){
                int viewId = (int) initialVariables[0];
                Runnable action = (Runnable) initialVariables[1];
                boolean isAdmin = (boolean) initialVariables[2];
                actionList.add(new ClickableAction(viewId, action, isAdmin));
            } else if (getConstructorType(initialVariables) == 2){
                int viewId = (int) initialVariables[0];
                Runnable action = (Runnable) initialVariables[1];
                actionList.add(new ClickableAction(viewId, action, false));
            } else if (getConstructorType(initialVariables) == 1){
                int viewId = (int) initialVariables[0];
                actionList.add(new ClickableAction(viewId));
            }
            return this;
        }

        @Override
        public int getConstructorType(Object... initialVariables) {
            return initialVariables.length;
        }

        @Override
        public List<ClickableAction> deliver() {
            return actionList;
        }
    }
}

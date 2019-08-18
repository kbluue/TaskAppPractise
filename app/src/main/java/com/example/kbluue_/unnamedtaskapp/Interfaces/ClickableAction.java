package com.example.kbluue_.unnamedtaskapp.Interfaces;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class ClickableAction {

    private int viewId;
    private Runnable action;
    private boolean isAdmin;

    public ClickableAction(int viewId, Runnable action, boolean isAdmin) {
        this.viewId = viewId;
        this.action = action;
        this.isAdmin = isAdmin;
    }

    public ClickableAction(int id){
        this.viewId = id;
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

    @Override
    public boolean equals(@Nullable Object obj) {
        if (obj instanceof ClickableAction){
            return ((ClickableAction)obj).getViewId() == this.getViewId();
        }
        return super.equals(obj);
    }

    public static class Delegate implements ListFactory {

        public static List<ClickableAction> actionList = new ArrayList<>();

        @Override
        public Delegate addMember(Object... initialVariables) {
            if (getConstructorType(initialVariables) == 0){
                int viewId = (int) initialVariables[0];
                Runnable action = (Runnable) initialVariables[1];
                boolean isAdmin = (boolean) initialVariables[2];
                actionList.add(new ClickableAction(viewId, action, isAdmin));
            } else if (getConstructorType(initialVariables) == 1){
                int viewId = (int) initialVariables[0];
                actionList.add(new ClickableAction(viewId));
            }
            return this;
        }

        @Override
        public int getConstructorType(Object... initialVariables) {
            if (initialVariables.length == 3
                    && initialVariables[0] instanceof Integer
                    && initialVariables[1] instanceof Runnable
                    && initialVariables[2] instanceof Boolean) {
                return 0;
            } else if (initialVariables.length == 1
                    && initialVariables[0] instanceof Integer) {
                return 1;
            } else {
                return -1;
            }
        }

        @Override
        public List<ClickableAction> deliver() {
            return actionList;
        }
    }
}

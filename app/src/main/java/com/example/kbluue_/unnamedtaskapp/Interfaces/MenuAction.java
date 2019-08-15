package com.example.kbluue_.unnamedtaskapp.Interfaces;

import android.view.MenuItem;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class MenuAction {

    private int menuId;
    private Runnable menuAction;
    private boolean isAdmin;

    public MenuAction(int menuId, Runnable menuAction, boolean isAdmin) {
        this.menuId = menuId;
        this.menuAction = menuAction;
        this.isAdmin = isAdmin;
    }

    public MenuAction(MenuItem menuItem){
        this.menuId = menuItem.getItemId();
    }

    public int getMenuId() {
        return menuId;
    }

    public Runnable getMenuAction() {
        return menuAction;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (obj instanceof MenuAction){
            return ((MenuAction)obj).getMenuId() == this.getMenuId();
        }
        return super.equals(obj);
    }

    public static class Delegate implements Delegator {

        public static List<MenuAction> actionList = new ArrayList<>();

        @Override
        public Delegate addMember(Object... initialVariables) {
            if (getConstructorType(initialVariables) == 0){
                int menuId = (int) initialVariables[0];
                Runnable menuAction = (Runnable) initialVariables[1];
                boolean isAdmin = (boolean) initialVariables[2];
                actionList.add(new MenuAction(menuId, menuAction, isAdmin));
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
            } else {
                return -1;
            }
        }

        @Override
        public List<MenuAction> deliver() {
            return actionList;
        }
    }
}

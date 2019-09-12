package com.example.kbluue_.unnamedtaskapp.Interfaces;

import android.view.Menu;

import java.util.List;

public interface HasMenu {

    int setMenuId();

    List<ClickableAction> setMenuActions();

    default void hideAdminMenu(Menu menu){
        List<ClickableAction> actions = setMenuActions();
        for (ClickableAction action : actions){
            if (action.isAdmin()){
                menu.findItem(action.getActionId())
                        .setVisible(false);
            }
        }
    }
}

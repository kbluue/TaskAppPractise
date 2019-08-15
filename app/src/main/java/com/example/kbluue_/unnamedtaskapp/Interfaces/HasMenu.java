package com.example.kbluue_.unnamedtaskapp.Interfaces;

import android.view.Menu;

import java.util.List;

public interface HasMenu {

    int setMenuId();

    List<MenuAction> setMenuActions();

    default void hideAdminMenu(Menu menu){
        List<MenuAction> actions = setMenuActions();
        for (MenuAction action : actions){
            if (action.isAdmin()){
                menu.findItem(action.getMenuId())
                        .setVisible(false);
            }
        }
    }
}

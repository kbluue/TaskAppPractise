package com.example.kbluue_.unnamedtaskapp.Interfaces;

import android.view.View;

import com.example.kbluue_.unnamedtaskapp.Utils.BaseActivity;

import java.util.List;

/**
 * Created by _kbluue_ on 8/2/2019.
 *
 *
 */

public interface HasButtons {

    List<ClickableAction> getButtonActions();

    default void hideAdminButtons(){
        List<ClickableAction> actions = getButtonActions();
        for (ClickableAction action : actions){
            if (action.isAdmin()){
                ((BaseActivity) this)
                        .findViewById(action.getViewId())
                        .setVisibility(View.GONE);
            }
        }
    }

}

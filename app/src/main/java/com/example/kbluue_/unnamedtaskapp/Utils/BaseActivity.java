package com.example.kbluue_.unnamedtaskapp.Utils;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.kbluue_.unnamedtaskapp.Interfaces.HasMenu;
import com.example.kbluue_.unnamedtaskapp.Interfaces.MenuAction;

import java.util.List;

/**
 * Created by _kbluue_ on 8/2/2019.
 */

public abstract class BaseActivity extends AppCompatActivity {

    private int menuRes;
    private boolean isAdmin = true;
    private List<MenuAction> menuActions;

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setLayoutId());
        init();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (this instanceof HasMenu){
            HasMenu menuActivity = (HasMenu) this;
            menuRes = menuActivity.setMenuId();
            menuActions = menuActivity.setMenuActions();
            getMenuInflater().inflate(menuRes, menu);
            if (!isAdmin){
                menuActivity.hideAdminMenu(menu);
            }
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        MenuAction menuAction = new MenuAction(item);
        if (menuActions.contains(menuAction)){
            int index = menuActions.indexOf(menuAction);
            menuAction = menuActions.get(index);
            Runnable action = menuAction.getMenuAction();
            if (action != null){
                action.run();
            } else {
                Toast.makeText(this, "Menu action not defined", Toast.LENGTH_SHORT).show();
            }
            return true;
        } else {
            return false;
        }
    }

    protected abstract int setLayoutId();

    protected abstract void init();
}

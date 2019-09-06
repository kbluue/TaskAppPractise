package com.example.kbluue_.unnamedtaskapp.Utils;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kbluue_.unnamedtaskapp.Interfaces.ClickableAction;
import com.example.kbluue_.unnamedtaskapp.Interfaces.HasButtons;
import com.example.kbluue_.unnamedtaskapp.Interfaces.HasInitialState;
import com.example.kbluue_.unnamedtaskapp.Interfaces.HasMenu;
import com.example.kbluue_.unnamedtaskapp.Interfaces.HasRecyclerView;

import java.util.List;

/**
 * Created by _kbluue_ on 8/2/2019.
 */

public abstract class BaseActivity extends AppCompatActivity {

    private int menuRes;
    private Menu menu;
    private boolean isAdmin = true;
    private List<ClickableAction> menuActions, buttonActions;
    private RecyclerView.Adapter baseAdapter;

    private static final String TAG = "BaseActivity";

    public Menu getMenu() {
        return menu;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public RecyclerView.Adapter getBaseAdapter() {
        return baseAdapter;
    }

    public void setBaseAdapter(RecyclerView.Adapter baseAdapter) {
        this.baseAdapter = baseAdapter;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        init();

        if (this instanceof HasButtons) {
            HasButtons hasButtons = (HasButtons) this;
            buttonActions = hasButtons.getButtonActions();
            if (!isAdmin) {
                hasButtons.hideAdminButtons();
            }
        }

        if (this instanceof HasInitialState) {
            HasInitialState hasInitialState = (HasInitialState) this;
            hasInitialState.saveInitialState();
        }

        if (this instanceof HasRecyclerView) {
            HasRecyclerView hasRecyclerView = (HasRecyclerView) this;
            hasRecyclerView.initRV(this);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (baseAdapter != null) {
            baseAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.menu = menu;

        if (this instanceof HasMenu) {
            HasMenu menuActivity = (HasMenu) this;
            menuRes = menuActivity.setMenuId();
            menuActions = menuActivity.setMenuActions();
            getMenuInflater().inflate(menuRes, menu);
            if (!isAdmin) {
                menuActivity.hideAdminMenu(menu);
            }
            assert getSupportActionBar() != null;
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        } else {
            Runnable action = findMenuActionById(item.getItemId());
            if (action == null) {
                Log.d(TAG, "onOptionsItemSelected: Menu action not defined");
                return false;
            } else {
                action.run();
                return true;
            }
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    public void onButtonPressed(View view) {
        Runnable runnable = findButtonActionById(view.getId());
        if (runnable == null) {
            Log.d(TAG, "onButtonPressed: Button action not defined");
        } else {
            runnable.run();
        }
    }

    private Runnable findActionById(List<ClickableAction> list, int id) {
        ClickableAction action = new ClickableAction(id);
        if (list.contains(action)) {
            int index = list.indexOf(action);
            action = list.get(index);
            return action.getAction();
        } else {
            return () -> Log.d(TAG, "findActionById: View not registered");
        }
    }

    public Runnable findMenuActionById(int id) {
        if (menuActions == null) {
            return () -> Log.d(TAG, "findMenuActionById: MenuActions not registered");
        } else {
            return findActionById(menuActions, id);
        }
    }

    public Runnable findButtonActionById(int id) {
        return findActionById(buttonActions, id);
    }

    protected abstract int getLayoutId();

    protected abstract void init();
}

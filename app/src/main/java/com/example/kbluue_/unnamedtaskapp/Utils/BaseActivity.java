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

import static com.example.kbluue_.unnamedtaskapp.Interfaces.ClickableAction.findActionById;
import static com.example.kbluue_.unnamedtaskapp.Interfaces.ClickableAction.findActionByView;

/**
 * Created by _kbluue_ on 8/2/2019.
 */

public abstract class BaseActivity extends AppCompatActivity {

    private int menuRes;
    private Menu menu;
    private boolean isAdmin = true;
    private List<ClickableAction> menuActions;
    private List<ClickableAction> buttonActions;
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

        if (this instanceof HasButtons)
            initButtonActions();

        if (this instanceof HasInitialState)
            initInitialState();

        if (this instanceof HasRecyclerView)
            initRecyclerView();
    }

    private void initButtonActions() {
        HasButtons hasButtons = (HasButtons) this;
        buttonActions = hasButtons.getButtonActions();
        if (!isAdmin) {
            hasButtons.hideAdminButtons();
        }
    }

    private void initInitialState() {
        HasInitialState hasInitialState = (HasInitialState) this;
        hasInitialState.saveInitialState();
    }

    public void initRecyclerView() {
        runOnUiThread(() -> {
            HasRecyclerView hasRecyclerView = (HasRecyclerView) this;
            hasRecyclerView.initRV(this);
        });
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
            Runnable action = findMenuAction(item);
            if (action == null) {
                Log.d(TAG, "onOptionsItemSelected: Menu action not defined");
                return false;
            } else {
                action.run();
                return true;
            }
        }
    }

    public void onButtonPressed(View view) {
        Runnable runnable = findButtonAction(view);
        if (runnable == null) {
            Log.d(TAG, "onButtonPressed: Button action not defined");
        } else {
            runnable.run();
        }
    }

    public Runnable findMenuAction(MenuItem menuItem) {
        if (menuActions == null) {
            return () -> Log.d(TAG, "findMenuActionById: MenuActions not registered");
        } else {
            return findActionById(menuActions, menuItem.getItemId());
        }
    }

    public Runnable findButtonAction(View view) {
        return findActionByView(buttonActions, view);
    }

    protected abstract int getLayoutId();

    protected abstract void init();
}

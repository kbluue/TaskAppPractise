package com.example.kbluue_.unnamedtaskapp.Utils;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kbluue_.unnamedtaskapp.Interfaces.ClickableAction;
import com.example.kbluue_.unnamedtaskapp.Interfaces.HasButtons;
import com.example.kbluue_.unnamedtaskapp.Interfaces.HasMenu;
import com.example.kbluue_.unnamedtaskapp.Interfaces.HasRecyclerView;
import com.example.kbluue_.unnamedtaskapp.R;

import java.util.List;

import static android.util.Log.wtf;

/**
 * Created by _kbluue_ on 8/2/2019.
 */

public abstract class BaseActivity extends AppCompatActivity {

    private int menuRes;
    private boolean isAdmin = true;
    private List<ClickableAction> menuActions, buttonActions;
    private RecyclerView.Adapter baseAdapter;

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
            if (!isAdmin){
                hasButtons.hideAdminButtons();
            }
        }

        if (this instanceof HasRecyclerView) {
            HasRecyclerView hasRecyclerView = (HasRecyclerView) this;
            hasRecyclerView.initRV(this);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (baseAdapter != null){
            baseAdapter.notifyDataSetChanged();
        }
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
            assert getSupportActionBar() != null;
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            onBackPressed();
            return true;
        } else {
            Runnable action = findMenuActionById(item.getItemId());
            if (action == null) {
                wtf(getString(R.string.app_name), "BaseActivity.onOptionsItemSelected: Action not defined");
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

    public void onButtonPressed(View view){
        Runnable runnable = findButtonActionById(view.getId());
        if (runnable == null) {
            wtf(getString(R.string.app_name), "BaseActivity.onButtonPressed: Button Action not defined");
        } else {
            runnable.run();
        }
    }

    private Runnable findActionById(List<ClickableAction> list, int id){
        ClickableAction action = new ClickableAction(id);
        if (list.contains(action)) {
            int index = list.indexOf(action);
            action = list.get(index);
            return action.getAction();
        } else {
            return () -> wtf(getString(R.string.app_name), "BaseActivity.findActionById: View not registered");
        }
    }

    public Runnable findMenuActionById(int id){
        if (menuActions == null) {
            return () -> wtf(getString(R.string.app_name), "BaseActivity.findMenuActionById: MenuActions not registered");
        } else {
            return findActionById(menuActions, id);
        }
    }

    public Runnable findButtonActionById(int id){
        return findActionById(buttonActions, id);
    }

    protected abstract int getLayoutId();

    protected abstract void init();
}

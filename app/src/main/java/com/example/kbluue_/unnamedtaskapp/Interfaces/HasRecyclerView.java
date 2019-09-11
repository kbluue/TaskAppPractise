package com.example.kbluue_.unnamedtaskapp.Interfaces;

import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.kbluue_.unnamedtaskapp.Utils.BaseActivity;

public interface HasRecyclerView {

    int getViewRes();

    RecyclerView.Adapter getAdapter();

    RecyclerView.LayoutManager getLayoutManager();

    default void initRV(BaseActivity host){
        RecyclerView view = host.findViewById(getViewRes());
        setBaseAdapter(host);
        view.setAdapter(host.getBaseAdapter());
        view.setLayoutManager(getLayoutManager());
        view.setHasFixedSize(true);

        if (this instanceof HasInitialState) {
            HasInitialState hasInitialState = (HasInitialState) this;
            Toast.makeText(host, "interlcking instance dependency possible", Toast.LENGTH_SHORT).show();
        }
    }

    default void setBaseAdapter(BaseActivity host){
        host.setBaseAdapter(getAdapter());
    }
}

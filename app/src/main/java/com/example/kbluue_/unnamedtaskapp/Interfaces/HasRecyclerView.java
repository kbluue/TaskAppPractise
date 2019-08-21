package com.example.kbluue_.unnamedtaskapp.Interfaces;

import androidx.recyclerview.widget.RecyclerView;

import com.example.kbluue_.unnamedtaskapp.Utils.BaseActivity;

public interface HasRecyclerView {

    int getViewRes();

    RecyclerView.Adapter getAdapter();

    RecyclerView.LayoutManager getLayoutManager();

    default void initRV(BaseActivity host){
        RecyclerView view = host.findViewById(getViewRes());
        setBaseAdapter(host);
        view.setAdapter(host.getAdapter());
        view.setLayoutManager(getLayoutManager());
        view.setHasFixedSize(true);
    }

    default void setBaseAdapter(BaseActivity host){
        host.setAdapter(getAdapter());
    }
}

package com.example.kbluue_.unnamedtaskapp.Interfaces;

public interface HasObserver {

    boolean isChanged();

    void setChanged();

    void clearChanged();

    Runnable getAction();

    default void notifyActions(){
        if (isChanged()) {
            getAction().run();
        }
        clearChanged();
    }
}

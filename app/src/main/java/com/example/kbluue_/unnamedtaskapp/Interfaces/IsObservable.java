package com.example.kbluue_.unnamedtaskapp.Interfaces;

public interface IsObservable {

    boolean isChanged();

    void setChanged();

    void clearChanged();

    Runnable getAction();

    default void notifyAction(){
        if (isChanged()) {
            getAction().run();
        }
        clearChanged();
    }
}

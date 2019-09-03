package com.example.kbluue_.unnamedtaskapp.Interfaces;

import android.text.Editable;
import android.text.TextWatcher;

public interface TextChangeListener {

    void onTextChanged(CharSequence s, int start, int before, int count);

    default TextWatcher WATCHER(){
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                TextChangeListener.this.onTextChanged(s, start, before, count);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };
    }
}

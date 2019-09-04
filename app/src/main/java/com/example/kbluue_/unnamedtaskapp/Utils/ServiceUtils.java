package com.example.kbluue_.unnamedtaskapp.Utils;

import android.content.Context;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

public class ServiceUtils {

    public static void showKeyboard(EditText view){
        InputMethodManager imm = (InputMethodManager) view
                .getContext()
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        assert imm != null;
        imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT);
    }

    public static void showToast(Context context, String msg){
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }
}

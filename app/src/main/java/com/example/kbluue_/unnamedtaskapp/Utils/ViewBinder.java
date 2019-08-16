package com.example.kbluue_.unnamedtaskapp.Utils;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class ViewBinder {

    private final Activity parentActivity;
    private final View parentView;

    private ViewBinder(Activity parentActivity) {
        this.parentActivity = parentActivity;
        parentView = null;
    }

    private ViewBinder(View parentView) {
        parentActivity = null;
        this.parentView = parentView;
    }

    public static ViewBinder getInstance(Activity parent) {
        return new ViewBinder(parent);
    }

    public static ViewBinder getInstance(View parent) {
        return new ViewBinder(parent);
    }

    private View getView(int viewId){
        if (parentActivity != null) {
            return parentActivity.findViewById(viewId);
        } else if (parentView != null) {
            return parentView.findViewById(viewId);
        } else {
            return null;
        }
    }

    private boolean bind(int viewId, Object content, int zero){
        try {
            View view = getView(viewId);

            if (view instanceof TextView) {
                ((TextView) view).setText(content.toString());
            } else if (view instanceof ImageView){
                ImageView target = (ImageView) view;
                if (content instanceof Integer){
                    int i = (int) content;
                    target.setImageResource(i);
                } else if (content instanceof Uri){
                    Uri uri = (Uri) content;
                    target.setImageURI(uri);
                } else if (content instanceof String) {
                    String s = (String) content;
                    Picasso.get()
                            .load(s)
                            .into(target);
                } else if (content instanceof Drawable) {
                    Drawable drawable = (Drawable) content;
                    target.setImageDrawable(drawable);
                }
            }
            return true;
        } catch (NullPointerException e){
            return false;
        }
    }

    public ViewBinder bind(int viewId, Object content){
        bind(viewId, content, 0);
        return this;
    }

    public ViewBinder addOnClickListener(int viewId, View.OnClickListener listener){
        View v = getView(viewId);
        if (v != null) {
            v.setClickable(true);
            v.setOnClickListener(listener);
        }
        return this;
    }
}

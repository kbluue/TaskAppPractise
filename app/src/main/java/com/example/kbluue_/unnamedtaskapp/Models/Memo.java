package com.example.kbluue_.unnamedtaskapp.Models;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

public class Memo extends StorableObject {

    private boolean parent;
    private boolean urgent;
    private List<Memo> children;
    private String name;
    private Timestamp timeCreated;
    private Timestamp lastUpdated;

    public Memo(){}

    public Memo(Context context, String prefix){
        super(context, prefix);
        name = "Memo #" + getId();
        timeCreated = new Timestamp();
        lastUpdated = timeCreated;
    }

    public boolean isParent() {
        return parent;
    }

    public Memo setParent(boolean parent) {
        this.parent = parent;
        return this;
    }

    public boolean isUrgent() {
        return urgent;
    }

    public Memo setUrgent(boolean urgent) {
        this.urgent = urgent;
        return this;
    }

    public List<Memo> getChildren() {
        return children;
    }

    public Memo setChildren(List<Memo> children) {
        this.children = children;
        return this;
    }

    public String getName() {
        return name;
    }

    public Memo setName(String name) {
        this.name = name;
        return this;
    }

    public Timestamp getTimeCreated() {
        return timeCreated;
    }

    public Memo setTimeCreated(Timestamp timeCreated) {
        this.timeCreated = timeCreated;
        return this;
    }

    public Timestamp getLastUpdated() {
        return lastUpdated;
    }

    public Memo setLastUpdated(Timestamp lastUpdated) {
        this.lastUpdated = lastUpdated;
        return this;
    }

    /**
     * @param child
     * @return null if Memo is not a parent
     *
     * Note: this will only work properly if calling Memo is a parent
     */
    public Memo addChild(Memo child){
        if (isParent()){
            if (children == null){
                children = new ArrayList<>();
            }
            children.add(child);
            setLastUpdated(child.getLastUpdated());
            return this;
        } else {
            return null;
        }
    }

    /**
     * @param position
     * @return the child memo in the required position. Will return null if calling memo is not a parent
     *
     * Note: this will only work properly if calling Memo is a parent
     */
    public Memo getChild(int position){
        if (isParent() && children != null){
            return children.get(position);
        } else {
            return null;
        }
    }
}

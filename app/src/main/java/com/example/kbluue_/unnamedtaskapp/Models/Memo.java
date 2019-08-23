package com.example.kbluue_.unnamedtaskapp.Models;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

public class Memo extends StorableObject implements Comparable {

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

    public <T extends Memo> List<T> getChildren(Class<T> klass) {
        if (getChildren() == null) {
            return null;
        } else {
            List<T> ts = new ArrayList<>();
            for (Memo memo : children){
                ts.add(klass.cast(memo));
            }
            return ts;
        }
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
            if (child.getId() != null){
                setLastUpdated(child.getLastUpdated());
            }
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

    @Override
    public int compareTo(Object o) {
        if (o instanceof Memo) {
            Memo anotherMemo = (Memo) o;
            Timestamp anotherMemoTS = anotherMemo.getTimeCreated(),
                    thisTS = getTimeCreated();
            if (anotherMemoTS == null ^ thisTS == null){
                if (thisTS == null){
                    return 1;
                } else {
                    return -1;
                }
            } else {
                if (thisTS == null){
                    return 0;
                } else {
                    return anotherMemoTS.getDate().compareTo(thisTS.getDate());
                }
            }
        } else {
            return -1;
        }
    }
}

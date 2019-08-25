package com.example.kbluue_.unnamedtaskapp.Models;

import android.content.Context;

import com.example.kbluue_.unnamedtaskapp.Utils.ArraysUtil;

public class Memo extends StorableObject implements Comparable {

    private boolean parent;
    private boolean urgent;
    private SubTask[] children;
    private Timestamp timeCreated;
    private Timestamp lastUpdated;

    public Memo(){}

    public Memo(Context context, String prefix){
        super(context, prefix);
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

    public SubTask[] getChildren() {
        return children;
    }

    public Memo setChildren(SubTask[] children) {
        this.children = children;
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
    public <T extends StorableObject> Memo addChild(T child){
        if (isParent()){
            ArraysUtil.add(children, child);
            if (child.getId() != null){
                Memo memo = ((Memo) child);
                setLastUpdated(memo.getLastUpdated());
            }
            return this;
        } else {
            return null;
        }
    }

    /**
     * @param position
     * @param aClass
     * @param <T>
     * @return the child memo in the required position. Will return null if calling memo is not a parent
     *
     * Note: this will only work properly if calling Memo is a parent
     */
    public <T extends StorableObject> T getChild(int position, Class<T> aClass){
        if (isParent() && children != null){
            Object o =  children[position];
            return aClass.cast(o);
        } else {
            return null;
        }
    }

    public Memo getChild(int position){
        return getChild(position, Memo.class);
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

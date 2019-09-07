package com.example.kbluue_.unnamedtaskapp.Models;

import android.content.Context;

public class Memo extends StorableObject implements Comparable {

    private boolean urgent;
    private Timestamp timeCreated;
    private Timestamp lastUpdated;

    public Memo(){}

    public Memo(Context context, String prefix){
        super(context, prefix);
        timeCreated = new Timestamp();
        lastUpdated = timeCreated;
    }

    public boolean isUrgent() {
        return urgent;
    }

    public void setUrgent(boolean urgent) {
        this.urgent = urgent;
        setChanged();
    }

    public Timestamp getTimeCreated() {
        return timeCreated;
    }

    public void setTimeCreated(Timestamp timeCreated) {
        this.timeCreated = timeCreated;
        setChanged();
    }

    public Timestamp getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Timestamp lastUpdated) {
        this.lastUpdated = lastUpdated;
        setChanged();
    }

    /**
     * @param o
     * @return -1, 0 or 1 if this Memo is considered greater than the Memo in param o
     *
     * Memos with null ids are considered the smaller Memos but if both have non-null ids, they will
     * be sorted based on their lastUpdated property. The most recently updated is considered smaller.
     */
    @Override
    public int compareTo(Object o) {
        if (o instanceof Memo) {
            Memo anotherMemo = (Memo) o;
            Timestamp anotherMemoTS = anotherMemo.getTimeCreated(),
                    thisTS = getTimeCreated();
            if (anotherMemoTS == null ^ thisTS == null){
                if (thisTS == null){
                    return -1;
                } else {
                    return 1;
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

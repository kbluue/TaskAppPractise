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

    public Memo setUrgent(boolean urgent) {
        this.urgent = urgent;
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

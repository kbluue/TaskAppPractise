package com.example.kbluue_.unnamedtaskapp.Utils;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class BaseAdapterTest {

    private BaseAdapter sampleBaseAdapter;
    private List sampleDataSet;

    @Before
    public void initSamples(){
        initSampleDataSet();
        initSampleAdapter();
    }

    private void initSampleAdapter(){
        sampleBaseAdapter = new BaseAdapter(sampleDataSet) {
            @Override
            public int getChildViewRes() {
                return 0;
            }

            @Override
            protected void bindDataToChild(ViewConfig config, Object childData) {

            }
        };
    }

    private void initSampleDataSet(){
        sampleDataSet = new ArrayList();
    }

    @Test
    public void setChildOnClickListener() {
    }

    @Test
    public void insertIntoDataSet() {
        Object data = new Object();

        assertEquals(sampleBaseAdapter.getItemCount(), 0);

        sampleBaseAdapter.insertIntoDataSet(data);

        assertEquals(sampleBaseAdapter.getItemCount(), 1);
    }

    @Test
    public void removeFromDataSet() {
        Object data = new Object();
        sampleDataSet.add(data);

//        sampleDataSet
    }

    @Test
    public void notifyDataChanged() {
    }

    @Test
    public void finish() {
    }
}
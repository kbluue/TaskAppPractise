package com.example.kbluue_.unnamedtaskapp.Utils;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kbluue_.unnamedtaskapp.Models.StorableObject;

import java.util.Collections;
import java.util.List;

public abstract class BaseAdapter extends RecyclerView.Adapter<BaseAdapter.BaseVH>{

    private List dataSet;
    private View.OnClickListener childOnClickListener;

    public BaseAdapter(@NonNull List dataSet) {
        this.dataSet = dataSet;
    }

    protected void setChildOnClickListener(View.OnClickListener childOnClickListener) {
        this.childOnClickListener = childOnClickListener;
    }

    protected void insertIntoDataSet(Object newData){
        dataSet.add(newData);
        notifyDataChanged();
    }

    protected void removeFromDataSet(com.example.kbluue_.unnamedtaskapp.Models.Task dataToBeRemoved){
        dataSet.remove(dataToBeRemoved);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public final BaseVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(getChildViewRes(), parent, false);
        return new BaseVH(view);
    }

    public abstract int getChildViewRes();

    class BaseVH extends RecyclerView.ViewHolder {

        BaseVH(@NonNull View itemView) {
            super(itemView);
        }

        void processChildView(int position){
            tagChild(position);
            initChildView(position);
            setChildOnClickListener();
        }

        private void tagChild(int position) {
            itemView.setTag(position);
        }

        private void initChildView(int position){
            ViewConfig config = ViewConfig.getInstance(itemView);
            Object childData = dataSet.get(position);
            bindDataToChild(config, childData);
        }

        private void setChildOnClickListener() {
            if (childOnClickListener != null)
                itemView.setOnClickListener(childOnClickListener);
        }

    }

    protected abstract void bindDataToChild(ViewConfig config, Object childData);

    @Override
    public final void onBindViewHolder(@NonNull BaseVH holder, int position) {
        holder.processChildView(position);
    }

    @Override
    public final int getItemCount() {
        return dataSet.size();
    }

    protected void notifyDataChanged(){
        sortDataSet();
        notifyDataSetChanged();
    }

    private void sortDataSet() {
        Collections.sort(dataSet);
    }

    public void finish() {
        Object dataSample = getDataSample();

        if (dataSample instanceof StorableObject)
            updateDataSetInPersistentStorage();
    }

    private Object getDataSample() {
        Object dataSample;
        if (!dataSet.isEmpty())
            dataSample = dataSet.get(0);
        else
            dataSample = null;
        return dataSample;
    }

    private void updateDataSetInPersistentStorage() {
        for (Object storableObject : dataSet){
            notifyForSaveAction((StorableObject) storableObject);
        }
    }

    private void notifyForSaveAction(StorableObject storableObject) {
        storableObject.notifyAction();
    }
}

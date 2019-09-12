package com.example.kbluue_.unnamedtaskapp.Utils;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Collections;
import java.util.List;

public abstract class BaseAdapter extends RecyclerView.Adapter<BaseAdapter.BaseVH>{

    private List dataSet;
    private boolean isChildClickable;
    private View.OnClickListener childOnClickListener;

    public void setChildClickable(boolean childClickable) {
        isChildClickable = childClickable;
    }

    public void setChildOnClickListener(View.OnClickListener childOnClickListener) {
        this.childOnClickListener = childOnClickListener;
    }

    public void insertIntoDataSet(Object newData){
        dataSet.add(newData);
    }

    public void removeFromDataSet(Object dataToBeRemoved){
        dataSet.remove(dataToBeRemoved);
    }

    public BaseAdapter(@NonNull List dataSet) {
        this.dataSet = dataSet;
    }

    @NonNull
    @Override
    public final BaseVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(getChildViewRes(), parent, false);
        return new BaseVH(view);
    }

    public abstract int getChildViewRes();

    public class BaseVH extends RecyclerView.ViewHolder {

        public BaseVH(@NonNull View itemView) {
            super(itemView);
        }

        public void processChildView(int position){
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
            bindChild(config, childData);
        }

        private void setChildOnClickListener() {
            if (isChildClickable && childOnClickListener != null)
                itemView.setOnClickListener(childOnClickListener);
        }

    }

    public abstract void bindChild(ViewConfig config, Object childData);

    @Override
    public final void onBindViewHolder(@NonNull BaseVH holder, int position) {
        holder.processChildView(position);
    }

    @Override
    public final int getItemCount() {
        return dataSet.size();
    }

    public void onDataSetChanged(){
        sortDataSet();
        notifyDataSetChanged();
    }

    private void sortDataSet() {
        Collections.sort(dataSet);
    }

    @Override
    protected void finalize() throws Throwable {
        onFinalize();
        super.finalize();
    }

    protected abstract void onFinalize();
}

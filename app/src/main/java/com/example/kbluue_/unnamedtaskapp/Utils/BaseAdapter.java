package com.example.kbluue_.unnamedtaskapp.Utils;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Collections;
import java.util.List;

public abstract class BaseAdapter extends RecyclerView.Adapter<BaseAdapter.BaseVH>{

    List dataSet;

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

    abstract int getChildViewRes();

    @Override
    public final void onBindViewHolder(@NonNull BaseVH holder, int position) {
        holder.bind(position);
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

    public class BaseVH extends RecyclerView.ViewHolder {

        public BaseVH(@NonNull View itemView) {
            super(itemView);
        }

        public void bind(int position){
            ViewConfig config = ViewConfig.getInstance(itemView);
            bindChild(config, position);
        }
    }

    abstract void bindChild(ViewConfig config, int position);
}

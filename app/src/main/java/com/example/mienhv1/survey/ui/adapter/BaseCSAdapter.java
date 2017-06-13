package com.example.mienhv1.survey.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MienHV1 on 6/13/2017.
 */

public abstract class BaseCSAdapter<T, D extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<D> {
    protected List<T> mItems;
    protected OnItemRecyclerClickListener<T> mListener;
    protected OnposItemRecyclerClickListener<T> mPosListener;

    public BaseCSAdapter(){
        mItems = new ArrayList<>();
    }
    public void updatesItem(List<T> items){
        if (items != null) {
            mItems = items;
            notifyDataSetChanged();
        }
    }
    public void addItems(List<T> items){
        if(items!=null)
        {
            mItems.remove(0);
            mItems.addAll(0,items);
            notifyDataSetChanged();
        }
    }
    public void addItem(T item) {
        if (item != null) {
            mItems.add(0, item);
            notifyDataSetChanged();
        }
    }

    public void replace(T item, int index) {
        if (item != null) {
            if (mItems.size() > 0 && index < mItems.size()) {
                mItems.remove(index);
            }
            addItem(item);
        }
    }

    public T getItemByPosition(int position) {
        if (position < getItemCount()) {
            return mItems.get(position);
        }
        return null;
    }
    @Override
    public D onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(D holder, int position) {

    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }
    public void setOnItemRecyclerClickListener(OnItemRecyclerClickListener<T> listener) {
        mListener = listener;
    }

    public void setOnPosItemRecyclerClickListener(OnposItemRecyclerClickListener<T> listener) {
        mPosListener = listener;
    }
}

package com.example.mienhv1.survey.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.datasource.model.StoreSystem;
import com.example.mienhv1.survey.base.BaseView;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

/**
 * Created by Forev on 17/04/17.
 */

public abstract class BaseAdapter<T, VH extends BaseViewHolder<T>> extends RecyclerView.Adapter<VH> {
    protected   final Context mContext;
    protected ArrayList<T> mData;

    RecyclerViewItemListener listener;


    public BaseAdapter(Context ctx, RecyclerViewItemListener l) {
        this.mContext = ctx;
        this.listener = l;
    }


    @Override
    public abstract VH onCreateViewHolder(ViewGroup parent, int viewType);

    @Override
    public void onBindViewHolder(VH holder, int position) {
        holder.bind(position,mData.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    public  void setData(ArrayList<T> data)
    {
        mData = data;
        notifyDataSetChanged();
    }
}

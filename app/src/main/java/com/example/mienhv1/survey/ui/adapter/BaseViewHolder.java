package com.example.mienhv1.survey.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Forev on 17/04/17.
 */

public abstract class BaseViewHolder<T> extends RecyclerView.ViewHolder {
    protected View root;
    protected Context mContext;
    public BaseViewHolder(Context ctx, View itemView) {
        super(itemView);
        this.mContext = ctx;
        root = itemView;
        root.setClickable(true);
        bindView();
    }

    protected abstract void bindView();


    public abstract void bind(int position, T data, RecyclerViewItemListener listener);
}

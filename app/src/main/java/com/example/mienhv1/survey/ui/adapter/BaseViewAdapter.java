package com.example.mienhv1.survey.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.datasource.model.ItemQuestionModel;

import java.util.ArrayList;

/**
 * Created by Forev on 17/04/21.
 */

public abstract class BaseViewAdapter<T>  {

    public int resourceId;
    public Context mContext;
    public ViewGroup mParent;
    public CallBackDataListener listener;
    public ItemQuestionModel itemModel;


    public ArrayList<View> getViews() {
        return views;
    }

    ArrayList<View> views = new ArrayList<>();
    protected ArrayList<T> mData = new ArrayList<>();

    public BaseViewAdapter(Context context, int id, ViewGroup parent, ArrayList data,CallBackDataListener listeneritem,ItemQuestionModel itemmodel) {
        this.mContext = context;
        resourceId = id;
        mParent = parent;
        mData = data;
        listener=listeneritem;
        itemModel = itemmodel;

        for (int i = 0; i < mData.size(); i++) {
            View view = initView(i);
            views.add(view);
        }
    }

    public int getCount() {
        return views.size();
    }


    public View initView(int position) {
        View root = LayoutInflater.from(mContext).inflate(resourceId, mParent, false);
        bindView(root, position,listener);
        return root;
    }

    public abstract void bindView(View view, int position, CallBackDataListener listeneritem);
}

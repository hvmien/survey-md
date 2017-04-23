package com.example.mienhv1.survey.ui.adapter;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.example.mienhv1.survey.R;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

/**
 * Created by Forev on 17/04/21.
 */

public abstract class BaseViewAdapter<T>  {

    public int resourceId;
    public Context mContext;
    public ViewGroup mParent;

    public ArrayList<View> getViews() {
        return views;
    }

    ArrayList<View> views = new ArrayList<>();
    protected ArrayList<T> mData = new ArrayList<>();

    public BaseViewAdapter(Context context, int id, ViewGroup parent, ArrayList data) {
        this.mContext = context;
        resourceId = id;
        mParent = parent;
        mData = data;

        for (int i = 0; i < mData.size(); i++) {
            View view = initView(i);
            views.add(view);
        }
    }

    public int getCount() {
        return views.size();
    }


    public View initView(int position) {
        View root = LayoutInflater.from(mContext).inflate(R.layout.item_radio_button, mParent, false);
        bindView(root, position);
        return root;
    }

    public abstract void bindView(View view, int position);
}

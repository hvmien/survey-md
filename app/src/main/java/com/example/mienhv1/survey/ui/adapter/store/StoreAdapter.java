package com.example.mienhv1.survey.ui.adapter.store;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.datasource.model.StoreSystem;
import com.example.mienhv1.survey.R;
import com.example.mienhv1.survey.ui.adapter.BaseAdapter;
import com.example.mienhv1.survey.ui.adapter.RecyclerViewItemListener;

import java.util.ArrayList;

/**
 * Created by Forev on 17/04/18.
 */

public class StoreAdapter extends BaseAdapter<StoreSystem, StoreViewHolder> {

    public StoreAdapter(Context ctx, RecyclerViewItemListener l) {
        super(ctx, l);
    }

    @Override
    public StoreViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new StoreViewHolder(mContext, LayoutInflater.from(mContext).inflate(R.layout.item_store_system_view, parent, false));
    }

}

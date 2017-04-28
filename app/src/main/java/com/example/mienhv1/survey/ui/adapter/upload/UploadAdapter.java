package com.example.mienhv1.survey.ui.adapter.upload;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.datasource.model.ItemAttributeModel;
import com.example.mienhv1.survey.R;
import com.example.mienhv1.survey.ui.adapter.BaseAdapter;
import com.example.mienhv1.survey.ui.adapter.BaseViewHolder;
import com.example.mienhv1.survey.ui.adapter.RecyclerViewItemListener;

/**
 * Created by Forev on 17/04/28.
 */

public class UploadAdapter extends BaseAdapter<ItemAttributeModel, UploadAdapter.UploadViewHolder> {

    public UploadAdapter(Context ctx, RecyclerViewItemListener l) {
        super(ctx, l);
    }

    @Override
    public UploadViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new UploadViewHolder(mContext, LayoutInflater.from(mContext).inflate(R.layout.item_pick_images, parent, false));
    }

    class UploadViewHolder extends BaseViewHolder<ItemAttributeModel> {

        public UploadViewHolder(Context ctx, View itemView) {
            super(ctx, itemView);
        }

        @Override
        protected void bindView() {

        }

        @Override
        public void bind(int position, ItemAttributeModel data, RecyclerViewItemListener listener) {

        }
    }
}

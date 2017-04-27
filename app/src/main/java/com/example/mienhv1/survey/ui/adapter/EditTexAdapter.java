package com.example.mienhv1.survey.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.example.datasource.model.ItemAttributeModel;
import com.example.mienhv1.survey.R;
import com.example.mienhv1.survey.utils.view.CSEditText;
import com.example.mienhv1.survey.utils.view.CSTextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Forev on 17/04/26.
 */

public class EditTexAdapter extends BaseAdapter<ItemAttributeModel, EditTexAdapter.ViewHolder> {


    public EditTexAdapter(Context ctx, RecyclerViewItemListener l) {
        super(ctx, l);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_editext_view, parent, false);

        return new ViewHolder(mContext, view);
    }

    public class ViewHolder extends BaseViewHolder<ItemAttributeModel> {

        CSEditText txtAtt;

        public ViewHolder(Context ctx, View itemView) {
            super(ctx, itemView);
        }

        @Override
        protected void bindView() {
            txtAtt = (CSEditText) itemView.findViewById(R.id.txt_att_name);
        }

        @Override
        public void bind(int position, ItemAttributeModel data, RecyclerViewItemListener listener) {
            txtAtt.setHint(data.name_display);
        }
    }
}

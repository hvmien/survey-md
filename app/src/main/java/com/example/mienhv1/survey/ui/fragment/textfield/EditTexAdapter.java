package com.example.mienhv1.survey.ui.fragment.textfield;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.datasource.model.ItemAttributeModel;
import com.example.mienhv1.survey.R;
import com.example.mienhv1.survey.ui.adapter.BaseAdapter;
import com.example.mienhv1.survey.ui.adapter.BaseViewHolder;
import com.example.mienhv1.survey.ui.adapter.RecyclerViewItemListener;
import com.example.mienhv1.survey.utils.view.CSEditText;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Forev on 17/04/26.
 */

public class EditTexAdapter extends BaseAdapter<ItemAttributeModel, EditTexAdapter.ViewHolder> {
    ArrayList listTextField=new ArrayList();
    private ViewHolder viewHD;
    HashMap<String, String> meMap=new HashMap<String, String>();
    public EditTexAdapter(Context ctx, RecyclerViewItemListener l) {
        super(ctx, l);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_editext_view, parent, false);

        return new ViewHolder(mContext, view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        viewHD = holder;
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
        public void bind(int position, final ItemAttributeModel data, RecyclerViewItemListener listener) {
            txtAtt.setHint(data.name_display);
            listTextField.add(data);
            txtAtt.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    meMap.put(data.name_column,txtAtt.getText().toString());
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });

        }
    }

    public HashMap<String, String> getDataTextview() {
        return meMap;
    }
}

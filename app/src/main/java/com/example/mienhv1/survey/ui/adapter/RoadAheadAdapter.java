package com.example.mienhv1.survey.ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import com.example.datasource.model.ItemQuestionModel;
import com.example.datasource.model.RoadAhead;
import com.example.mienhv1.survey.utils.view.CSRadioButton;

import java.util.ArrayList;

/**
 * Created by Forev on 17/04/21.
 */

public class RoadAheadAdapter extends BaseViewAdapter<RoadAhead> {
    OnUpdateListener<RoadAhead> listener;

    int count = 2;

    public void setOnUpdateListener(OnUpdateListener l) {
        this.listener = l;
    }

    public RoadAheadAdapter(Context context, int id, ViewGroup parent, ArrayList data,CallBackDataListener listener,ItemQuestionModel itemmodel) {
        super(context, id, parent, data,listener,itemmodel);
    }

    @Override
    public void bindView(View view, final int position, CallBackDataListener listeneritem) {
        CSRadioButton item = (CSRadioButton) view;
        item.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mData.get(position).select = isChecked;
                if (count == 2) {
                    if (listener != null)
                        listener.onUpdate(mData);
                    count = 0;
                }

                count++;
            }
        });
        item.setText(mData.get(position).name);
    }
}

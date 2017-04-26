package com.example.mienhv1.survey.ui.fragment.radiobutton;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import com.example.datasource.model.CheckboxModel;
import com.example.datasource.model.ItemAttributeModel;
import com.example.datasource.model.RadiobuttonModel;
import com.example.datasource.model.RoadAhead;
import com.example.mienhv1.survey.R;
import com.example.mienhv1.survey.ui.adapter.BaseViewAdapter;
import com.example.mienhv1.survey.ui.adapter.OnUpdateListener;
import com.example.mienhv1.survey.utils.view.CSCheckBox;
import com.example.mienhv1.survey.utils.view.CSRadioButton;

import java.util.ArrayList;

/**
 * Created by HVM on 4/23/2017.
 */

public class RadioButtonAdapter extends BaseViewAdapter<ItemAttributeModel> {
    OnUpdateListener<ItemAttributeModel> listener;


    public RadioButtonAdapter(Context context, @LayoutRes int id, ViewGroup parent, ArrayList<RadiobuttonModel> data) {
        super(context, id, parent, data);
    }

    @Override
    public void bindView(View view, final int position) {
        CSRadioButton item = (CSRadioButton) view;
        item.setText(mData.get(position).name_display);
    }
}

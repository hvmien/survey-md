package com.example.mienhv1.survey.ui.fragment.radiobutton;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.example.datasource.model.ItemAttributeModel;
import com.example.datasource.model.ItemQuestionModel;
import com.example.datasource.model.RadiobuttonModel;
import com.example.mienhv1.survey.ui.adapter.BaseViewAdapter;
import com.example.mienhv1.survey.ui.adapter.OnUpdateListener;
import com.example.mienhv1.survey.ui.adapter.CallBackDataListener;
import com.example.mienhv1.survey.utils.view.CSRadioButton;

import java.util.ArrayList;

/**
 * Created by HVM on 4/23/2017.
 */

public class RadioButtonAdapter extends BaseViewAdapter<ItemAttributeModel> {

    OnUpdateListener<ItemAttributeModel> listener;

    public RadioButtonAdapter(Context context, @LayoutRes int id, ViewGroup parent, ArrayList<ItemAttributeModel> data,CallBackDataListener listener,ItemQuestionModel itemmodel) {
        super(context, id, parent, data,listener,itemmodel);
    }

    @Override
    public void bindView(View view, final int position, final CallBackDataListener listener) {
        CSRadioButton item = (CSRadioButton) view;
        item.setText(mData.get(position).name_display);
//        item.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                listener.onItemClick(itemModel,position);
//            }
//        });
    }

    public void getdataFromAdapter() {

        Log.d("àdsfasdfas","ádfasd");
    }
}

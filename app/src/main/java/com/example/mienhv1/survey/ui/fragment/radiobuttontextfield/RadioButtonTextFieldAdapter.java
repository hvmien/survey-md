package com.example.mienhv1.survey.ui.fragment.radiobuttontextfield;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.example.datasource.model.ItemQuestionModel;
import com.example.datasource.model.RadioButtonTexFieldModel;
import com.example.mienhv1.survey.ui.adapter.BaseViewAdapter;
import com.example.mienhv1.survey.ui.adapter.CallBackDataListener;

import java.util.ArrayList;

/**
 * Created by HVM on 4/23/2017.
 */

public class RadioButtonTextFieldAdapter extends BaseViewAdapter<RadioButtonTexFieldModel>{
    public RadioButtonTextFieldAdapter(Context context, int id, ViewGroup parent, ArrayList data,CallBackDataListener listener,ItemQuestionModel itemmodel) {
        super(context, id, parent, data,listener,itemmodel);
    }

    @Override
    public void bindView(View view, int position, CallBackDataListener listeneritem) {

    }
}

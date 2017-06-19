package com.example.mienhv1.survey.ui.fragment.checboxtextfield;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.datasource.model.CheckboxTextFieldModel;
import com.example.datasource.model.ItemQuestionModel;
import com.example.mienhv1.survey.R;
import com.example.mienhv1.survey.ui.adapter.BaseViewAdapter;
import com.example.mienhv1.survey.ui.adapter.CallBackDataListener;
import com.example.mienhv1.survey.ui.fragment.checkbox.CheckboxAdapter;
import com.example.mienhv1.survey.utils.view.CSEditText;
import com.example.mienhv1.survey.utils.view.CSGroupCheckbox;

import java.util.ArrayList;

/**
 * Created by HVM on 4/23/2017.
 */

public class CheckboxTextFieldAdapter extends BaseViewAdapter<CheckboxTextFieldModel>implements CallBackDataListener {

    public CheckboxTextFieldAdapter(Context context, @LayoutRes int id, ViewGroup parent, ArrayList<CheckboxTextFieldModel> data,CallBackDataListener listener,ItemQuestionModel itemmodel) {
        super(context, id, parent, data,listener,itemmodel);
    }

    @Override
    public void bindView(View view, int position, CallBackDataListener listeneritem) {
        LinearLayout linearLayout = (LinearLayout) view;
        CSGroupCheckbox grcheck = (CSGroupCheckbox) (linearLayout).getChildAt(0);
        CardView cd = (CardView) (linearLayout).getChildAt(1);
        CSEditText editText = (CSEditText) cd.getChildAt(0);
        for (int i = 0; i < mData.size(); i++) {

//            grcheck.setAdapter(new CheckboxAdapter(mContext, R.layout.item_check_box_view,grcheck,mData.get(0).mList,this,item));
            // editText.setText(mData.get(i).text);
        }
    }

    @Override
    public void onItemClick(ItemQuestionModel item,int id,boolean ischeck) {

    }
}

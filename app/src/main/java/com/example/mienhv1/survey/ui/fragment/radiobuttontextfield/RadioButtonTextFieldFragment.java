package com.example.mienhv1.survey.ui.fragment.radiobuttontextfield;

import android.os.Bundle;
import android.view.View;

import com.example.datasource.model.ItemQuestionModel;
import com.example.mienhv1.survey.Constants;
import com.example.mienhv1.survey.R;
import com.example.mienhv1.survey.base.BaseFragment;
import com.example.mienhv1.survey.ui.adapter.EnumSurveyFragment;
import com.example.mienhv1.survey.ui.fragment.ItemBaseSurveyFragment;
import com.example.mienhv1.survey.utils.view.CSGroupRadiobuttonEdittext;
import com.example.mienhv1.survey.utils.view.CSTextView;

/**
 * Created by HVM on 4/23/2017.
 */

public class RadioButtonTextFieldFragment extends ItemBaseSurveyFragment {


    private CSTextView txtTitle;
    private CSGroupRadiobuttonEdittext groupRadiobuttonEdittext;

    @Override
    protected int getResourcesLayout() {
        return R.layout.fragment_radiobutton_textfield;
    }

    @Override
    protected void mapView(View view) {
        txtTitle = (CSTextView) view.findViewById(R.id.txt_title);
        groupRadiobuttonEdittext = (CSGroupRadiobuttonEdittext) view.findViewById(R.id.gr_radiobutton_text_field);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void destroyView() {

    }

    public static RadioButtonTextFieldFragment newInstance(ItemQuestionModel itemQuestionModel) {
        Bundle args = new Bundle();
        args.putParcelable(Constants.ARG_ITEM_SURVEY, itemQuestionModel);
        RadioButtonTextFieldFragment fragment = new RadioButtonTextFieldFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public EnumSurveyFragment fragmentType() {
        return EnumSurveyFragment.RadioButton_EditText;
    }
}

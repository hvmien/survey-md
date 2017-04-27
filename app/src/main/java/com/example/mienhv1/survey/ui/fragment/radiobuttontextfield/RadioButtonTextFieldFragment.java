package com.example.mienhv1.survey.ui.fragment.radiobuttontextfield;

import android.os.Bundle;
import android.view.View;

import com.example.datasource.model.DataResponse;
import com.example.datasource.model.ItemAttributeModel;
import com.example.datasource.model.ItemQuestionModel;
import com.example.datasource.repository.DataRepository;
import com.example.datasource.repository.DataRepositoryFactory;
import com.example.datasource.usercases.GetSurveyAttributeUsercase;
import com.example.mienhv1.survey.Constants;
import com.example.mienhv1.survey.R;
import com.example.mienhv1.survey.ui.adapter.EnumSurveyFragment;
import com.example.mienhv1.survey.ui.fragment.ItemBaseSurveyFragment;
import com.example.mienhv1.survey.ui.fragment.radiobutton.RadioButtonAdapter;
import com.example.mienhv1.survey.utils.view.CSGroupRadiobuttonEdittext;
import com.example.mienhv1.survey.utils.view.CSTextView;

import java.util.ArrayList;

/**
 * Created by HVM on 4/23/2017.
 */

public class RadioButtonTextFieldFragment extends ItemBaseSurveyFragment {


    private CSTextView txtTitle;
    private CSGroupRadiobuttonEdittext groupRadiobuttonEdittext;

    private GetSurveyAttributeUsercase getSurveyAttributeUsercase;

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
        super.initData();
        ItemQuestionModel item = getArguments().getParcelable(Constants.ARG_ITEM_SURVEY);
        txtTitle.setText(item.title);
    }

    protected  void onDataListen(ArrayList<ItemAttributeModel> data){
        groupRadiobuttonEdittext.setData(data);
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

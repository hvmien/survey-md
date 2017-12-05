package com.example.mienhv1.survey.ui.fragment.radiobuttontextfield;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.datasource.model.AnswerModel;
import com.example.datasource.model.ItemAttributeModel;
import com.example.datasource.model.ItemQuestionModel;
import com.example.mienhv1.survey.Constants;
import com.example.mienhv1.survey.R;
import com.example.mienhv1.survey.ui.adapter.EnumSurveyFragment;
import com.example.mienhv1.survey.ui.fragment.ItemBaseSurveyFragment;
import com.example.mienhv1.survey.utils.view.CSGroupCallBackDataEdittext;
import com.example.mienhv1.survey.utils.view.CSTextView;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by HVM on 4/23/2017.
 */

public class RadioButtonTextFieldFragment extends ItemBaseSurveyFragment {


    private CSTextView txtTitle;
    private CSGroupCallBackDataEdittext groupRadiobuttonEdittext;
    private ProgressBar radiobtntextProgressbar;


    @Override
    protected int getResourcesLayout() {
        return R.layout.fragment_radiobutton_textfield;
    }

    @Override
    protected void mapView(View view) {
        radiobtntextProgressbar = (ProgressBar) view.findViewById(R.id.radiobtntext_progressbar);
        txtTitle = (CSTextView) view.findViewById(R.id.txt_title);
        groupRadiobuttonEdittext = (CSGroupCallBackDataEdittext) view.findViewById(R.id.gr_radiobutton_text_field);
    }

    ItemQuestionModel item;

    @Override
    protected void initData() {
        super.initData();
        item = getArguments().getParcelable(Constants.ARG_ITEM_SURVEY);
        txtTitle.setText(item.order_rank + ". " + item.title);
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

    @Override
    public boolean checkData() {
        return false;
    }

    @Override
    public AnswerModel getDataFromUserHandle() {
        AnswerModel m =new AnswerModel();
        m.idQuestion=item.order_rank;
//        m.modelAnswerMeta=getData();
        return m;
    }

    @Override
    public void showProgress() {
        radiobtntextProgressbar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        radiobtntextProgressbar.setVisibility(View.GONE);
    }

    @Override
    public void showError(String error) {
        Toast.makeText(getActivity(), error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onGetDataListenner(ArrayList<ItemAttributeModel> data) {
        groupRadiobuttonEdittext.setData(data, item);
    }
    public HashMap<String,String> getData(){
        return groupRadiobuttonEdittext.getDataFromUser();
    }
}

package com.example.mienhv1.survey.ui.fragment.checboxtextfield;

import android.view.View;

import com.example.datasource.model.CheckboxModel;
import com.example.datasource.model.CheckboxTextFieldModel;
import com.example.mienhv1.survey.R;
import com.example.mienhv1.survey.base.BaseFragment;
import com.example.mienhv1.survey.utils.view.CSGroupCheckboxEdittext;
import com.example.mienhv1.survey.utils.view.CSTextView;

import java.util.ArrayList;

/**
 * Created by HVM on 4/23/2017.
 */

public class CheckboxTextFieldFragment extends BaseFragment {
    private CSTextView txtTitle;
    private CSGroupCheckboxEdittext groupCheckboxEdittextParent;
    @Override
    protected int getResourcesLayout() {
        return R.layout.fragment_checkbox_textfield;
    }

    @Override
    protected void mapView(View view) {
        txtTitle = (CSTextView) view.findViewById(R.id.txt_title);
        groupCheckboxEdittextParent = (CSGroupCheckboxEdittext) view.findViewById(R.id.gr_checkbox_text_field);
    }

    @Override
    protected void initData() {
//        ArrayList<CheckboxTextFieldModel> mListTextField = new ArrayList<>();
//
//        ArrayList<CheckboxModel> mList = new ArrayList<>();
//        CheckboxModel data = new CheckboxModel(1, "CheckboxModel3", false);
//        CheckboxModel data1 = new CheckboxModel(2, "CheckboxModel4", false);
//        mList.add(data);
//        mList.add(data1);
//        String textField="Input EditText";
//
//        mListTextField.add(new CheckboxTextFieldModel(mList,textField));
////        mListTextField.add(new CheckboxTextFieldModel(mList,textField));
//        CheckboxTextFieldAdapter ckA = new CheckboxTextFieldAdapter(getActivity(), R.layout.cs_item_survey_checkbox_edittext, groupCheckboxEdittextParent, mListTextField);
//       //groupCheckboxEdittextParent.setAdapter(ckA);
    }

    @Override
    protected void destroyView() {

    }
}

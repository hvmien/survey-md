package com.example.mienhv1.survey.ui.fragment.checkbox;

import android.os.Bundle;
import android.view.View;

import com.example.datasource.model.CheckboxModel;
import com.example.datasource.model.ItemAttributeModel;
import com.example.datasource.model.ItemQuestionModel;
import com.example.datasource.utils.Constant;
import com.example.mienhv1.survey.Constants;
import com.example.mienhv1.survey.R;
import com.example.mienhv1.survey.ui.adapter.EnumSurveyFragment;
import com.example.mienhv1.survey.ui.fragment.ItemBaseSurveyFragment;
import com.example.mienhv1.survey.utils.view.CSGroupCheckbox;
import com.example.mienhv1.survey.utils.view.CSTextView;

import java.util.ArrayList;

/**
 * Created by HVM on 4/23/2017.
 */

public class CheckboxFragment extends ItemBaseSurveyFragment {
    private CSTextView txtTitle;
    private CSGroupCheckbox grCheckboxParent;

    public static CheckboxFragment newInstance(ItemQuestionModel item) {

        Bundle args = new Bundle();
        args.putParcelable(Constants.ARG_ITEM_SURVEY, item);
        CheckboxFragment fragment = new CheckboxFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getResourcesLayout() {
        return R.layout.fragment_checkbox;
    }

    @Override
    protected void mapView(View view) {
        txtTitle = (CSTextView) view.findViewById(R.id.txt_title);
        grCheckboxParent = (CSGroupCheckbox) view.findViewById(R.id.gr_checkbox);


    }

    @Override
    protected void initData() {
        ItemQuestionModel item = getArguments().getParcelable(Constants.ARG_ITEM_SURVEY);
        txtTitle.setText(item.title);
        //goi toi api /table_attritute params{table_id} lay table_id trong getArguments()
        ArrayList mList = new ArrayList<>();
        ItemAttributeModel item1 = new ItemAttributeModel();
        item1.name_column = "alley";
        item1.name_display = "Hem";
        mList.add(item1);

        ItemAttributeModel item2 = new ItemAttributeModel();
        item2.name_column = "route";
        item2.name_display = "Quoc Lo";
        mList.add(item2);

        ItemAttributeModel item3 = new ItemAttributeModel();
        item3.name_column = "street";
        item3.name_display = "Duong Pho";
        mList.add(item3);

        CheckboxAdapter ckA = new CheckboxAdapter(getActivity(), R.layout.item_check_box_view, grCheckboxParent, mList);
        grCheckboxParent.setAdapter(ckA);

    }

    @Override
    protected void destroyView() {

    }

    @Override
    public EnumSurveyFragment fragmentType() {
        return EnumSurveyFragment.CheckBox;
    }
}

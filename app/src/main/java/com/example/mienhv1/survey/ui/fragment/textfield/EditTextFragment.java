package com.example.mienhv1.survey.ui.fragment.textfield;

import android.os.Bundle;
import android.view.View;

import com.example.datasource.model.ItemAttributeModel;
import com.example.datasource.model.ItemQuestionModel;
import com.example.mienhv1.survey.Constants;
import com.example.mienhv1.survey.R;
import com.example.mienhv1.survey.ui.adapter.EnumSurveyFragment;
import com.example.mienhv1.survey.ui.fragment.ItemBaseSurveyFragment;
import com.example.mienhv1.survey.utils.view.CSTextView;

/**
 * Created by Forev on 17/04/26.
 */

public class EditTextFragment extends ItemBaseSurveyFragment{

    public static EditTextFragment newInstance(ItemQuestionModel model) {

        Bundle args = new Bundle();
        args.putParcelable(Constants.ARG_ITEM_SURVEY, model);
        EditTextFragment fragment = new EditTextFragment();
        fragment.setArguments(args);
        return fragment;
    }

    CSTextView txtTitle;
    @Override
    public EnumSurveyFragment fragmentType() {
        return EnumSurveyFragment.Editext;
    }

    @Override
    protected int getResourcesLayout() {
        return R.layout.fragment_only_editext_question;
    }

    @Override
    protected void mapView(View view) {
        txtTitle = (CSTextView) view.findViewById(R.id.title);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void destroyView() {

    }
}

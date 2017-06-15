package com.example.mienhv1.survey.ui.fragment.textfield;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.datasource.model.ItemAttributeModel;
import com.example.datasource.model.ItemQuestionModel;
import com.example.mienhv1.survey.Constants;
import com.example.mienhv1.survey.R;
import com.example.mienhv1.survey.ui.adapter.EditTexAdapter;
import com.example.mienhv1.survey.ui.adapter.EnumSurveyFragment;
import com.example.mienhv1.survey.ui.fragment.ItemBaseSurveyFragment;
import com.example.mienhv1.survey.utils.view.CSTextView;

import java.util.ArrayList;

/**
 * Created by Forev on 17/04/26.
 */

public class EditTextFragment extends ItemBaseSurveyFragment {

    public static EditTextFragment newInstance(ItemQuestionModel model) {

        Bundle args = new Bundle();
        args.putParcelable(Constants.ARG_ITEM_SURVEY, model);
        EditTextFragment fragment = new EditTextFragment();
        fragment.setArguments(args);
        return fragment;
    }

    CSTextView txtTitle;
    RecyclerView rcQuestion;

    @Override
    public EnumSurveyFragment fragmentType() {
        return EnumSurveyFragment.Editext;
    }

    @Override
    protected void returnDataFromFragment() {

    }

    @Override
    protected int getResourcesLayout() {
        return R.layout.fragment_only_editext_question;
    }

    @Override
    protected void mapView(View view) {
        txtTitle = (CSTextView) view.findViewById(R.id.txt_title);
        rcQuestion = (RecyclerView) view.findViewById(R.id.rc_question);
        rcQuestion.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    protected void initData() {
        super.initData();
        ItemQuestionModel item = getArguments().getParcelable(Constants.ARG_ITEM_SURVEY);
        txtTitle.setText(item.order_rank+ ". " +item.title);
    }

    @Override
    protected void destroyView() {

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showError(String error) {
        Toast.makeText(getActivity(), error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onGetDataListenner(ArrayList<ItemAttributeModel> data) {
        EditTexAdapter adapter = new EditTexAdapter(getContext(), null);
        adapter.updateData(data);
        rcQuestion.setAdapter(adapter);
    }
}

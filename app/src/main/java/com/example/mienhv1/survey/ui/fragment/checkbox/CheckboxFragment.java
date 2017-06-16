package com.example.mienhv1.survey.ui.fragment.checkbox;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.datasource.model.AnswerModel;
import com.example.datasource.model.ItemAttributeModel;
import com.example.datasource.model.ItemQuestionModel;
import com.example.mienhv1.survey.Constants;
import com.example.mienhv1.survey.R;
import com.example.mienhv1.survey.ui.adapter.CallBackDataListener;
import com.example.mienhv1.survey.ui.adapter.EnumSurveyFragment;
import com.example.mienhv1.survey.ui.fragment.ItemBaseSurveyFragment;
import com.example.mienhv1.survey.utils.view.CSGroupCheckbox;
import com.example.mienhv1.survey.utils.view.CSTextView;

import java.util.ArrayList;

/**
 * Created by HVM on 4/23/2017.
 */

public class CheckboxFragment extends ItemBaseSurveyFragment implements CallBackDataListener {
    private CSTextView txtTitle;
    private CSGroupCheckbox grCheckboxParent;
    private ProgressBar checkboxProgressbar;


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
        checkboxProgressbar = (ProgressBar) view.findViewById(R.id.checkbox_progress);
        txtTitle = (CSTextView) view.findViewById(R.id.txt_title);
        grCheckboxParent = (CSGroupCheckbox) view.findViewById(R.id.gr_checkbox);
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

    @Override
    public EnumSurveyFragment fragmentType() {
        return EnumSurveyFragment.CheckBox;
    }

    @Override
    public boolean checkData() {
        return false;
    }

    @Override
    public AnswerModel getDataFromUserHandle() {
        return null;
    }

    @Override
    public void showProgress() {
        checkboxProgressbar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        checkboxProgressbar.setVisibility(View.GONE);
    }

    @Override
    public void showError(String error) {
        Toast.makeText(getActivity(), error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onGetDataListenner(ArrayList<ItemAttributeModel> dataAtt) {
        if (dataAtt != null) {
            //goi toi api /table_attritute params{table_id} lay table_id trong getArguments()
            ArrayList mList = new ArrayList<>();
            mList.addAll(dataAtt);
            CheckboxAdapter ckA = new CheckboxAdapter(getActivity(), R.layout.item_check_box_view, grCheckboxParent, mList, this, item);
            grCheckboxParent.setAdapter(ckA);
        }
    }

    @Override
    public void onItemClick(ItemQuestionModel item, int id) {
        if (mListener != null) {
            mListener.onFragmentInteraction(item, id);
        }
    }


}

package com.example.mienhv1.survey.ui.fragment.radiobutton;

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
import com.example.mienhv1.survey.utils.view.CSRadioGroup;
import com.example.mienhv1.survey.utils.view.CSTextView;

import java.util.ArrayList;

/**
 * Created by HVM on 4/23/2017.
 */

public class RadioButtonFragment extends ItemBaseSurveyFragment implements View.OnClickListener, CallBackDataListener {
    private CSTextView txtTitle;
    private CSRadioGroup csRadioGroupParent;
    private ProgressBar radiobuttonProgress;
    private ArrayList mList;
    private int mID = -1;

    public static RadioButtonFragment newInstance(ItemQuestionModel item) {

        Bundle args = new Bundle();
        args.putParcelable(Constants.ARG_ITEM_SURVEY, item);
        RadioButtonFragment fragment = new RadioButtonFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getResourcesLayout() {
        return R.layout.fragment_radiobutton;
    }

    @Override
    protected void mapView(View view) {
        radiobuttonProgress = (ProgressBar) view.findViewById(R.id.radiobutton_progress);
        txtTitle = (CSTextView) view.findViewById(R.id.txt_title_radio_group);
        csRadioGroupParent = (CSRadioGroup) view.findViewById(R.id.gp_data);
        csRadioGroupParent.setOnClickListener(this);
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
        return EnumSurveyFragment.RadioButton;
    }

    @Override
    public boolean checkData() {
        if (mID == -1)
            return false;
        return true;
    }

    @Override
    public AnswerModel getDataFromUserHandle() {
        return null;
    }

    @Override
    public void showProgress() {
        radiobuttonProgress.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        radiobuttonProgress.setVisibility(View.GONE);
    }

    @Override
    public void showError(String error) {
        Toast.makeText(getActivity(), error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onGetDataListenner(ArrayList<ItemAttributeModel> data) {
        if (data != null) {
            //goi toi api /table_attritute params{table_id} lay table_id trong getArguments()
            mList = new ArrayList<>();
            mList.addAll(data);
            RadioButtonAdapter ckA = new RadioButtonAdapter(getActivity(), R.layout.item_radio_button, csRadioGroupParent, mList, this, item);
            csRadioGroupParent.setAdapter(ckA);
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.gp_data) {

        }
    }

    @Override
    public void onItemClick(ItemQuestionModel item, int id) {
        if (mListener != null) {
            mID = id;
            mListener.onFragmentInteraction(item, id);
        }

    }
}

package com.example.mienhv1.survey.ui.fragment.info;

import android.util.Log;
import android.view.View;

import com.example.datasource.model.RoadAhead;
import com.example.mienhv1.survey.R;
import com.example.mienhv1.survey.base.BaseFragment;
import com.example.mienhv1.survey.ui.adapter.EnumSurveyFragment;
import com.example.mienhv1.survey.ui.adapter.OnUpdateListener;
import com.example.mienhv1.survey.ui.adapter.RoadAheadAdapter;
import com.example.mienhv1.survey.ui.fragment.ItemBaseSurveyFragment;
import com.example.mienhv1.survey.utils.view.CSGroupCheckbox;
import com.example.mienhv1.survey.utils.view.CSRadioGroup;

import java.util.ArrayList;

/**
 * Created by Forev on 17/04/20.
 */

public class InfoFragment extends ItemBaseSurveyFragment implements InfoView {

    InfoPresenter presenter;
    private String TAG = "InfoFragment";



    @Override
    protected int getResourcesLayout() {
        return R.layout.fragment_general_information;
    }

    @Override
    protected void mapView(View view) {
        presenter = new InfoPresenter(this);
    }

    @Override
    protected void initData() {
        presenter.create();
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

    }

    @Override
    public EnumSurveyFragment fragmentType() {
        return EnumSurveyFragment.Common;
    }
}

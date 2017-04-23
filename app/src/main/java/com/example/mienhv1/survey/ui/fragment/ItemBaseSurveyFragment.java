package com.example.mienhv1.survey.ui.fragment;

import android.view.View;

import com.example.mienhv1.survey.base.BaseFragment;
import com.example.mienhv1.survey.ui.adapter.EnumSurveyFragment;

/**
 * Created by Forev on 17/04/23.
 */

public abstract class ItemBaseSurveyFragment extends BaseFragment {
    public EnumSurveyFragment fType;

    public abstract EnumSurveyFragment fragmentType();

    public EnumSurveyFragment getFragmentType() {
        return fType;
    }
}

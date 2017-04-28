package com.example.mienhv1.survey.ui.fragment;

import com.example.datasource.model.ItemQuestionModel;
import com.example.mienhv1.survey.Constants;
import com.example.mienhv1.survey.base.BaseFragment;
import com.example.mienhv1.survey.ui.adapter.EnumSurveyFragment;


/**
 * Created by Forev on 17/04/23.
 */

public abstract class ItemBaseSurveyFragment extends BaseFragment implements ItemBaseSurveyView {
    public EnumSurveyFragment fType;
    protected ItemBaseSurveyPresenter presenter = new ItemBaseSurveyPresenter(getActivity(),this);

    public abstract EnumSurveyFragment fragmentType();

    public EnumSurveyFragment getFragmentType() {
        return fType;
    }



    @Override
    protected void initData() {
        ItemQuestionModel item = getArguments().getParcelable(Constants.ARG_ITEM_SURVEY);
        presenter.initUserCase(item.id);
    }

}

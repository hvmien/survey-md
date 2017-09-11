package com.example.mienhv1.survey.ui.fragment;

import android.app.Activity;

import com.example.datasource.model.AnswerModel;
import com.example.datasource.model.ItemQuestionModel;
import com.example.datasource.model.SurveyPreModel;
import com.example.mienhv1.survey.Constants;
import com.example.mienhv1.survey.base.BaseFragment;
import com.example.mienhv1.survey.ui.adapter.EnumSurveyFragment;


/**
 * Created by Forev on 17/04/23.
 */

public abstract class ItemBaseSurveyFragment<T> extends BaseFragment implements ItemBaseSurveyView {
    public EnumSurveyFragment fType;
    protected ItemBaseSurveyPresenter presenter = new ItemBaseSurveyPresenter(getActivity(), this);

    public abstract EnumSurveyFragment fragmentType();

    public EnumSurveyFragment getFragmentType() {
        return fType;
    }

    public abstract boolean checkData();
    public abstract AnswerModel<T> getDataFromUserHandle();

    public OnFragmentInteractionListener mListener;

    @Override
    protected void initData() {
        ItemQuestionModel item = getArguments().getParcelable(Constants.ARG_ITEM_SURVEY);
        presenter.initUserCase(item.id);
    }
    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(ItemQuestionModel item,int id);
    }
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) activity;
        } else {
            throw new RuntimeException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

}

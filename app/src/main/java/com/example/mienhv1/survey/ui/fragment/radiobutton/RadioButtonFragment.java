package com.example.mienhv1.survey.ui.fragment.radiobutton;

import android.os.Bundle;
import android.view.View;

import com.example.datasource.model.DataResponse;
import com.example.datasource.model.ItemAttributeModel;
import com.example.datasource.model.ItemQuestionModel;
import com.example.datasource.repository.DataRepository;
import com.example.datasource.repository.DataRepositoryFactory;
import com.example.datasource.usercases.GetSurveyAttributeUsercase;
import com.example.mienhv1.survey.Constants;
import com.example.mienhv1.survey.R;
import com.example.mienhv1.survey.ui.adapter.EnumSurveyFragment;
import com.example.mienhv1.survey.ui.fragment.ItemBaseSurveyFragment;
import com.example.mienhv1.survey.utils.view.CSRadioGroup;
import com.example.mienhv1.survey.utils.view.CSTextView;

import java.util.ArrayList;

/**
 * Created by HVM on 4/23/2017.
 */

public class RadioButtonFragment extends ItemBaseSurveyFragment {
    private CSTextView txtTitle;
    private CSRadioGroup csRadioGroupParent;
    private GetSurveyAttributeUsercase getSurveyAttributeUsercase;

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
        txtTitle = (CSTextView) view.findViewById(R.id.txt_title_radio_group);
        csRadioGroupParent = (CSRadioGroup) view.findViewById(R.id.gp_data);
    }

    @Override
    protected void initData() {
        ItemQuestionModel item = getArguments().getParcelable(Constants.ARG_ITEM_SURVEY);
        txtTitle.setText(item.title);
        initUserCase(item.id);
    }

    private void initUserCase(int id) {
        DataRepository data = DataRepositoryFactory.createDataRepository(getActivity());
        getSurveyAttributeUsercase = new GetSurveyAttributeUsercase(data);
        GetSurveyAttributeUsercase.RequestValue requestValue = new GetSurveyAttributeUsercase.RequestValue(id);
        getSurveyAttributeUsercase.execute(new SurveyAttributeObserver(), requestValue);
    }

    @Override
    protected void destroyView() {

    }

    @Override
    public EnumSurveyFragment fragmentType() {
        return EnumSurveyFragment.RadioButton;
    }

    private class SurveyAttributeObserver extends io.reactivex.observers.DisposableObserver<com.example.datasource.model.DataResponse<ItemAttributeModel>> {
        @Override
        public void onNext(DataResponse<ItemAttributeModel> itemAttributeModelDataResponse) {
            if (itemAttributeModelDataResponse.data != null) {
                //goi toi api /table_attritute params{table_id} lay table_id trong getArguments()
                ArrayList mList = new ArrayList<>();
                mList.addAll(itemAttributeModelDataResponse.data);
                RadioButtonAdapter ckA = new RadioButtonAdapter(getActivity(), R.layout.item_radio_button, csRadioGroupParent, mList);
                csRadioGroupParent.setAdapter(ckA);
            }
        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onComplete() {

        }
    }
}

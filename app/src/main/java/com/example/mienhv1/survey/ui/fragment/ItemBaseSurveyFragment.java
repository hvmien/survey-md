package com.example.mienhv1.survey.ui.fragment;

import android.view.View;

import com.example.datasource.model.AddressModel;
import com.example.datasource.model.DataResponse;
import com.example.datasource.model.ItemAttributeModel;
import com.example.datasource.model.ItemQuestionModel;
import com.example.datasource.repository.DataRepository;
import com.example.datasource.repository.DataRepositoryFactory;
import com.example.datasource.usercases.GetSurveyAttributeUsercase;
import com.example.mienhv1.survey.Constants;
import com.example.mienhv1.survey.R;
import com.example.mienhv1.survey.base.BaseFragment;
import com.example.mienhv1.survey.ui.adapter.EnumSurveyFragment;
import com.example.mienhv1.survey.ui.fragment.radiobutton.RadioButtonFragment;
import com.example.mienhv1.survey.utils.view.CSRadioGroup;
import com.example.mienhv1.survey.utils.view.CSTextView;

import java.util.ArrayList;

/**
 * Created by Forev on 17/04/23.
 */

public abstract class ItemBaseSurveyFragment extends BaseFragment {
    public EnumSurveyFragment fType;

    public abstract EnumSurveyFragment fragmentType();

    public EnumSurveyFragment getFragmentType() {
        return fType;
    }

    private GetSurveyAttributeUsercase getSurveyAttributeUsercase;

    @Override
    protected void initData() {
        ItemQuestionModel item = getArguments().getParcelable(Constants.ARG_ITEM_SURVEY);
        initUserCase(item.id);
    }

    private void initUserCase(int id) {
        DataRepository data = DataRepositoryFactory.createDataRepository(getActivity());
        getSurveyAttributeUsercase = new GetSurveyAttributeUsercase(data);
        GetSurveyAttributeUsercase.RequestValue requestValue = new GetSurveyAttributeUsercase.RequestValue(id);
        getSurveyAttributeUsercase.execute(new ItemBaseSurveyFragment.SurveyAttributeObserver(), requestValue);
    }


    private class SurveyAttributeObserver extends io.reactivex.observers.DisposableObserver<com.example.datasource.model.DataResponse<ItemAttributeModel>> {
        @Override
        public void onNext(DataResponse<ItemAttributeModel> itemAttributeModelDataResponse) {
            if (itemAttributeModelDataResponse.data != null) {
                onDataListen(itemAttributeModelDataResponse.data);
            }
        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onComplete() {

        }
    }

    protected  void onDataListen(ArrayList<ItemAttributeModel> data){}

}

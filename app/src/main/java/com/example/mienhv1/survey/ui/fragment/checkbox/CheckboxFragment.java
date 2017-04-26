package com.example.mienhv1.survey.ui.fragment.checkbox;

import android.os.Bundle;
import android.view.View;

import com.example.datasource.model.CheckboxModel;
import com.example.datasource.model.DataResponse;
import com.example.datasource.model.ItemAttributeModel;
import com.example.datasource.model.ItemQuestionModel;
import com.example.datasource.repository.DataRepository;
import com.example.datasource.repository.DataRepositoryFactory;
import com.example.datasource.usercases.GetSurveyAttributeUsercase;
import com.example.datasource.utils.Constant;
import com.example.mienhv1.survey.Constants;
import com.example.mienhv1.survey.R;
import com.example.mienhv1.survey.ui.adapter.EnumSurveyFragment;
import com.example.mienhv1.survey.ui.fragment.ItemBaseSurveyFragment;
import com.example.mienhv1.survey.ui.fragment.radiobutton.RadioButtonAdapter;
import com.example.mienhv1.survey.ui.fragment.radiobutton.RadioButtonFragment;
import com.example.mienhv1.survey.utils.view.CSGroupCheckbox;
import com.example.mienhv1.survey.utils.view.CSTextView;

import java.util.ArrayList;

/**
 * Created by HVM on 4/23/2017.
 */

public class CheckboxFragment extends ItemBaseSurveyFragment {
    private CSTextView txtTitle;
    private CSGroupCheckbox grCheckboxParent;
    private GetSurveyAttributeUsercase getSurveyAttributeUsercase;

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
        txtTitle = (CSTextView) view.findViewById(R.id.txt_title);
        grCheckboxParent = (CSGroupCheckbox) view.findViewById(R.id.gr_checkbox);


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
        return EnumSurveyFragment.CheckBox;
    }
    private class SurveyAttributeObserver extends io.reactivex.observers.DisposableObserver<com.example.datasource.model.DataResponse<ItemAttributeModel>> {
        @Override
        public void onNext(DataResponse<ItemAttributeModel> itemAttributeModelDataResponse) {
            if (itemAttributeModelDataResponse.data != null) {
                //goi toi api /table_attritute params{table_id} lay table_id trong getArguments()
                ArrayList mList = new ArrayList<>();
                mList.addAll(itemAttributeModelDataResponse.data);
                CheckboxAdapter ckA = new CheckboxAdapter(getActivity(), R.layout.item_check_box_view, grCheckboxParent, mList);
                grCheckboxParent.setAdapter(ckA);
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

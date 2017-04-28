package com.example.mienhv1.survey.ui.fragment;

import android.app.Activity;
import android.support.v4.app.FragmentActivity;

import com.example.datasource.model.DataResponse;
import com.example.datasource.model.ItemAttributeModel;
import com.example.datasource.repository.DataRepository;
import com.example.datasource.repository.DataRepositoryFactory;
import com.example.datasource.usercases.GetSurveyAttributeUsercase;
import com.example.mienhv1.survey.base.BasePresenter;

import io.reactivex.observers.DisposableObserver;

/**
 * Created by MienHV1 on 4/28/2017.
 */

public class ItemBaseSurveyPresenter implements BasePresenter {
    private GetSurveyAttributeUsercase getSurveyAttributeUsercase;
    Activity activity;
    ItemBaseSurveyView mView;
    public ItemBaseSurveyPresenter(FragmentActivity activity, ItemBaseSurveyView mview) {
        this.activity = activity;
        mView= mview;
    }

    public void initUserCase(int id) {
        mView.showProgress();
        DataRepository data = DataRepositoryFactory.createDataRepository((activity));
        getSurveyAttributeUsercase = new GetSurveyAttributeUsercase(data);
        GetSurveyAttributeUsercase.RequestValue requestValue = new GetSurveyAttributeUsercase.RequestValue(id);
        getSurveyAttributeUsercase.execute(new SurveyAttributeObserver(), requestValue);
    }

    private class SurveyAttributeObserver extends DisposableObserver<DataResponse<ItemAttributeModel>> {
        @Override
        public void onNext(DataResponse<ItemAttributeModel> itemAttributeModelDataResponse) {
            mView.hideProgress();
            if (itemAttributeModelDataResponse.data != null) {

                mView.onGetDataListenner(itemAttributeModelDataResponse.data);
            }
        }

        @Override
        public void onError(Throwable e) {
            mView.hideProgress();
            mView.showError(e.getMessage());
        }

        @Override
        public void onComplete() {
            mView.hideProgress();
        }
    }


    @Override
    public void create() {

    }

    @Override
    public void start() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void stop() {

    }

    @Override
    public void destroy() {

    }
}

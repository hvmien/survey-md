package com.example.mienhv1.survey.ui.home;

import android.content.Context;
import android.widget.Toast;

import com.example.datasource.model.DataResponse;
import com.example.datasource.model.ItemQuestionModel;
import com.example.datasource.repository.DataRepository;
import com.example.datasource.repository.DataRepositoryFactory;
import com.example.datasource.usercases.GetSurveyQuestionUserCase;
import com.example.datasource.usercases.SignOutUserCase;
import com.example.mienhv1.survey.MyApplication;
import com.example.mienhv1.survey.base.BasePresenter;

import io.reactivex.observers.DisposableObserver;

/**
 * Created by MienHV1 on 4/12/2017.
 */

public class HomePresenter implements BasePresenter {

    private HomeView mHomeView;
    private Context mContext;

    private GetSurveyQuestionUserCase getSurveyQuestionUserCase;

    public HomePresenter(DataRepository dataRepository, HomeView homeView) {
        mHomeView = homeView;
        getSurveyQuestionUserCase = new GetSurveyQuestionUserCase(dataRepository);
        mContext = MyApplication.getInstance().getApplicationContext();
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
        getSurveyQuestionUserCase.dispose();
    }


    public void createDatabase(int id) {
        mHomeView.showProgress();
        if (id < 0)
            return;
        GetSurveyQuestionUserCase.RequestValue requestValue = new GetSurveyQuestionUserCase.RequestValue(id);
        getSurveyQuestionUserCase.execute(new GetSurveyQuestionObserver(), requestValue);
    }

    private class GetSurveyQuestionObserver extends DisposableObserver<DataResponse<ItemQuestionModel>> {
        @Override
        public void onNext(DataResponse<ItemQuestionModel> questionModelDataResponse) {
            mHomeView.hideProgress();
            if (questionModelDataResponse != null) {
                mHomeView.getListQuestion(questionModelDataResponse);
            }
        }

        @Override
        public void onError(Throwable e) {
            mHomeView.showError(e.getMessage());
            mHomeView.hideProgress();
        }

        @Override
        public void onComplete() {

        }
    }
}

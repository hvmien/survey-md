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
    private SignOutUserCase mSignOutUserCase;
    private GetSurveyQuestionUserCase getSurveyQuestionUserCase;

    public HomePresenter(DataRepository dataRepository, HomeView homeView) {
        mHomeView = homeView;
        mSignOutUserCase = new SignOutUserCase(dataRepository);
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

    }

    public void signOut() {
        mSignOutUserCase.execute(new SignOutObserver(), null);
    }

    public void createDatabase() {
        mHomeView.showProgress();
        getSurveyQuestionUserCase.execute(new GetSurveyQuestionObserver(), null);
    }

    private class SignOutObserver extends DisposableObserver<Boolean> {
        @Override
        public void onNext(Boolean aBoolean) {
            if (mHomeView != null) {
                mHomeView.navigateToLoginPage();
            }
        }

        @Override
        public void onError(Throwable e) {
            if (mHomeView != null) {
                mHomeView.showError(e.toString());
            }
        }

        @Override
        public void onComplete() {

        }
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

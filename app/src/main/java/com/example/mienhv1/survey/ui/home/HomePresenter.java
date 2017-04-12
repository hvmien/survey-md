package com.example.mienhv1.survey.ui.home;

import com.example.datasource.repository.DataRepository;
import com.example.datasource.usercases.SignOutUserCase;
import com.example.mienhv1.survey.base.BasePresenter;

import io.reactivex.observers.DisposableObserver;

/**
 * Created by MienHV1 on 4/12/2017.
 */

public class HomePresenter implements BasePresenter {

    private HomeView mHomeView;
    private SignOutUserCase mSignOutUserCase;

    public HomePresenter(DataRepository dataRepository, HomeView homeView) {
        mHomeView = homeView;
        mSignOutUserCase = new SignOutUserCase(dataRepository);
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
        mSignOutUserCase.execute(new SignOutObserver(),null);
    }

    private class SignOutObserver extends DisposableObserver<Boolean> {
        @Override
        public void onNext(Boolean aBoolean) {
            if(mHomeView!=null)
            {
                mHomeView.navigateToLoginPage();
            }
        }

        @Override
        public void onError(Throwable e) {
            if(mHomeView!=null){
                mHomeView.showError(e.toString());
            }
        }

        @Override
        public void onComplete() {

        }
    }
}

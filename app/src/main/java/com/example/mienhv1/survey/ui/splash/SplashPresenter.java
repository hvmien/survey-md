package com.example.mienhv1.survey.ui.splash;

import android.os.Handler;

import com.example.datasource.repository.DataRepository;
import com.example.mienhv1.survey.base.BasePresenter;


/**
 * Created by MienHV1 on 4/11/2017.
 */

public class SplashPresenter implements BasePresenter {

    private DataRepository mDataRepository;
    private SplashView mSplashView;

    public SplashPresenter(DataRepository dataRepository, SplashView splashView) {
        mDataRepository = dataRepository;
        mSplashView = splashView;
    }

    private void checkLogin() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if( mDataRepository.isSignedIn()){
                    mSplashView.navigateToLoginNoPassPage();
                }
                else {
                    mSplashView.navigateToLoginPage();
                }
            }
        },2000);
    }


    @Override
    public void create() {

    }

    @Override
    public void start() {
        checkLogin();
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

package com.example.mienhv1.survey.ui.splash;

import android.content.Intent;

import com.example.datasource.repository.DataRepository;
import com.example.datasource.repository.DataRepositoryFactory;
import com.example.mienhv1.survey.ui.home.HomeActivity;
import com.example.mienhv1.survey.R;
import com.example.mienhv1.survey.base.BaseActivity;
import com.example.mienhv1.survey.ui.loginnopass.LoginNoPassPresenter;

/**
 * Created by MienHV1 on 4/11/2017.
 */

public class SplashActivity extends BaseActivity implements SplashView{

    private SplashPresenter mSplashPresenter;
    private LoginNoPassPresenter mLoginNoPassPresenter;

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showError(String error) {

    }

    @Override
    public void navigateToLoginNoPassPage(String username) {
        //openLoginPage();
        //openLoginNoPassPage();
        mLoginNoPassPresenter.signInNoPass(username);
        finish();
    }

    @Override
    public void navigateToHomePage() {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void navigateToLoginPage() {
        openLoginPage();
        finish();
    }

    @Override
    protected void setScreenOrientation(boolean isTablet) {
        if (isTablet) {
            setLanscapeOrientation();
        } else {
            setPortraitOrientation();
        }
    }

    @Override
    protected int getResourcesLayout() {
        return R.layout.activity_splash;
    }

    @Override
    protected void mapView() {

    }

    @Override
    protected void initData() {
        DataRepository dataRepository = DataRepositoryFactory.createDataRepository(getApplicationContext());
        mSplashPresenter = new SplashPresenter(dataRepository,this);
        mLoginNoPassPresenter = new LoginNoPassPresenter(dataRepository,this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mSplashPresenter.start();
    }
}

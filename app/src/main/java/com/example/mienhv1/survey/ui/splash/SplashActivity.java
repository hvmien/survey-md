package com.example.mienhv1.survey.ui.splash;

import android.content.Intent;

import com.example.datasource.repository.DataRepository;
import com.example.datasource.repository.DataRepositoryFactory;
import com.example.mienhv1.survey.MainActivity;
import com.example.mienhv1.survey.R;
import com.example.mienhv1.survey.base.BaseActivity;

/**
 * Created by MienHV1 on 4/11/2017.
 */

public class SplashActivity extends BaseActivity implements SplashView{

    private SplashPresenter mSplashPresenter;

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
    public void navigateToHomePage() {
        Intent intent = new Intent(this, MainActivity.class);
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
    }

    @Override
    protected void onStart() {
        super.onStart();
        mSplashPresenter.start();
    }
}

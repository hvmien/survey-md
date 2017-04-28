package com.example.mienhv1.survey.ui.home;

import com.example.mienhv1.survey.R;
import com.example.mienhv1.survey.base.BaseActivity;

public class HomeActivity extends BaseActivity implements HomeFragment.OnHomeListener {


    @Override
    protected void setScreenOrientation(boolean isTablet) {

    }

    @Override
    protected int getResourcesLayout() {
        return R.layout.activity_home;
    }

    @Override
    protected void mapView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    public void onOpenLoginPage() {
        openLoginPage();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}

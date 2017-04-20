package com.example.mienhv1.survey.ui.loginnopass;

import com.example.mienhv1.survey.R;
import com.example.mienhv1.survey.base.BaseActivity;

/**
 * Created by HVM on 4/20/2017.
 */

public class LoginNoPassActivity extends BaseActivity {
    @Override
    protected void setScreenOrientation(boolean isTablet) {
        if(isTablet){
            setLanscapeOrientation();
        }else {
            setPortraitOrientation();
        }
    }

    @Override
    protected int getResourcesLayout() {
        return R.layout.activity_login_no_pass;
    }

    @Override
    protected void mapView() {

    }

    @Override
    protected void initData() {

    }
}

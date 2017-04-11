package com.example.mienhv1.survey.ui.login;

import com.example.mienhv1.survey.R;
import com.example.mienhv1.survey.base.BaseActivity;

/**
 * Created by MienHV1 on 4/11/2017.
 */

public class LoginActivity extends BaseActivity {
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
        return R.layout.activity_login;
    }

    @Override
    protected void mapView() {

    }

    @Override
    protected void initData() {

    }
}

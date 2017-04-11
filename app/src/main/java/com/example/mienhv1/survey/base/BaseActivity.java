package com.example.mienhv1.survey.base;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.mienhv1.survey.R;
import com.example.mienhv1.survey.ui.login.LoginActivity;

/**
 * Created by MienHV1 on 4/11/2017.
 */

public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setScreenOrientation(getResources().getBoolean(R.bool.is_tablet));
        setContentView(getResourcesLayout());
        mapView();
        initData();
    }
    protected abstract void setScreenOrientation(boolean isTablet);
    protected abstract int getResourcesLayout();
    protected abstract void mapView();
    protected abstract void initData();

    protected void openLoginPage(){
        Intent intent = new Intent(this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    protected void setPortraitOrientation(){
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }
    protected void setLanscapeOrientation(){
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
    }
}

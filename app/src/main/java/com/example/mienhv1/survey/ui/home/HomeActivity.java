package com.example.mienhv1.survey.ui.home;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.example.datasource.repository.DataRepository;
import com.example.datasource.repository.DataRepositoryFactory;
import com.example.datasource.usercases.SignOutUserCase;
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
        Toolbar toolbar = (Toolbar) findViewById(R.id.home_toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        toggle.setDrawerIndicatorEnabled(true);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

    }

    @Override
    protected void initData() {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setDisplayUseLogoEnabled(true);

            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
    }

    @Override
    public void onOpenLoginPage() {
        openLoginPage();
    }
}

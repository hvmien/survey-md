package com.example.mienhv1.survey.ui.fragment.store;

import android.os.Handler;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.widget.Toast;

import com.example.mienhv1.survey.R;
import com.example.mienhv1.survey.base.BaseActivity;
import com.example.mienhv1.survey.ui.fragment.DrawerMenuFragment;

/**
 * Created by MienHV1 on 4/28/2017.
 */

public class StoreActivity extends BaseActivity implements StoreFragment.OnStoreListener, DrawerMenuFragment.OnCallbackDataFromNavi {
    private DrawerLayout drawer;
    private boolean doubleBackToExitPressedOnce = false;

    @Override
    protected void setScreenOrientation(boolean isTablet) {

    }

    @Override
    protected int getResourcesLayout() {
        return R.layout.activity_store;
    }

    @Override
    protected void mapView() {
        final Toolbar toolbar = (Toolbar) findViewById(R.id.home_toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        toggle.setDrawerIndicatorEnabled(true);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        DrawerMenuFragment fragment = (DrawerMenuFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_drawer);
        fragment.setLogoutlayoutPress(this);
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
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(Gravity.LEFT);
        } else {
            if (doubleBackToExitPressedOnce) {
                super.onBackPressed();
                return;
            }

            this.doubleBackToExitPressedOnce = true;
            Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    doubleBackToExitPressedOnce = false;
                }
            }, 2000);
        }
    }

    @Override
    public void onOpenLoginPage() {
        openLoginPage();
    }

    @Override
    public void onLogoutPress() {
        StoreFragment fragment = (StoreFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_home);
        fragment.logoutPrensenter();
    }
}

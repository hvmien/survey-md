package com.example.mienhv1.survey;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

/**
 * Created by MienHV1 on 4/11/2017.
 */

public class MyApplication extends MultiDexApplication {

    private static MyApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        MultiDex.install(this);
        instance = this;
    }

    public static Context getInstance() {
        return instance;
    }
}

package com.example.mienhv1.survey;

import android.app.Application;
import android.content.Context;

/**
 * Created by MienHV1 on 4/11/2017.
 */

public class MyApplication extends Application {

    private static MyApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public static Context getInstance() {
        return instance;
    }
}

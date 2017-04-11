package com.example.datasource.source.preference;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by MienHV1 on 4/11/2017.
 */

public class PreferenceHelper {
    private static final String NAME = "MyApp";
    private static final String USER_KEY = "user_survey";
    private static PreferenceHelper INSTANCE;
    private SharedPreferences mSharedPreferences;

    public PreferenceHelper(Context context) {
        mSharedPreferences = context.getApplicationContext().getSharedPreferences(NAME,Context.MODE_PRIVATE);
    }

    public synchronized static PreferenceHelper getInstance(Context context) {
        if(INSTANCE==null)
            INSTANCE = new PreferenceHelper(context);
        return INSTANCE;
    }

    public String getUserName() {
        return mSharedPreferences.getString(USER_KEY,"");
    }

    public void saveUser(String accountId) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(USER_KEY, accountId);
        editor.apply();
    }

    public void saveProfileId(String profileId) {

    }
}

package com.example.datasource.repository;

import android.content.Context;

import com.example.datasource.source.preference.PreferenceHelper;
import com.example.datasource.source.remote.RemoteDataSource;

/**
 * Created by MienHV1 on 4/11/2017.
 */

public class DataRepositoryFactory {
    public static DataRepository createDataRepository(Context context){
        RemoteDataSource remoteDataSource = RemoteDataSource.getInstance(context);
        PreferenceHelper preferenceHelper = PreferenceHelper.getInstance(context);
        return DataRepository.getInstance(remoteDataSource,preferenceHelper);
    }
}

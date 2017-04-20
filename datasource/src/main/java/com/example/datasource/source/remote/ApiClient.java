package com.example.datasource.source.remote;

import com.example.datasource.utils.Constant;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by HVM on 4/18/2017.
 */

public class ApiClient {
    private static Retrofit INSTANCE;
    public synchronized static Retrofit getClient(){
        if(INSTANCE==null){
            INSTANCE = new Retrofit.Builder()
                    .baseUrl(Constant.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return INSTANCE;
    }
}

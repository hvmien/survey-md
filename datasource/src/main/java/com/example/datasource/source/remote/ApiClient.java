package com.example.datasource.source.remote;

import com.example.datasource.utils.Constant;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by HVM on 4/18/2017.
 */

public class ApiClient {
    private static Retrofit INSTANCE;
    public static Gson returnGson(){
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        return gson;
    }
    public synchronized static Retrofit getClient(){

        if(INSTANCE==null){
            INSTANCE = new Retrofit.Builder()
                    .baseUrl(Constant.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(returnGson()))
                    .client(getRequestHeader())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        }
        return INSTANCE;
    }
    private static OkHttpClient getRequestHeader() {
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build();

        return client;
    }
}

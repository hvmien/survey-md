package com.example.datasource.source.remote;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by HVM on 4/18/2017.
 */

public interface ApiInterface {
    @GET
    Call<String> getDatabase();
}

package com.example.datasource.source.remote;

import com.example.datasource.model.DataLoginResponse;
import com.example.datasource.model.User;

import io.reactivex.Observable;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by HVM on 4/18/2017.
 */

public interface ApiInterface {
    @GET
    Call<String> getDatabase();

    @FormUrlEncoded
    @POST("login")
    Observable<DataLoginResponse<User>> signIn(@Field("username") String username, @Field("password") String password, @Field("devicesid") String devicesid);

    @FormUrlEncoded
    @POST("login_no_pass")
    Observable<DataLoginResponse<User>> signInNoPass(@Field("username")String username ,@Field("devicesid")String deviesid);
}

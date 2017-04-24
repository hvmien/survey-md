package com.example.datasource.source.remote;

import com.example.datasource.model.DataLoginResponse;
import com.example.datasource.model.DataResponse;
import com.example.datasource.model.ImageRespone;
import com.example.datasource.model.User;

import java.util.ArrayList;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * Created by HVM on 4/18/2017.
 */

public interface ApiInterface {
    @GET
    Call<String> getDatabase();

    @FormUrlEncoded
    @POST("login")
    Observable<DataLoginResponse<User>> signIn(@Field("username") String username, @Field("password") String password, @Field("deviceid") String deviceid);

    @FormUrlEncoded
    @POST("login_no_pass")
    Observable<DataLoginResponse<User>> signInNoPass(@Field("username")String username ,@Field("deviceid")String deviceid);

    @Multipart
    @POST("upload_images")
    Observable<DataResponse<ImageRespone>> uploadImageFileMutilPart(@Part ArrayList<MultipartBody.Part> files);
}

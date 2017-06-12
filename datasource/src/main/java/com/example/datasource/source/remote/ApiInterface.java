package com.example.datasource.source.remote;

import com.example.datasource.model.DataLoginResponse;
import com.example.datasource.model.DataResponse;
import com.example.datasource.model.DistrictModel;
import com.example.datasource.model.ImageRespone;
import com.example.datasource.model.ItemAttributeModel;
import com.example.datasource.model.ItemQuestionModel;
import com.example.datasource.model.ProvinceModel;
import com.example.datasource.model.StoreSystem;
import com.example.datasource.model.User;
import com.example.datasource.model.WardModel;

import java.util.ArrayList;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
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

    @POST("list_store")
    Observable<DataResponse<StoreSystem>> getListStore();

    @POST("survey")
    Observable<DataResponse<ItemQuestionModel>> getDatabaseQuestion();

    @FormUrlEncoded
    @POST("login")
    Observable<DataLoginResponse<User>> signIn(@Field("username") String username, @Field("password") String password, @Field("deviceid") String deviceid);

    @FormUrlEncoded
    @POST("login_no_pass")
    Observable<DataLoginResponse<User>> signInNoPass(@Field("username")String username ,@Field("deviceid")String deviceid);

    @Multipart
    @POST("upload_images")
    Observable<DataResponse<ImageRespone>> uploadImageFileMutilPart(@Part ArrayList<MultipartBody.Part> files);

    @GET("list_province")
    Observable<DataResponse<ProvinceModel>> getListProvince();

    @FormUrlEncoded
    @POST("list_district")
    Observable<DataResponse<DistrictModel>> getDistrictViaProvince(@Field("province_id")String provinceid);

    @FormUrlEncoded
    @POST("list_ward")
    Observable<DataResponse<WardModel>> getWardViaDistrict(@Field("district_id") String districtid);

    @FormUrlEncoded
    @POST("table_attritute")
    Observable<DataResponse<ItemAttributeModel>> getSurveyAttribute(@Field("table_id")int tableid);
}

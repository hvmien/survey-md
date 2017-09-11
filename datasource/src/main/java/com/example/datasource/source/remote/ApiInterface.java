package com.example.datasource.source.remote;

import com.example.datasource.model.DataAnswerText;
import com.example.datasource.model.DataAnswerTextModel;
import com.example.datasource.model.DataLoginResponse;
import com.example.datasource.model.DataResponse;
import com.example.datasource.model.DistrictModel;
import com.example.datasource.model.ImageRespone;
import com.example.datasource.model.ItemAttributeModel;
import com.example.datasource.model.ItemQuestionModel;
import com.example.datasource.model.ProvinceModel;
import com.example.datasource.model.ResponeDataText;
import com.example.datasource.model.StoreSystem;
import com.example.datasource.model.User;
import com.example.datasource.model.WardModel;

import java.util.ArrayList;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.http.Body;
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

    @FormUrlEncoded
    @POST("list_topic_user")
    Observable<DataResponse<StoreSystem>> getListStore(@Field("username") String username);

    @FormUrlEncoded
    @POST("list_question")
    Observable<DataResponse<ItemQuestionModel>> getDatabaseQuestion(@Field("id_topic") int idtopic);

    @FormUrlEncoded
    @POST("login")
    Observable<DataLoginResponse<User>> signIn(@Field("username") String username, @Field("password") String password, @Field("deviceid") String deviceid);

    @FormUrlEncoded
    @POST("login_no_pass")
    Observable<DataLoginResponse<User>> signInNoPass(@Field("username")String username ,@Field("deviceid")String deviceid);

    @Multipart
    @POST("upload_images")
    Observable<DataResponse<ImageRespone>> uploadImageFileMutilPart(@Part ArrayList<MultipartBody.Part> files);

    @GET("list_provinces")
    Observable<DataResponse<ProvinceModel>> getListProvince();

    @FormUrlEncoded
    @POST("list_district")
    Observable<DataResponse<DistrictModel>> getDistrictViaProvince(@Field("province_id")String provinceid);

    @FormUrlEncoded
    @POST("list_ward")
    Observable<DataResponse<WardModel>> getWardViaDistrict(@Field("district_id") String districtid);

    @FormUrlEncoded
    @POST("question_meta")
    Observable<DataResponse<ItemAttributeModel>> getSurveyAttribute(@Field("id_question")int tableid);

    @POST("anwser_result")
    Observable<DataResponse<ResponeDataText>> uploadDataText(@Body DataAnswerTextModel data);
}

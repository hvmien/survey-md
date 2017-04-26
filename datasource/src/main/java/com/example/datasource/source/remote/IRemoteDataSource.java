package com.example.datasource.source.remote;

import com.example.datasource.model.DataLoginResponse;
import com.example.datasource.model.DataResponse;
import com.example.datasource.model.DistrictModel;
import com.example.datasource.model.ImageRespone;
import com.example.datasource.model.ProvinceModel;
import com.example.datasource.model.SurveyAttributeModel;
import com.example.datasource.model.SurveyTableModel;
import com.example.datasource.model.User;
import com.example.datasource.model.WardModel;

import java.util.ArrayList;

import io.reactivex.Observable;
import okhttp3.MultipartBody;

/**
 * Created by MienHV1 on 4/11/2017.
 */

public interface IRemoteDataSource {

    Observable<DataLoginResponse<User>> signIn(String email, String password,String devicesid);

    Observable<Boolean> signOut();

    Observable<DataResponse<SurveyTableModel>> getDatabaseQuestion();

    Observable<DataLoginResponse<User>> signInNoPass(String email,String devicesid);

    Observable<DataResponse<ImageRespone>> upLoadImageFilesMutilPart(ArrayList<MultipartBody.Part> files);

    Observable<DataResponse<ProvinceModel>> getListProvince();

    Observable<DataResponse<DistrictModel>> getDistrictViaProvince(String provinceid);

    Observable<DataResponse<WardModel>> getWardViaDistrict(String districtid);

    Observable<DataResponse<SurveyAttributeModel>> getSurveyAttribute(int tableid);
}

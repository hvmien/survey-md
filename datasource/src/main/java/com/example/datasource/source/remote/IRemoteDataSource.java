package com.example.datasource.source.remote;

import com.example.datasource.model.DataLoginResponse;
import com.example.datasource.model.Model;
import com.example.datasource.model.User;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by MienHV1 on 4/11/2017.
 */

public interface IRemoteDataSource {

    Observable<DataLoginResponse<User>> signIn(String email, String password,String devicesid);

    Observable<Boolean> signOut();

    Observable<List<Model>> getDatabase();

    Observable<DataLoginResponse<User>> signInNoPass(String email,String devicesid);
}

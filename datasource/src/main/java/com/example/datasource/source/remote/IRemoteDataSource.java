package com.example.datasource.source.remote;

import com.example.datasource.model.User;

import io.reactivex.Observable;

/**
 * Created by MienHV1 on 4/11/2017.
 */

public interface IRemoteDataSource {
    Observable<User> signIn(String email, String password);
}

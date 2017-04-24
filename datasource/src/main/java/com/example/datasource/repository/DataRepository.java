package com.example.datasource.repository;

import android.text.TextUtils;

import com.example.datasource.model.DataLoginResponse;
import com.example.datasource.model.DataResponse;
import com.example.datasource.model.ImageRespone;
import com.example.datasource.model.Model;
import com.example.datasource.model.User;
import com.example.datasource.source.preference.PreferenceHelper;
import com.example.datasource.source.remote.IRemoteDataSource;
import com.example.datasource.source.remote.RemoteDataSource;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.MultipartBody;

/**
 * Created by MienHV1 on 4/11/2017.
 */

public class DataRepository {
    private static DataRepository INSTANCE;

    private PreferenceHelper mPreferenceHelper;
    private IRemoteDataSource mRemoteDataSource;

    public DataRepository(IRemoteDataSource remoteDataSource, PreferenceHelper preferenceHelper) {
        mRemoteDataSource = remoteDataSource;
        mPreferenceHelper = preferenceHelper;
    }

    public static DataRepository getInstance(RemoteDataSource remoteDataSource, PreferenceHelper preferenceHelper) {
        if (INSTANCE == null)
            INSTANCE = new DataRepository(remoteDataSource, preferenceHelper);
        return INSTANCE;
    }

    public boolean isSignedIn() {
        String username = mPreferenceHelper.getUserName();
        return !TextUtils.isEmpty(username);
    }

    public String getUserName() {
        String username = mPreferenceHelper.getUserName();
        if (!TextUtils.isEmpty(username)) {
            return username;
        }
        return null;
    }

    public Observable<DataLoginResponse<User>> signIn(String email, String password, String devicesid) {
        return mRemoteDataSource.signIn(email, password, devicesid);
    }

    public Observable<DataLoginResponse<User>> signInNoPass(String email, String devicesid) {
        return mRemoteDataSource.signInNoPass(email, devicesid);
    }


    public void saveUser(String username) {
        mPreferenceHelper.saveUser(username);
    }

    public void clearUser() {
        mPreferenceHelper.clearUser();
    }

    public Observable<Boolean> signOut() {
        return mRemoteDataSource.signOut();
    }

    public Observable<List<Model>> getDatabase() {
        return mRemoteDataSource.getDatabase();
    }

    public Observable<DataResponse<ImageRespone>> upLoadImageFilesMutilPart(MultipartBody.Part responseBody) {
        return mRemoteDataSource.upLoadImageFilesMutilPart(responseBody);
    }
}

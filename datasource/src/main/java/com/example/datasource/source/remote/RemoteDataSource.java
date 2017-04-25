package com.example.datasource.source.remote;

import android.content.Context;

import com.example.datasource.model.DataLoginResponse;
import com.example.datasource.model.DataResponse;
import com.example.datasource.model.DistrictModel;
import com.example.datasource.model.ImageRespone;
import com.example.datasource.model.Model;
import com.example.datasource.model.ProvinceModel;
import com.example.datasource.model.User;
import com.example.datasource.model.WardModel;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import okhttp3.MultipartBody;

/**
 * Created by MienHV1 on 4/11/2017.
 */

public class RemoteDataSource implements IRemoteDataSource {

    private static RemoteDataSource INSTANCE;
    private Context mContext;
    private ApiInterface mApiInterface;

    public RemoteDataSource() {
    }

    public RemoteDataSource(Context context) {
        mContext = context.getApplicationContext();
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);
    }

    public synchronized static RemoteDataSource getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new RemoteDataSource(context);
        }
        return INSTANCE;
    }

    @Override
    public Observable<DataLoginResponse<User>> signIn(final String email, String password,String devicesid) {
        User a = new User();
        a.username = email;
        a.password = password;
        a.deviceid = devicesid;
        Observable<DataLoginResponse<User>> result = mApiInterface.signIn(a.username,a.password,a.deviceid);
        return result;
    }

    @Override
    public Observable<Boolean> signOut() {
        return Observable.create(new ObservableOnSubscribe<Boolean>() {

            @Override
            public void subscribe(ObservableEmitter<Boolean> e) throws Exception {
                e.onNext(true);
                e.onComplete();
            }
        });
    }

    @Override
    public Observable<List<Model>> getDatabase() {
        return Observable.create(new ObservableOnSubscribe<List<Model>>() {
            @Override
            public void subscribe(ObservableEmitter<List<Model>> e) throws Exception {
                List<Model> mList = new ArrayList<Model>();
                Model model = new Model();
                model.name = "abc";
                mList.add(model);
                e.onNext(mList);
                e.onComplete();
                return;
            }
        });
    }

    @Override
    public Observable<DataLoginResponse<User>> signInNoPass(String email,String devicesid) {
        User a = new User();
        a.username = email;
        a.deviceid = devicesid;
        Observable<DataLoginResponse<User>> result = mApiInterface.signInNoPass(a.username,a.deviceid);
        return result;
    }

    @Override
    public Observable<DataResponse<ImageRespone>> upLoadImageFilesMutilPart(ArrayList<MultipartBody.Part> files) {
        return mApiInterface.uploadImageFileMutilPart(files);
    }

    @Override
    public Observable<DataResponse<ProvinceModel>> getListProvince() {
        return mApiInterface.getListProvince();
    }

    @Override
    public Observable<DataResponse<DistrictModel>> getDistrictViaProvince(String provinceid) {
        return mApiInterface.getDistrictViaProvince(provinceid);
    }

    @Override
    public Observable<DataResponse<WardModel>> getWardViaDistrict(String districtid) {
        return mApiInterface.getWardViaDistrict(districtid);
    }
}

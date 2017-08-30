package com.example.mienhv1.survey.ui.fragment.upload;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.datasource.model.DataResponse;
import com.example.datasource.model.ImageRespone;
import com.example.datasource.repository.DataRepository;
import com.example.datasource.repository.DataRepositoryFactory;
import com.example.datasource.usercases.UpLoadImageFileUserCase;
import com.example.mienhv1.survey.MyApplication;
import com.example.mienhv1.survey.base.BasePresenter;

import java.util.ArrayList;

import io.reactivex.observers.DisposableObserver;
import okhttp3.MultipartBody;

/**
 * Created by HVM on 4/29/2017.
 */

public class UploadPresenter implements BasePresenter {
    private DataRepository mDataRepository;
    private UploadView uploadView;
    private UpLoadImageFileUserCase upLoadImageFileUserCase;
    private Context mContext;

    public UploadPresenter(DataRepository dataRepository, UploadView uploadview) {
        uploadView = uploadview;
        mDataRepository = dataRepository;
        mContext = MyApplication.getInstance().getApplicationContext();
    }

    public void uploadImage(ArrayList<MultipartBody.Part> files) {
        uploadView.showProgress();
        DataRepository dataRepository = DataRepositoryFactory.createDataRepository(mContext);
        upLoadImageFileUserCase = new UpLoadImageFileUserCase(dataRepository);
        UpLoadImageFileUserCase.RequestValue requestValue = new UpLoadImageFileUserCase.RequestValue(files);
        upLoadImageFileUserCase.execute(new UpLoadImageFileObserver(), requestValue);
    }

    @Override
    public void create() {

    }

    @Override
    public void start() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void stop() {

    }

    @Override
    public void destroy() {
        if (upLoadImageFileUserCase != null)
            upLoadImageFileUserCase.dispose();
    }

    private class UpLoadImageFileObserver extends DisposableObserver<DataResponse<ImageRespone>> {
        @Override
        public void onNext(DataResponse<ImageRespone> responseBody) {
            uploadView.hideProgress();
            if (uploadView != null) {

//                ArrayList<ImageRespone> list = responseBody.data;
//                if (list != null && list.size() > 0) {
//                    for (int i = 0; i < list.size(); i++) {
//                        Log.d("OnNext", list.get(i).image + " image upload thanh cong");
//                    }
//                }
                uploadView.onSuccessUploadImage(responseBody.msg);
            }
        }

        @Override
        public void onError(Throwable e) {
            uploadView.hideProgress();
//            uploadView.showError(e.getMessage());
            uploadView.onErrorUploadImage(e.getMessage());
        }

        @Override
        public void onComplete() {
            uploadView.hideProgress();
        }
    }
}

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

import java.util.ArrayList;

import io.reactivex.observers.DisposableObserver;
import okhttp3.MultipartBody;

/**
 * Created by HVM on 4/29/2017.
 */

public class UploadPresenter {
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

                Toast.makeText(mContext, "onNext: " + responseBody.msg+ " hinh upload thanh cong", Toast.LENGTH_LONG).show();
            }
        }

        @Override
        public void onError(Throwable e) {
            uploadView.hideProgress();
            uploadView.showError(e.getMessage());
        }

        @Override
        public void onComplete() {
            uploadView.hideProgress();
        }
    }
}

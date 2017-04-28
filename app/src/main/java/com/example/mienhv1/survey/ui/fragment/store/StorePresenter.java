package com.example.mienhv1.survey.ui.fragment.store;

import android.content.Context;

import com.example.datasource.model.DataResponse;
import com.example.datasource.model.StoreSystem;
import com.example.datasource.repository.DataRepository;
import com.example.datasource.usercases.GetListStoreUserCase;
import com.example.mienhv1.survey.MyApplication;
import com.example.mienhv1.survey.base.BasePresenter;

import io.reactivex.observers.DisposableObserver;

/**
 * Created by Forev on 17/04/18.
 */

public class StorePresenter implements BasePresenter {
    Context mContext;
    GetListStoreUserCase getListStoreUserCase;
    DataRepository dataRepository;
    StoreView storeView;

    public StorePresenter(DataRepository data, StoreView view) {
        this.storeView = view;
        this.dataRepository = data;
        getListStoreUserCase = new GetListStoreUserCase(this.dataRepository);
        mContext= MyApplication.getInstance().getApplicationContext();
    }


    @Override
    public void create() {
//        ArrayList<StoreSystem> data = new ArrayList<>();
//        data.add(new StoreSystem());
//        data.add(new StoreSystem());
//        data.add(new StoreSystem());
//        storeView.initStoreData(data);
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

    }

    public void getListStoreForRecyclerView() {
        storeView.showProgress();
        getListStoreUserCase.execute(new GetListStoreObserver(),null);
    }

    private class GetListStoreObserver extends DisposableObserver<DataResponse<StoreSystem>> {
        @Override
        public void onNext(DataResponse<StoreSystem> listStoreModelDataResponse) {
            storeView.hideProgress();
            if(listStoreModelDataResponse.data!=null&&listStoreModelDataResponse.data.size()>0){
                storeView.initStoreData(listStoreModelDataResponse.data);
            }
        }

        @Override
        public void onError(Throwable e) {
        storeView.showError(e.getMessage());
        }

        @Override
        public void onComplete() {
            storeView.hideProgress();
        }
    }
}

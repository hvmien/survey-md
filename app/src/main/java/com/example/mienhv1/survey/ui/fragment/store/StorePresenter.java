package com.example.mienhv1.survey.ui.fragment.store;

import android.content.Context;

import com.example.datasource.model.DataResponse;
import com.example.datasource.model.StoreSystem;
import com.example.datasource.repository.DataRepository;
import com.example.datasource.repository.DataRepositoryFactory;
import com.example.datasource.usercases.GetListStoreUserCase;
import com.example.datasource.usercases.SignOutUserCase;
import com.example.mienhv1.survey.MyApplication;
import com.example.mienhv1.survey.base.BasePresenter;
import com.example.mienhv1.survey.ui.home.HomePresenter;

import io.reactivex.observers.DisposableObserver;

/**
 * Created by Forev on 17/04/18.
 */

public class StorePresenter implements BasePresenter {
    Context mContext;
    private SignOutUserCase mSignOutUserCase;
    GetListStoreUserCase getListStoreUserCase;
    DataRepository dataRepository;
    StoreView storeView;

    public StorePresenter(DataRepository data, StoreView view) {
        this.storeView = view;
        this.dataRepository = data;
        mSignOutUserCase = new SignOutUserCase(dataRepository);
        getListStoreUserCase = new GetListStoreUserCase(this.dataRepository);
        mContext = MyApplication.getInstance().getApplicationContext();
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
        getListStoreUserCase.dispose();
    }


    public void signOut() {
        mSignOutUserCase.execute(new SignOutObserver(), null);
    }

    public void getListStoreForRecyclerView() {
        storeView.showProgress();
        DataRepository dataRepository = DataRepositoryFactory.createDataRepository(MyApplication.getInstance().getApplicationContext());
        if (dataRepository == null)
            return;

        GetListStoreUserCase.RequestValue username = new GetListStoreUserCase.RequestValue(dataRepository.getUserName());
        getListStoreUserCase.execute(new GetListStoreObserver(), username);
    }

    private class GetListStoreObserver extends DisposableObserver<DataResponse<StoreSystem>> {
        @Override
        public void onNext(DataResponse<StoreSystem> listStoreModelDataResponse) {
            storeView.hideProgress();
            if (listStoreModelDataResponse.data != null && listStoreModelDataResponse.data.size() > 0) {
                storeView.initStoreData(listStoreModelDataResponse.data);
            }
        }

        @Override
        public void onError(Throwable e) {
            storeView.hideProgress();
            storeView.showError(e.getMessage());
        }

        @Override
        public void onComplete() {
            storeView.hideProgress();
        }
    }

    private class SignOutObserver extends DisposableObserver<Boolean> {
        @Override
        public void onNext(Boolean aBoolean) {
            if (storeView != null) {
                storeView.navigateToLoginPage();
            }
        }

        @Override
        public void onError(Throwable e) {
            if (storeView != null) {
                storeView.showError(e.toString());
            }
        }

        @Override
        public void onComplete() {

        }
    }
}

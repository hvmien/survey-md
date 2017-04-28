package com.example.datasource.usercases;

import com.example.datasource.model.DataResponse;
import com.example.datasource.model.StoreSystem;
import com.example.datasource.repository.DataRepository;

import io.reactivex.Observable;

/**
 * Created by MienHV1 on 4/28/2017.
 */

public class GetListStoreUserCase extends RxUserCase<DataResponse<StoreSystem>, GetListStoreUserCase.RequestValue> {
    public GetListStoreUserCase(DataRepository dataRepository) {
        super(dataRepository);
    }

    @Override
    protected Observable<DataResponse<StoreSystem>> buildPagrams(RequestValue pagram) {
        return mDataRepository.getListStore();
    }

    public static class RequestValue implements RxUserCase.RequestValue {
    }
}

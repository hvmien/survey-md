package com.example.datasource.usercases;

import com.example.datasource.model.DataResponse;
import com.example.datasource.model.ProvinceModel;
import com.example.datasource.repository.DataRepository;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by HVM on 4/25/2017.
 */

public class GetListProvinceUserCase extends RxUserCase<DataResponse<ProvinceModel>, GetListProvinceUserCase.RequestValue> {
    public GetListProvinceUserCase(DataRepository dataRepository) {
        super(dataRepository);
    }

    @Override
    protected Observable<DataResponse<ProvinceModel>> buildPagrams(RequestValue pagram) {
        return mDataRepository.getListProvince()
                .observeOn(Schedulers.io())
                .map(new Function<DataResponse<ProvinceModel>, DataResponse<ProvinceModel>>() {
                    @Override
                    public DataResponse<ProvinceModel> apply(@NonNull DataResponse<ProvinceModel> provinceModelDataResponse) throws Exception {
                        return provinceModelDataResponse;
                    }
                });
    }

    public static class RequestValue implements RxUserCase.RequestValue {

    }
}

package com.example.datasource.usercases;

import com.example.datasource.model.DataResponse;
import com.example.datasource.model.WardModel;
import com.example.datasource.repository.DataRepository;

import io.reactivex.Observable;

/**
 * Created by HVM on 4/25/2017.
 */

public class GetListWardUserCase extends RxUserCase<DataResponse<WardModel>,GetListWardUserCase.RequestValue> {
    public GetListWardUserCase(DataRepository dataRepository) {
        super(dataRepository);
    }

    @Override
    protected Observable<DataResponse<WardModel>> buildPagrams(RequestValue pagram) {
        return mDataRepository.getWardViaDistrict(pagram.districtid);
    }
    public static class RequestValue implements RxUserCase.RequestValue{
        public RequestValue(String districtid) {

            this.districtid = districtid;
        }

        String districtid;
    }
}

package com.example.datasource.usercases;

import com.example.datasource.model.DataResponse;
import com.example.datasource.model.DistrictModel;
import com.example.datasource.repository.DataRepository;

import io.reactivex.Observable;

/**
 * Created by HVM on 4/25/2017.
 */

public class GetListDistrictUserCase extends RxUserCase<DataResponse<DistrictModel>,GetListDistrictUserCase.RequestValue> {
    public GetListDistrictUserCase(DataRepository dataRepository) {
        super(dataRepository);
    }

    @Override
    protected Observable<DataResponse<DistrictModel>> buildPagrams(RequestValue pagram) {
        return mDataRepository.getDistrictViaProvince(pagram.provinceid);
    }
    public static class RequestValue implements RxUserCase.RequestValue{
        public RequestValue(String provinceid) {
            this.provinceid = provinceid;
        }

        String provinceid;
    }
}

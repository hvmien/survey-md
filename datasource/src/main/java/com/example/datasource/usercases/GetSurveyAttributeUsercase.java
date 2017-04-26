package com.example.datasource.usercases;

import com.example.datasource.model.DataResponse;
import com.example.datasource.model.ItemAttributeModel;
import com.example.datasource.repository.DataRepository;

import io.reactivex.Observable;

/**
 * Created by MienHV1 on 4/26/2017.
 */

public class GetSurveyAttributeUsercase extends RxUserCase<DataResponse<ItemAttributeModel>,GetSurveyAttributeUsercase.RequestValue> {
    public GetSurveyAttributeUsercase(DataRepository dataRepository) {
        super(dataRepository);
    }

    @Override
    protected Observable<DataResponse<ItemAttributeModel>> buildPagrams(RequestValue pagram) {
        return mDataRepository.getSurveyAttribute(pagram.tableid);
    }
    public static class RequestValue implements RxUserCase.RequestValue{
        public RequestValue(int tableid) {
            this.tableid = tableid;
        }

        int tableid;
    }
}

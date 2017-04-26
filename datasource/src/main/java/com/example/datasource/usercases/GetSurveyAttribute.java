package com.example.datasource.usercases;

import com.example.datasource.model.DataResponse;
import com.example.datasource.model.SurveyAttributeModel;
import com.example.datasource.repository.DataRepository;

import io.reactivex.Observable;

/**
 * Created by MienHV1 on 4/26/2017.
 */

public class GetSurveyAttribute extends RxUserCase<DataResponse<SurveyAttributeModel>,GetSurveyAttribute.RequestValue> {
    public GetSurveyAttribute(DataRepository dataRepository) {
        super(dataRepository);
    }

    @Override
    protected Observable<DataResponse<SurveyAttributeModel>> buildPagrams(RequestValue pagram) {
        return mDataRepository.getSurveyAttribute(pagram.tableid);
    }
    public static class RequestValue implements RxUserCase.RequestValue{
        public RequestValue(int tableid) {
            this.tableid = tableid;
        }

        int tableid;
    }
}

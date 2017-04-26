package com.example.datasource.usercases;

import com.example.datasource.model.DataResponse;
import com.example.datasource.model.SurveyTableModel;
import com.example.datasource.repository.DataRepository;

import io.reactivex.Observable;

/**
 * Created by HVM on 4/18/2017.
 */

public class GetSurveyTableUserCase extends RxUserCase<DataResponse<SurveyTableModel>,GetSurveyTableUserCase.RequestValue> {
    public GetSurveyTableUserCase(DataRepository dataRepository) {
        super(dataRepository);
    }

    @Override
    protected Observable<DataResponse<SurveyTableModel>> buildPagrams(RequestValue pagram) {
        return mDataRepository.getDatabaseQuestion();
    }
    public static class RequestValue implements RxUserCase.RequestValue{

    }
}

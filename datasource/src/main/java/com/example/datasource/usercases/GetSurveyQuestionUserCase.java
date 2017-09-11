package com.example.datasource.usercases;

import com.example.datasource.model.DataResponse;
import com.example.datasource.model.ItemQuestionModel;
import com.example.datasource.repository.DataRepository;

import io.reactivex.Observable;

/**
 * Created by HVM on 4/18/2017.
 */

public class GetSurveyQuestionUserCase extends RxUserCase<DataResponse<ItemQuestionModel>,GetSurveyQuestionUserCase.RequestValue> {
    public GetSurveyQuestionUserCase(DataRepository dataRepository) {
        super(dataRepository);
    }

    @Override
    protected Observable<DataResponse<ItemQuestionModel>> buildPagrams(RequestValue pagram) {
        return mDataRepository.getDatabaseQuestion(pagram.idTopic);
    }
    public static class RequestValue implements RxUserCase.RequestValue{
        public RequestValue(int idTopic) {
            this.idTopic = idTopic;
        }

        public int idTopic;
    }
}

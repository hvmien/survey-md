package com.example.datasource.usercases;

import com.example.datasource.model.DataAnswerText;
import com.example.datasource.model.DataLoginResponse;
import com.example.datasource.model.DataResponse;
import com.example.datasource.model.ResponeDataText;
import com.example.datasource.model.StoreSystem;
import com.example.datasource.model.User;
import com.example.datasource.repository.DataRepository;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by MienHV1 on 4/11/2017.
 */

public class UploadDataAnswerTextUserCase extends RxUserCase<DataResponse<ResponeDataText>, UploadDataAnswerTextUserCase.RequestValue> {
    public UploadDataAnswerTextUserCase(DataRepository dataRepository) {
        super(dataRepository);

    }

    @Override
    protected Observable<DataResponse<ResponeDataText>> buildPagrams(RequestValue pagram) {
        return mDataRepository.uploadDataText(pagram.order);
    }


    public static class RequestValue implements RxUserCase.RequestValue {
        DataAnswerText order;

        public RequestValue(DataAnswerText orderdetail) {
            order=orderdetail;
        }
    }
}

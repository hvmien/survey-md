package com.example.datasource.usercases;

import com.example.datasource.model.Model;
import com.example.datasource.repository.DataRepository;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by HVM on 4/18/2017.
 */

public class GetDataBaseUserCase extends RxUserCase<List<Model>,GetDataBaseUserCase.RequestValue> {
    public GetDataBaseUserCase(DataRepository dataRepository) {
        super(dataRepository);
    }

    @Override
    protected Observable<List<Model>> buildPagrams(RequestValue pagram) {
        return mDataRepository.getDatabase();
    }
    public static class RequestValue implements RxUserCase.RequestValue{

    }
}

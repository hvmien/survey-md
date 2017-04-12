package com.example.datasource.usercases;

import com.example.datasource.repository.DataRepository;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

/**
 * Created by MienHV1 on 4/12/2017.
 */

public class SignOutUserCase extends RxUserCase<Boolean,SignOutUserCase.RequestValue> {
    public SignOutUserCase(DataRepository dataRepository) {
        super(dataRepository);
    }

    @Override
    protected Observable<Boolean> buildPagrams(RequestValue pagram) {
        mDataRepository.clearUser();
        return Observable.create(new ObservableOnSubscribe<Boolean>() {
            @Override
            public void subscribe(ObservableEmitter<Boolean> e) throws Exception {
                e.onNext(true);
                e.onComplete();
            }
        });
    }
    public static class RequestValue implements RxUserCase.RequestValue{

    }
}

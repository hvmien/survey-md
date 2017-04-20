package com.example.datasource.usercases;

import com.example.datasource.repository.DataRepository;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by MienHV1 on 4/12/2017.
 */

public class SignOutUserCase extends RxUserCase<Boolean,SignOutUserCase.RequestValue> {
    public SignOutUserCase(DataRepository dataRepository) {
        super(dataRepository);
    }

    @Override
    protected Observable<Boolean> buildPagrams(RequestValue pagram) {
       return mDataRepository.signOut()
               .observeOn(Schedulers.io())
                .map(new Function<Boolean, Boolean>() {
                    @Override
                    public Boolean apply(@NonNull Boolean aBoolean) throws Exception {
                        mDataRepository.clearUser();
                        return true;
                    }
                });
//        return Observable.create(new ObservableOnSubscribe<Boolean>() {
//            @Override
//            public void subscribe(ObservableEmitter<Boolean> e) throws Exception {
//                e.onNext(true);
//                e.onComplete();
//            }
//        });
    }
    public static class RequestValue implements RxUserCase.RequestValue{

    }
}

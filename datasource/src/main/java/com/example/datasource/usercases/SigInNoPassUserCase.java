package com.example.datasource.usercases;

import com.example.datasource.model.DataLoginResponse;
import com.example.datasource.model.User;
import com.example.datasource.repository.DataRepository;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by HVM on 4/20/2017.
 */

public class SigInNoPassUserCase extends RxUserCase<DataLoginResponse<User>, SigInNoPassUserCase.RequestValue> {
    public SigInNoPassUserCase(DataRepository dataRepository) {
        super(dataRepository);
    }

    @Override
    protected Observable<DataLoginResponse<User>> buildPagrams(RequestValue pagram) {
        return mDataRepository.signInNoPass(pagram.username,pagram.devices_id)
                .observeOn(Schedulers.newThread())
                .map(new Function<DataLoginResponse<User>, DataLoginResponse<User>>() {
                    @Override
                    public DataLoginResponse<User> apply(@NonNull DataLoginResponse<User> userDataResponse) throws Exception {
                        if (userDataResponse.error == 0) {
                            return userDataResponse;
                        } else {
                            return null;
                        }
                    }
                });

    }

    public static class RequestValue implements RxUserCase.RequestValue {
        String username;
        String devices_id;

        public RequestValue(String username,String devices_id) {
            this.username = username;
            this.devices_id=devices_id;
        }
    }
}

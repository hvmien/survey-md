package com.example.datasource.usercases;

import com.example.datasource.model.DataLoginResponse;
import com.example.datasource.model.User;
import com.example.datasource.repository.DataRepository;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by MienHV1 on 4/11/2017.
 */

public class SignInUserCase extends RxUserCase<DataLoginResponse<User>, SignInUserCase.RequestValue> {
    public SignInUserCase(DataRepository dataRepository) {
        super(dataRepository);

    }

    @Override
    protected Observable<DataLoginResponse<User>> buildPagrams(RequestValue pagram) {
        return mDataRepository.signIn(pagram.email, pagram.password,pagram.devicesid)
                .observeOn(Schedulers.newThread())
                .map(new Function<DataLoginResponse<User>, DataLoginResponse<User>>() {
                    @Override
                    public DataLoginResponse<User> apply(@NonNull DataLoginResponse<User> userDataResponse) throws Exception {
                        if (userDataResponse.error==0) {
                            if (userDataResponse.data.username != null) {
                                mDataRepository.saveUser(userDataResponse.data.username);
                            }
                            return userDataResponse;
                        } else {
                            return null;
                        }

                    }
                });
    }


    public static class RequestValue implements RxUserCase.RequestValue {
        String email;
        String password;
        String devicesid;

        public RequestValue(String email, String password,String devicesid) {
            this.email = email;
            this.password = password;
            this.devicesid = devicesid;
        }
    }
}

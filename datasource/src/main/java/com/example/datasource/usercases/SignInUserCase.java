package com.example.datasource.usercases;

import com.example.datasource.model.User;
import com.example.datasource.repository.DataRepository;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by MienHV1 on 4/11/2017.
 */

public class SignInUserCase extends RxUserCase<User, SignInUserCase.RequestValue> {
    public SignInUserCase(DataRepository dataRepository) {
        super(dataRepository);

    }

    @Override
    protected Observable<User> buildPagrams(RequestValue pagram) {
        return mDataRepository.signIn(pagram.email, pagram.password)
                .observeOn(Schedulers.newThread())
                .map(new Function<User, User>() {
                    @Override
                    public User apply(@NonNull User user) throws Exception {
                        mDataRepository.saveUser(user);
                        return user;
                    }
                });
    }


    public static class RequestValue implements RxUserCase.RequestValue {
        String email;
        String password;

        public RequestValue(String email, String password) {
            this.email = email;
            this.password = password;
        }
    }
}

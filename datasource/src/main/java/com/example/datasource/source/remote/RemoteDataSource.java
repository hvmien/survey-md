package com.example.datasource.source.remote;

import android.content.Context;

import com.example.datasource.model.Model;
import com.example.datasource.model.User;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

/**
 * Created by MienHV1 on 4/11/2017.
 */

public class RemoteDataSource implements IRemoteDataSource {

    private static RemoteDataSource INSTANCE;
    private Context mContext;
    private ApiInterface mApiInterface;

    public RemoteDataSource() {
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);
    }

    public RemoteDataSource(Context context) {
        mContext = context.getApplicationContext();
    }

    public synchronized static RemoteDataSource getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new RemoteDataSource(context);
        }
        return INSTANCE;
    }

    @Override
    public Observable<User> signIn(final String email, String password) {
        return Observable.create(new ObservableOnSubscribe<User>() {
            @Override
            public void subscribe(ObservableEmitter<User> emitter) throws Exception {
                //handle code this here
                if (email.equals("hominhduc") || email.equals("hvmien")) {
                    User user = new User();
                    user.accountId = email;
                    emitter.onNext(user);
                    emitter.onComplete();
                } else {
                    emitter.onError(null);
                    emitter.onComplete();
                }
            }
        });
    }

    @Override
    public Observable<Boolean> signOut() {
        return Observable.create(new ObservableOnSubscribe<Boolean>() {

            @Override
            public void subscribe(ObservableEmitter<Boolean> e) throws Exception {
                e.onNext(true);
                e.onComplete();
            }
        });
    }

    @Override
    public Observable<List<Model>> getDatabase() {
        return Observable.create(new ObservableOnSubscribe<List<Model>>() {
            @Override
            public void subscribe(ObservableEmitter<List<Model>> e) throws Exception {
                List<Model> mList = new ArrayList<Model>();
                Model model = new Model();
                model.name="abc";
                mList.add(model);
                e.onNext(mList);
                e.onComplete();
                return;
            }
        });
    }
}

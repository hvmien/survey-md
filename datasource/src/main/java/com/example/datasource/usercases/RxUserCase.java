package com.example.datasource.usercases;

import com.example.datasource.repository.DataRepository;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by MienHV1 on 4/11/2017.
 */

public abstract class RxUserCase<T, D extends RxUserCase.RequestValue> {

    protected DataRepository mDataRepository;
    private CompositeDisposable mCompositeDisposable;
    public RxUserCase(DataRepository dataRepository){
        mDataRepository=dataRepository;
        mCompositeDisposable = new CompositeDisposable();
    }

    public void execute(DisposableObserver<T> disposableObserver,D pagram){
        final Observable<T> observable = buildPagrams(pagram);
        Disposable disposable = observable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(disposableObserver);
        mCompositeDisposable.add(disposable);
    }

    public void dispose(){
        if(mCompositeDisposable!=null&&!mCompositeDisposable.isDisposed()){
            mCompositeDisposable.dispose();
        }
    }

    protected abstract Observable<T> buildPagrams(D pagram);

    interface RequestValue {
    }
}

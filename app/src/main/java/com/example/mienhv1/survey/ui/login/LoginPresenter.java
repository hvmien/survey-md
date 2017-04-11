package com.example.mienhv1.survey.ui.login;

import android.content.Context;
import android.text.TextUtils;

import com.example.datasource.model.User;
import com.example.datasource.repository.DataRepository;
import com.example.datasource.usercases.SignInUserCase;
import com.example.mienhv1.survey.MyApplication;
import com.example.mienhv1.survey.R;
import com.example.mienhv1.survey.base.BasePresenter;
import com.example.mienhv1.survey.utils.Utils;

import io.reactivex.observers.DisposableObserver;

/**
 * Created by MienHV1 on 4/11/2017.
 */

public class LoginPresenter implements BasePresenter{

    private DataRepository mDataRepository;
    private LoginView mLoginView;
    private SignInUserCase mSignInUserCase;
    private Context mContext;

    public LoginPresenter(DataRepository dataRepository,  LoginView loginView) {
        mDataRepository = dataRepository;
        mLoginView = loginView;

        mSignInUserCase = new SignInUserCase(dataRepository);
        mContext = MyApplication.getInstance().getApplicationContext();
    }

    public void signIn(String email, String password) {
        if(validateLoginForm(email,password)){
            mLoginView.showProgress();
            SignInUserCase.RequestValue requestValue = new SignInUserCase.RequestValue(email, password);
            mSignInUserCase.execute(new SignInObserver(), requestValue);
        }
    }

    private boolean validateLoginForm(String email, String password) {
        if(!Utils.isNetworkAvailable())
        {
            mLoginView.showError(mContext.getString(R.string.error_network));
            return false;
        }
        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            mLoginView.showError(mContext.getString(R.string.error_invalid_username));

            return false;
        }

        // Check for a valid password, if the user entered one.
        if (TextUtils.isEmpty(password)) {
            mLoginView.showError(mContext.getString(R.string.error_invalid_password));
            return false;
        }
        return true;
    }

    @Override
    public void create() {

    }

    @Override
    public void start() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void stop() {
        mSignInUserCase.dispose();
    }

    public void destroy() {
        mLoginView = null;
    }

    private class SignInObserver extends DisposableObserver<User> {
        @Override
        public void onNext(User user) {
            if(mLoginView!=null){
                mLoginView.hideProgress();
                mLoginView.navigateToHomePage();
            }
        }

        @Override
        public void onError(Throwable e) {
            if (mLoginView != null) {
                mLoginView.hideProgress();
                mLoginView.showError(mContext.getString(R.string.error_invalid_login));
            }
        }

        @Override
        public void onComplete() {

        }
    }
}

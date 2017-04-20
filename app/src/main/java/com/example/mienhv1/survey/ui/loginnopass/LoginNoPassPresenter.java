package com.example.mienhv1.survey.ui.loginnopass;

import android.content.Context;
import android.provider.Settings;
import android.text.TextUtils;

import com.example.datasource.model.DataLoginResponse;
import com.example.datasource.model.User;
import com.example.datasource.repository.DataRepository;
import com.example.datasource.usercases.SigInNoPassUserCase;
import com.example.datasource.usercases.SignInUserCase;
import com.example.mienhv1.survey.MyApplication;
import com.example.mienhv1.survey.R;
import com.example.mienhv1.survey.base.BasePresenter;
import com.example.mienhv1.survey.ui.login.LoginPresenter;
import com.example.mienhv1.survey.utils.Utils;

import io.reactivex.observers.DisposableObserver;

import static java.security.AccessController.getContext;

/**
 * Created by HVM on 4/20/2017.
 */

public class LoginNoPassPresenter implements BasePresenter {
    private SigInNoPassUserCase mSignSigInNoPassUserCase;
    private DataRepository mDataRepository;
    private LoginNoPassView mLoginNoPassView;
    private Context mContext;

    public LoginNoPassPresenter(DataRepository dataRepository, LoginNoPassView loginNoPassView) {
        this.mDataRepository = dataRepository;
        this.mLoginNoPassView = loginNoPassView;
        mSignSigInNoPassUserCase = new SigInNoPassUserCase(dataRepository);
        mContext = MyApplication.getInstance().getApplicationContext();
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

    }

    @Override
    public void destroy() {

    }

    public void signInNoPass(String email) {
        if(validateLoginForm(email)){
            String device_id = getDeviceId();
            mLoginNoPassView.showProgress();
            SigInNoPassUserCase.RequestValue requestValue = new SigInNoPassUserCase.RequestValue(email,device_id);
            mSignSigInNoPassUserCase.execute(new LoginNoPassPresenter.SignInNoPassObserver(), requestValue);
        }
    }

    private String getDeviceId() {
        return Settings.Secure.getString(mContext.getContentResolver(),
                Settings.Secure.ANDROID_ID);
    }

    private boolean validateLoginForm(String email) {
        if(!Utils.isNetworkAvailable())
        {
            mLoginNoPassView.showError(mContext.getString(R.string.error_network));
            return false;
        }
        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            mLoginNoPassView.showError(mContext.getString(R.string.error_invalid_username));

            return false;
        }
        return true;
    }

    private class SignInNoPassObserver extends DisposableObserver<DataLoginResponse<User>> {
        @Override
        public void onNext(DataLoginResponse<User> userDataResponse) {
            if (mLoginNoPassView != null) {
                mLoginNoPassView.hideProgress();
                mLoginNoPassView.navigateToHomePage();

            }
        }

        @Override
        public void onError(Throwable e) {
            if (mLoginNoPassView != null) {
                mLoginNoPassView.hideProgress();
                mLoginNoPassView.showError(mContext.getString(R.string.error_invalid_login_no_pass));
            }
        }

        @Override
        public void onComplete() {

        }
    }
}

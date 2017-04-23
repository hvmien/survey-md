package com.example.mienhv1.survey.ui.loginnopass;

import android.content.Intent;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.example.datasource.repository.DataRepository;
import com.example.datasource.repository.DataRepositoryFactory;
import com.example.mienhv1.survey.R;
import com.example.mienhv1.survey.base.BaseFragment;
import com.example.mienhv1.survey.ui.home.HomeActivity;
import com.example.mienhv1.survey.ui.login.LoginPresenter;

/**
 * Created by HVM on 4/20/2017.
 */

public class LoginNoPassFragment extends BaseFragment implements LoginNoPassView,View.OnClickListener {

    private ProgressBar mProgressBar;
    private AutoCompleteTextView mEmailView;
    private RelativeLayout mContainer;
    private LoginNoPassPresenter mPresenter;


    @Override
    protected int getResourcesLayout() {
        return R.layout.fragment_login_no_pass;
    }

    @Override
    protected void mapView(View view) {
        mProgressBar = (ProgressBar) view.findViewById(R.id.login_no_pass_progress);
        mEmailView = (AutoCompleteTextView) view.findViewById(R.id.login_no_pass_email_input);
        mContainer = (RelativeLayout) view.findViewById(R.id.login_no_pass_container_view);
        Button signInBtn = (Button) view.findViewById(R.id.login_no_pass_signin_btn);
        signInBtn.setOnClickListener(this);

    }

    @Override
    protected void initData() {
//        DataRepository dataRepository = DataRepositoryFactory.createDataRepository(getActivity());
//        mPresenter = new LoginNoPassPresenter(dataRepository, this);
    }

    @Override
    protected void destroyView() {

    }

    @Override
    public void navigateToHomePage() {
        Intent intent = new Intent(getActivity(), HomeActivity.class);
        startActivity(intent);
        getActivity().finish();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login_no_pass_signin_btn:
                attemplogin();
                break;
            default:
                break;
        }
    }

    private void attemplogin() {
        mEmailView.setError(null);

        // Store values at the time of the login attempt.
        String email = mEmailView.getText().toString();

        mPresenter.signInNoPass(email);
    }

    @Override
    public void showProgress() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void showError(String error) {
        showFooterMessage(mContainer, error);
    }

}

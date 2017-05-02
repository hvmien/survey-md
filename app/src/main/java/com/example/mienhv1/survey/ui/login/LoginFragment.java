package com.example.mienhv1.survey.ui.login;

import android.content.Intent;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.example.datasource.repository.DataRepository;
import com.example.datasource.repository.DataRepositoryFactory;
import com.example.mienhv1.survey.R;
import com.example.mienhv1.survey.base.BaseFragment;
import com.example.mienhv1.survey.ui.fragment.store.StoreActivity;
import com.example.mienhv1.survey.utils.view.CSButton;
import com.example.mienhv1.survey.utils.view.CSEditText;
import com.example.mienhv1.survey.utils.view.CSTextView;

/**
 * Created by MienHV1 on 4/11/2017.
 */

public class LoginFragment extends BaseFragment implements LoginView, View.OnClickListener {

    private ProgressBar mProgressBar;
    private CSEditText mEmailView;
    private CSEditText mPasswordView;
    private RelativeLayout mContainer;
    private LoginPresenter mPresenter;

    @Override
    protected int getResourcesLayout() {
        return R.layout.fragment_login;
    }

    @Override
    protected void mapView(View view) {
        mProgressBar = (ProgressBar) view.findViewById(R.id.login_progress);
        mEmailView = (CSEditText) view.findViewById(R.id.login_email_input);
        mPasswordView = (CSEditText) view.findViewById(R.id.login_password_input);
        mContainer = (RelativeLayout) view.findViewById(R.id.login_container_view);
        CSButton signInBtn = (CSButton) view.findViewById(R.id.login_signin_btn);
        signInBtn.setOnClickListener(this);

    }

    @Override
    protected void initData() {
        DataRepository dataRepository = DataRepositoryFactory.createDataRepository(getActivity());
        mPresenter = new LoginPresenter(dataRepository, this);
    }

    @Override
    protected void destroyView() {
        mPresenter.destroy();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_signin_btn:
                attemplogin();
                break;
            default:
                break;
        }
    }

    private void attemplogin() {
        mEmailView.setError(null);
        mPasswordView.setError(null);

        // Store values at the time of the login attempt.
        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();

        mPresenter.signIn(email, password);
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

    @Override
    public void navigateToHomePage() {
//        Intent intent = new Intent(getActivity(), HomeActivity.class);
//        startActivity(intent);
//        getActivity().finish();
    }

    @Override
    public void navigateToStoreListPage() {
        Intent intent = new Intent(getActivity(), StoreActivity.class);
        startActivity(intent);
        getActivity().finish();
    }
}

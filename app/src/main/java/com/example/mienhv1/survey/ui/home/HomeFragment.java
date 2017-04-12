package com.example.mienhv1.survey.ui.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.example.datasource.repository.DataRepository;
import com.example.datasource.repository.DataRepositoryFactory;
import com.example.datasource.usercases.SignOutUserCase;
import com.example.mienhv1.survey.R;
import com.example.mienhv1.survey.base.BaseFragment;

/**
 * Created by MienHV1 on 4/12/2017.
 */

public class HomeFragment extends BaseFragment implements HomeView{

    private HomePresenter mHomePresenter;
    private OnHomeListener mListener;
    private ProgressBar mProgressbar;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof OnHomeListener){
            mListener = (OnHomeListener) context;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener=null;
    }

    @Override
    protected int getResourcesLayout() {
        return R.layout.fragment_home;
    }

    @Override
    protected void mapView(View view) {
        mProgressbar = (ProgressBar) view.findViewById(R.id.home_progress);
    }

    @Override
    protected void initData() {
        DataRepository dataRepository = DataRepositoryFactory.createDataRepository(getActivity());
        mHomePresenter = new HomePresenter(dataRepository,this);
        setHasOptionsMenu(true);
        //add viewpager this here

    }

    @Override
    protected void destroyView() {

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showError(String error) {

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_home,menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_signout:
                mHomePresenter.signOut();
                return true;
        }
        return true;
    }

    @Override
    public void navigateToLoginPage() {
        mListener.onOpenLoginPage();
    }
    interface OnHomeListener{
        void onOpenLoginPage();
    }
}

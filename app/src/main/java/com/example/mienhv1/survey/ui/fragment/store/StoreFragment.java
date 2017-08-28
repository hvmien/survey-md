package com.example.mienhv1.survey.ui.fragment.store;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.datasource.model.StoreSystem;
import com.example.datasource.repository.DataRepository;
import com.example.datasource.repository.DataRepositoryFactory;
import com.example.mienhv1.survey.R;
import com.example.mienhv1.survey.base.BaseFragment;
import com.example.mienhv1.survey.ui.adapter.RecyclerViewItemListener;
import com.example.mienhv1.survey.ui.adapter.store.StoreAdapter;
import com.example.mienhv1.survey.ui.home.HomeActivity;

import java.util.ArrayList;

/**
 * Created by Forev on 17/04/18.
 */

public class StoreFragment extends BaseFragment implements StoreView, RecyclerViewItemListener {
    private OnStoreListener mListener;
    RecyclerView rcStore;
    StoreAdapter storeAdapter;
    StorePresenter presenter;
    ArrayList<StoreSystem> dataStoreSystem = new ArrayList<>();
    ProgressBar storeProgerssbar;

    interface OnStoreListener {
        void onOpenLoginPage();

        void onTopicClick(int pos);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof OnStoreListener) {
            mListener = (OnStoreListener) activity;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    @Override
    protected int getResourcesLayout() {
        return R.layout.fragment_store_system;
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.create();
    }

    @Override
    protected void mapView(View view) {
        storeProgerssbar = (ProgressBar) view.findViewById(R.id.store_progress);
        rcStore = (RecyclerView) view.findViewById(R.id.rc_store);
        DataRepository data = DataRepositoryFactory.createDataRepository(getActivity());
        presenter = new StorePresenter(data, this);

    }

    @Override
    protected void initData() {
        presenter.getListStoreForRecyclerView();

        storeAdapter = new StoreAdapter(getActivity(), this);
        setHasOptionsMenu(true);
        rcStore.setLayoutManager(new LinearLayoutManager(getActivity()));
        rcStore.setAdapter(storeAdapter);
    }

    @Override
    protected void destroyView() {
        presenter.destroy();
    }

    @Override
    public void showProgress() {
        storeProgerssbar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        storeProgerssbar.setVisibility(View.GONE);
    }

    @Override
    public void showError(String error) {
        Toast.makeText(getActivity(), error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void initStoreData(ArrayList<StoreSystem> data) {
        if (data != null) {
            dataStoreSystem.addAll(data);
            storeAdapter.updateData(data);
        }


    }

    @Override
    public void navigateToLoginPage() {
        mListener.onOpenLoginPage();
    }

    @Override
    public void onItemClick(int position) {
        mListener.onTopicClick(position);
    }

    @Override
    public void onItemClickElement(String titleElement, int position) {

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_home, menu);
    }

    public void logoutPrensenter() {
        if (presenter != null) {
            presenter.signOut();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item1:
                Toast.makeText(getActivity(), "Receive notify", Toast.LENGTH_SHORT).show();
                return true;
        }
        return true;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.destroy();
    }
}

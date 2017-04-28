package com.example.mienhv1.survey.ui.fragment.store;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

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
    RecyclerView rcStore;
    StoreAdapter storeAdapter;
    StorePresenter presenter;
    ArrayList<StoreSystem> dataStoreSystem = new ArrayList<>();

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
        rcStore = (RecyclerView) view.findViewById(R.id.rc_store);
        DataRepository data = DataRepositoryFactory.createDataRepository(getActivity());
        presenter = new StorePresenter(data, this);

    }

    @Override
    protected void initData() {
        presenter.getListStoreForRecyclerView();

        storeAdapter = new StoreAdapter(getActivity(), this);

        rcStore.setLayoutManager(new LinearLayoutManager(getActivity()));
        rcStore.setAdapter(storeAdapter);
    }

    @Override
    protected void destroyView() {
        presenter.destroy();
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
    public void initStoreData(ArrayList<StoreSystem> data) {
        if (data != null) {
            dataStoreSystem.addAll(data);
            storeAdapter.updateData(data);
        }


    }

    @Override
    public void onItemClick(int position) {
        //check gps this here
        Intent intent = new Intent(getActivity(), HomeActivity.class);
        startActivity(intent);
    }
}

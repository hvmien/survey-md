package com.example.mienhv1.survey.ui.fragment.store;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.datasource.model.StoreSystem;
import com.example.mienhv1.survey.R;
import com.example.mienhv1.survey.base.BaseFragment;
import com.example.mienhv1.survey.ui.adapter.RecyclerViewItemListener;
import com.example.mienhv1.survey.ui.adapter.store.StoreAdapter;

import java.util.ArrayList;

/**
 * Created by Forev on 17/04/18.
 */

public class StoreFragment extends BaseFragment implements StoreView{
    RecyclerView rcStore;
    StoreAdapter storeAdapter;

    StorePresenter presenter;

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
        presenter = new StorePresenter(this);

    }

    @Override
    protected void initData() {
        storeAdapter = new StoreAdapter(getActivity(), new RecyclerViewItemListener() {
            @Override
            public void onItemClick(int position) {

            }
        });

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
        storeAdapter.setData(data);
    }
}

package com.example.mienhv1.survey.ui.fragment.store;

import com.example.datasource.model.StoreSystem;
import com.example.mienhv1.survey.base.BasePresenter;

import java.util.ArrayList;

/**
 * Created by Forev on 17/04/18.
 */

public class StorePresenter implements BasePresenter {

    StoreView storeView;

    public StorePresenter(StoreView view) {
        this.storeView = view;
    }


    @Override
    public void create() {
        ArrayList<StoreSystem> data = new ArrayList<>();
        data.add(new StoreSystem());
        data.add(new StoreSystem());
        data.add(new StoreSystem());
        storeView.initStoreData(data);
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
}

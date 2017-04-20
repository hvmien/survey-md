package com.example.mienhv1.survey.ui.fragment.store;

import com.example.datasource.model.StoreSystem;
import com.example.mienhv1.survey.base.BaseView;

import java.util.ArrayList;

/**
 * Created by Forev on 17/04/18.
 */

public interface StoreView extends BaseView {
    void initStoreData(ArrayList<StoreSystem> data);
}

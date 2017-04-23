package com.example.mienhv1.survey.ui.fragment.info;

import android.util.Log;
import android.view.View;

import com.example.datasource.model.RoadAhead;
import com.example.mienhv1.survey.R;
import com.example.mienhv1.survey.base.BaseFragment;
import com.example.mienhv1.survey.ui.adapter.OnUpdateListener;
import com.example.mienhv1.survey.ui.adapter.RoadAheadAdapter;
import com.example.mienhv1.survey.utils.view.CSGroupCheckbox;
import com.example.mienhv1.survey.utils.view.CSRadioGroup;

import java.util.ArrayList;

/**
 * Created by Forev on 17/04/20.
 */

public class InfoFragment extends BaseFragment implements InfoView {

    CSRadioGroup grDirection;
    InfoPresenter presenter;
    private String TAG = "InfoFragment";


    @Override
    protected int getResourcesLayout() {
        return R.layout.fragment_general_information;
    }

    @Override
    protected void mapView(View view) {
        presenter = new InfoPresenter(this);
//        grDirection = (CSRadioGroup) view.findViewById(R.id.gr_direction);

        CSGroupCheckbox gpCheckbox = (CSGroupCheckbox) view.findViewById(R.id.gr_checkbox);

        ArrayList<RoadAhead> data = new ArrayList<>();
        data.add(new RoadAhead(1, "Quoc Lo", false));
        data.add(new RoadAhead(2, "Duong Lon", false));
        data.add(new RoadAhead(3, "Hem", false));
        data.add(new RoadAhead(4, "Ngo Cut", false));

        RoadAheadAdapter adapter = new RoadAheadAdapter(getActivity(), R.layout.item_radio_button, gpCheckbox, data);
        adapter.setOnUpdateListener(new OnUpdateListener<RoadAhead>() {
            @Override
            public void onUpdate(ArrayList<RoadAhead> data) {
                for (int i = 0; i < data.size(); i++) {
                    RoadAhead d = data.get(i);
                    Log.d(TAG, d.name + " " + d.select);
                }
            }
        });

        gpCheckbox.setAdapter(adapter);
        //grDirection.setAdapter(adapter);
    }

    @Override
    protected void initData() {
        presenter.create();
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
}

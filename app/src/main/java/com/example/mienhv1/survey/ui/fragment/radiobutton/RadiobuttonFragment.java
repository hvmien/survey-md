package com.example.mienhv1.survey.ui.fragment.radiobutton;

import android.view.View;

import com.example.datasource.model.RadiobuttonModel;
import com.example.mienhv1.survey.R;
import com.example.mienhv1.survey.base.BaseFragment;
import com.example.mienhv1.survey.utils.view.CSRadioGroup;
import com.example.mienhv1.survey.utils.view.CSTextView;

import java.util.ArrayList;

/**
 * Created by HVM on 4/23/2017.
 */

public class RadiobuttonFragment extends BaseFragment {
    private CSTextView txtTitle;
    private CSRadioGroup csRadioGroupParent;

    @Override
    protected int getResourcesLayout() {
        return R.layout.fragment_radiobutton;
    }

    @Override
    protected void mapView(View view) {
        txtTitle = (CSTextView) view.findViewById(R.id.txt_title_radio_group);
        csRadioGroupParent = (CSRadioGroup) view.findViewById(R.id.gp_data);
    }

    @Override
    protected void initData() {
        ArrayList<RadiobuttonModel> modelArrayList = new ArrayList<>();
        RadiobuttonModel a = new RadiobuttonModel(1, "Radiobutton1", false);
        RadiobuttonModel b = new RadiobuttonModel(2, "Radiobutton2", false);
        RadiobuttonModel c = new RadiobuttonModel(3, "Radiobutton3", false);
        modelArrayList.add(a);
        modelArrayList.add(b);
        modelArrayList.add(c);

        RadioButtonAdapter adapter2 = new RadioButtonAdapter(getActivity(), R.layout.item_radio_button, csRadioGroupParent, modelArrayList);
        csRadioGroupParent.setAdapter(adapter2);
    }

    @Override
    protected void destroyView() {

    }
}

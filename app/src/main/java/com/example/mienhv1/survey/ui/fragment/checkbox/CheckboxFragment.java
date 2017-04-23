package com.example.mienhv1.survey.ui.fragment.checkbox;

import android.view.View;

import com.example.datasource.model.CheckboxModel;
import com.example.mienhv1.survey.R;
import com.example.mienhv1.survey.base.BaseFragment;
import com.example.mienhv1.survey.utils.view.CSGroupCheckbox;
import com.example.mienhv1.survey.utils.view.CSTextView;

import java.util.ArrayList;

/**
 * Created by HVM on 4/23/2017.
 */

public class CheckboxFragment extends BaseFragment {
    private CSTextView txtTitle;
    private CSGroupCheckbox grCheckboxParent;

    @Override
    protected int getResourcesLayout() {
        return R.layout.fragment_checkbox;
    }

    @Override
    protected void mapView(View view) {
        txtTitle = (CSTextView) view.findViewById(R.id.txt_title);
        grCheckboxParent = (CSGroupCheckbox) view.findViewById(R.id.gr_checkbox);


    }

    @Override
    protected void initData() {
        ArrayList<CheckboxModel> mList = new ArrayList<>();
        CheckboxModel data = new CheckboxModel(1, "CheckboxModel1", false);
        CheckboxModel data1 = new CheckboxModel(1, "CheckboxModel2", false);
        mList.add(data);
        mList.add(data1);
        CheckboxAdapter ckA = new CheckboxAdapter(getActivity(), R.layout.item_check_box_view, grCheckboxParent, mList);
        grCheckboxParent.setAdapter(ckA);

    }

    @Override
    protected void destroyView() {

    }
}

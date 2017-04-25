package com.example.mienhv1.survey.ui.fragment.checkbox;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import com.example.datasource.model.CheckboxModel;
import com.example.datasource.model.ItemAttributeModel;
import com.example.datasource.repository.DataRepository;
import com.example.datasource.repository.DataRepositoryFactory;
import com.example.mienhv1.survey.R;
import com.example.mienhv1.survey.ui.adapter.BaseViewAdapter;
import com.example.mienhv1.survey.utils.view.CSCheckBox;

import java.util.ArrayList;

/**
 * Created by HVM on 4/23/2017.
 */

public class CheckboxAdapter extends BaseViewAdapter<ItemAttributeModel> {

    public CheckboxAdapter(Context context, @LayoutRes int id, ViewGroup parent, ArrayList<CheckboxModel> data) {
        super(context, id, parent, data);
    }

    @Override
    public void bindView(View view, int position) {
        CardView item = (CardView) view;
        CSCheckBox ck=(CSCheckBox) item.getChildAt(0);
        ck.setText(mData.get(position).name_display);
        ck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                //DataRepository m = DataRepositoryFactory.createDataRepository(mContext);

            }
        });
    }
}

package com.example.mienhv1.survey.utils.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.example.datasource.model.ItemAttributeModel;
import com.example.mienhv1.survey.R;
import com.example.mienhv1.survey.ui.adapter.BaseViewAdapter;
import com.example.mienhv1.survey.ui.fragment.radiobutton.RadioButtonAdapter;

import java.util.ArrayList;

/**
 * Created by MienHV1 on 4/26/2017.
 */

public class CSGroupRadiobuttonEdittext extends LinearLayout {
    private CSRadioGroup groupRadio;
    private CSEditText csEditText;

    public CSGroupRadiobuttonEdittext(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mapView();
    }


    private void mapView() {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View root = inflater.inflate(R.layout.cs_item_survey_radiobutton_edittext, this, true);
        groupRadio = (CSRadioGroup) root.findViewById(R.id.cs_gr_radio);
        csEditText = (CSEditText) root.findViewById(R.id.cs_edittext_radio);
    }

    public void setData(ArrayList<ItemAttributeModel> data) {
        ArrayList mList = new ArrayList();
        for (int i = 0; i < data.size() - 1; i++)
            mList.add(data.get(i));
        RadioButtonAdapter adapter = new RadioButtonAdapter(getContext(), R.layout.item_radio_button, groupRadio, mList);
        groupRadio.setAdapter(adapter);
        if (data.size() > 1)
            csEditText.setHint(data.get(data.size() - 1).name_display);


        invalidate();
    }
}

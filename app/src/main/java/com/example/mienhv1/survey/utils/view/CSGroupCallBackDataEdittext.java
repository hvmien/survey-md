package com.example.mienhv1.survey.utils.view;

import android.content.Context;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;
import android.widget.RadioGroup;

import com.example.datasource.model.ItemAttributeModel;
import com.example.datasource.model.ItemQuestionModel;
import com.example.mienhv1.survey.MyApplication;
import com.example.mienhv1.survey.R;
import com.example.mienhv1.survey.ui.adapter.CallBackDataListener;
import com.example.mienhv1.survey.ui.fragment.radiobutton.RadioButtonAdapter;

import java.util.ArrayList;

/**
 * Created by MienHV1 on 4/26/2017.
 */

public class CSGroupCallBackDataEdittext extends LinearLayout implements CallBackDataListener {
    private CSRadioGroup groupRadio;
    private CSEditText csEditText;
    ArrayList<ItemAttributeModel> dataRb = new ArrayList<>();
    private Context mContext;

    public CSGroupCallBackDataEdittext(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mContext = MyApplication.getInstance().getApplicationContext();
        mapView();
        clearRadiobuttonFocussEditText();
    }

    private void mapView() {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View root = inflater.inflate(R.layout.cs_item_survey_radiobutton_edittext, this, true);
        groupRadio = (CSRadioGroup) root.findViewById(R.id.cs_gr_radio);
        csEditText = (CSEditText) root.findViewById(R.id.cs_edittext_radio);
    }

    private void clearRadiobuttonFocussEditText() {
        csEditText.setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                groupRadio.clearCheck();
            }
        });
        groupRadio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                csEditText.setText("");
                csEditText.setFocusable(true);
                csEditText.setFocusableInTouchMode(true);
                hideKeyboard();
            }
        });
    }

    private void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager)
                mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(
                csEditText.getWindowToken(), 0);
    }

    public void setData(ArrayList<ItemAttributeModel> data, ItemQuestionModel item) {
        dataRb = data;
        ArrayList mList = new ArrayList();
        for (int i = 0; i < data.size() - 1; i++)
            mList.add(data.get(i));
        RadioButtonAdapter adapter = new RadioButtonAdapter(getContext(), R.layout.item_radio_button, groupRadio, mList,this,item);
        groupRadio.setAdapter(adapter);
        if (data.size() > 1)
            csEditText.setHint(data.get(data.size() - 1).name_display);
        invalidate();
    }

    @Override
    public void onItemClick(ItemQuestionModel item,int id) {

    }
}

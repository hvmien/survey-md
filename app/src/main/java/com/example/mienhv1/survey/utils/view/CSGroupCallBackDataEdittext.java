package com.example.mienhv1.survey.utils.view;

import android.content.Context;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
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
import java.util.HashMap;

/**
 * Created by MienHV1 on 4/26/2017.
 */

public class CSGroupCallBackDataEdittext extends LinearLayout implements CallBackDataListener,
        RadioGroup.OnCheckedChangeListener, TextWatcher {
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
        csEditText.addTextChangedListener(this);
        groupRadio.setOnCheckedChangeListener(this);
    }

    private void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager)
                mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(
                csEditText.getWindowToken(), 0);
    }

    private void showKeyboard() {
        InputMethodManager imm = (InputMethodManager)
                mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(
                csEditText, InputMethodManager.SHOW_IMPLICIT);
    }

    ArrayList<ItemAttributeModel> mList;
    int mPosition;

    public void setData(ArrayList<ItemAttributeModel> data, ItemQuestionModel item) {
        dataRb = data;
        mList = new ArrayList();
        for (int i = 0; i < data.size() - 1; i++)
            mList.add(data.get(i));
        RadioButtonAdapter adapter = new RadioButtonAdapter(getContext(), R.layout.item_radio_button, groupRadio, mList, this, item);
        groupRadio.setAdapter(adapter);
        for (int i = 0; i < data.size(); i++) {
            meMap.put(data.get(i).name_column, "0");
        }
        if (data.size() > 1) {
            csEditText.setHint(data.get(data.size() - 1).name_display);
            meMap.put(data.get(data.size()-1).name_column, "");
        }

        invalidate();
    }

    HashMap<String, String> meMap = new HashMap<String, String>();

    public HashMap<String, String> getDataFromUser() {
        if (meMap.containsKey(dataRb.get(mList.size()-1).name_column)) {
            meMap.put(dataRb.get(dataRb.size()-1).name_column, csEditText.getText().toString());
        }
        return meMap;
    }

    @Override
    public void onItemClick(ItemQuestionModel item, int id, boolean ischeck) {

    }

    @Override
    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
        int radioButtonID = group.getCheckedRadioButtonId();
        View radioButton = group.findViewById(radioButtonID);
        int position = group.indexOfChild(radioButton);
        mPosition = position;
        if (meMap.containsKey(mList.get(mPosition).name_column)) {
            meMap.put(mList.get(mPosition).name_column, "1");
        }
        csEditText.removeTextChangedListener(this);
        csEditText.setText(null);
        csEditText.addTextChangedListener(this);
        hideKeyboard();

    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

        groupRadio.setOnCheckedChangeListener(null);
        groupRadio.clearCheck();
        groupRadio.setOnCheckedChangeListener(this);
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}

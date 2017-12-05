package com.example.mienhv1.survey.ui.fragment.info;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.datasource.model.AnswerMetaModel;
import com.example.datasource.model.AnswerModel;
import com.example.datasource.model.DataResponse;
import com.example.datasource.model.DistrictModel;
import com.example.datasource.model.ItemAttributeModel;
import com.example.datasource.model.ItemQuestionModel;
import com.example.datasource.model.ProvinceModel;
import com.example.datasource.model.SurveyPreModel;
import com.example.datasource.model.WardModel;
import com.example.mienhv1.survey.Constants;
import com.example.mienhv1.survey.R;
import com.example.mienhv1.survey.ui.adapter.EnumSurveyFragment;
import com.example.mienhv1.survey.ui.fragment.ItemBaseSurveyFragment;
import com.example.mienhv1.survey.utils.view.CustomSpinnerAdapter;

import java.util.ArrayList;

/**
 * Created by Forev on 17/04/20.
 */

public class InfoFragment<T> extends ItemBaseSurveyFragment implements InfoView {


    InfoPresenter presenter;
    private String TAG = "InfoFragment";
    private ProgressBar mProgressBar;
    private Spinner provinceSpinner;
    private Spinner districtSpinner;
    private Spinner wardSpinner;

    private String address = "";
    private String province = "";
    private String district = "";
    private String ward = "";
    private TextView addressTextView;
    private TextView titleQuestion;
    private TextView nameStoreEditText;
    private TextView homeNumberStreet;
    private TextView nameUserSurvey;
    public static final String BLANK_SPACE = "";
    private ItemQuestionModel item;
    private ArrayList<ItemAttributeModel> listQuestionMeta;

    public static InfoFragment newInstance(ItemQuestionModel mode) {
        Bundle args = new Bundle();
        args.putParcelable(Constants.ARG_ITEM_SURVEY, mode);
        InfoFragment fragment = new InfoFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getResourcesLayout() {
        return R.layout.fragment_general_information;
    }

    @Override
    protected void mapView(View view) {
        presenter = new InfoPresenter(getActivity(), this);
        mProgressBar = (ProgressBar) view.findViewById(R.id.progress_upload);
        addressTextView = (TextView) view.findViewById(R.id.address_text_view);
        homeNumberStreet = (TextView) view.findViewById(R.id.name_street);
        titleQuestion = (TextView) view.findViewById(R.id.txt_title);
        nameStoreEditText = (EditText) view.findViewById(R.id.name_store_editext);
        nameUserSurvey = (EditText) view.findViewById(R.id.name_user_survey);

        provinceSpinner = (Spinner) view.findViewById(R.id.spinner_province_id);
        districtSpinner = (Spinner) view.findViewById(R.id.spinner_district_id);
        wardSpinner = (Spinner) view.findViewById(R.id.spinner_ward_id);
    }

    @Override
    protected void initData() {
        super.initData();
        presenter.create();
        presenter.getProvinceList();
        item = getArguments().getParcelable(Constants.ARG_ITEM_SURVEY);
        titleQuestion.setText(item.order_rank + ". " + item.title);
        setDefaultValueSpinner(provinceSpinner, getResources().getString(R.string.default_value_province));
        setDefaultValueSpinner(districtSpinner, getResources().getString(R.string.default_value_district));
        setDefaultValueSpinner(wardSpinner, getResources().getString(R.string.default_value_ward));
    }

    private void setDefaultValueSpinner(Spinner spinner, String defaultvalue) {
        ArrayList<String> TempList = new ArrayList<>();
        TempList.add(defaultvalue);
        ArrayAdapter adapter = new CustomSpinnerAdapter<>(getActivity(), android.R.layout.simple_spinner_item, TempList);
        spinner.setAdapter(adapter);
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.start();
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.resume();
    }

    @Override
    public void onPause() {
        super.onPause();
        presenter.pause();
    }

    @Override
    public void onStop() {
        super.onStop();
        presenter.stop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.destroy();
    }

    @Override
    protected void destroyView() {

    }

    @Override
    public void onGetDataListenner(ArrayList<ItemAttributeModel> data) {
        if (data != null) {
            listQuestionMeta = new ArrayList<>();
            listQuestionMeta.addAll(data);
            String a = "ádfsd";
            Log.d("", a+"");
        }
    }

    @Override
    public void showProgress() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void showError(String error) {
        Toast.makeText(getActivity(), error, Toast.LENGTH_LONG).show();
    }

    @Override
    public EnumSurveyFragment fragmentType() {
        return EnumSurveyFragment.Common;
    }

    @Override
    public boolean checkData() {
        if (nameStoreEditText.getText().toString().equals(BLANK_SPACE)) {
            return false;
        }
        return true;
    }

    @Override
    public AnswerModel<T> getDataFromUserHandle() {
        AnswerModel result = new AnswerModel();
        ArrayList<AnswerMetaModel> arrayList = new ArrayList<>();

        for (int i = 0; i <listQuestionMeta.size(); i++) {
            AnswerMetaModel temp = new AnswerMetaModel();
            temp.idQuestionMeta = listQuestionMeta.get(i).id_question_meta;

            arrayList.add(temp);
        }

        InforModel inforModel = new InforModel();
        inforModel.namesystemstore="B'mart";
        inforModel.namestoredetail = nameStoreEditText.getText().toString();
        inforModel.province = province;
        inforModel.district = district;
        inforModel.ward = ward;
        inforModel.street = homeNumberStreet.getText().toString();
        inforModel.housenumber = homeNumberStreet.getText().toString();
        inforModel.namepersonsurvey = nameUserSurvey.getText().toString();


        result.idQuestion = item.order_rank;
        result.modelAnswerMeta = arrayList;
        return result;
    }

    public SurveyPreModel getInforSysDevicesPreSurvey() {
        SurveyPreModel preSurvey = new SurveyPreModel();
        preSurvey.id_survey_for_what=1;
        preSurvey.id_topic=1;
        preSurvey.id_account="minhduc";
        preSurvey.gps="1,1,1,";
        preSurvey.date_survey="12-12-2012";
        return preSurvey;
    }

    @Override
    public void getListProvince(DataResponse<ProvinceModel> mdataRes) {
        ArrayList<String> nameProvinceList = new ArrayList<>();
        for (int i = 0; i < mdataRes.data.size(); i++) {
            nameProvinceList.add(mdataRes.data.get(i).type + " - " + mdataRes.data.get(i).name);
        }
        setAdapterProvince(nameProvinceList, mdataRes.data);
    }

    @Override
    public void getListDistrict(DataResponse<DistrictModel> mdataRes) {
        ArrayList<String> nameDistrictList = new ArrayList<>();
        for (int i = 0; i < mdataRes.data.size(); i++) {
            nameDistrictList.add(mdataRes.data.get(i).type + " - " + mdataRes.data.get(i).name);
        }
        setAdapterDistrict(nameDistrictList, mdataRes.data);

    }

    @Override
    public void getListWard(DataResponse<WardModel> mdataRes) {
        ArrayList<String> nameWardList = new ArrayList<>();
        for (int i = 0; i < mdataRes.data.size(); i++) {
            nameWardList.add(mdataRes.data.get(i).type + " - " + mdataRes.data.get(i).name);
        }
        setAdapterWardList(nameWardList);
    }

    private void setAdapterWardList(ArrayList<String> nameWardList) {
        final ArrayList<String> tempList = new ArrayList<>();
        tempList.add(getResources().getString(R.string.default_value_ward));
        tempList.addAll(nameWardList);
        ArrayAdapter<String> adapter = new CustomSpinnerAdapter<>(getActivity(),
                android.R.layout.simple_spinner_item,
                tempList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        wardSpinner.setAdapter(adapter);
        wardSpinner.setOnItemSelectedListener(new MyProcessEventWard());
    }

    private void setAdapterDistrict(ArrayList<String> nameDistrictList, final ArrayList<DistrictModel> data) {
        final ArrayList<String> tempList = new ArrayList<>();
        tempList.add(getResources().getString(R.string.default_value_district));
        tempList.addAll(nameDistrictList);
        ArrayAdapter<String> adapter = new CustomSpinnerAdapter<>(getActivity(),
                android.R.layout.simple_spinner_item,
                tempList);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        districtSpinner.setAdapter(adapter);
        districtSpinner.setOnItemSelectedListener(new MyProcessEventDistrict(data));
    }

    private void setAdapterProvince(final ArrayList<String> nameProvinceList, final ArrayList<ProvinceModel> data) {
        final ArrayList<String> tempList = new ArrayList<>();
        tempList.add(getResources().getString(R.string.default_value_province));
        tempList.addAll(nameProvinceList);
        ArrayAdapter<String> adapter = new CustomSpinnerAdapter<>(getActivity(),
                android.R.layout.simple_spinner_item,
                tempList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        provinceSpinner.setAdapter(adapter);
        provinceSpinner.setOnItemSelectedListener(new MyProcessEvent(data));
    }


    private void getDistristViaProvince(ProvinceModel pos) {
        presenter.getDistristViaProvince(pos.provinceid);
    }

    private void getWardViaDistrict(DistrictModel pos) {
        presenter.getWardViaDistrict(pos.districtid);
    }

    // selected item spinner
    private class MyProcessEvent implements AdapterView.OnItemSelectedListener {
        ArrayList<ProvinceModel> nameProvinceList = new ArrayList<>();

        public MyProcessEvent(ArrayList<ProvinceModel> nameprovincelist) {
            this.nameProvinceList.addAll(nameprovincelist);
        }

        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            if (i > 0) {
                getDistristViaProvince(nameProvinceList.get(i - 1));
                province = adapterView.getItemAtPosition(i).toString();
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    }

    private class MyProcessEventDistrict implements AdapterView.OnItemSelectedListener {
        ArrayList<DistrictModel> nameDistrictList = new ArrayList<>();

        public MyProcessEventDistrict(ArrayList<DistrictModel> data) {
            this.nameDistrictList = data;
        }

        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            if (i > 0) {
                getWardViaDistrict(nameDistrictList.get(i - 1));
                district = adapterView.getItemAtPosition(i).toString();
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    }

    private class MyProcessEventWard implements AdapterView.OnItemSelectedListener {
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            if (i > 0) {
                ward = adapterView.getItemAtPosition(i).toString();
                addressTextView.setText(province + "," + district + "," + ward);
                address = addressTextView.getText().toString() + homeNumberStreet.getText().toString();
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    }
}

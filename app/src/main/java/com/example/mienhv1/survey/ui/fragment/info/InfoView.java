package com.example.mienhv1.survey.ui.fragment.info;

import com.example.datasource.model.AddressModel;
import com.example.datasource.model.DataResponse;
import com.example.datasource.model.DistrictModel;
import com.example.datasource.model.ProvinceModel;
import com.example.datasource.model.WardModel;
import com.example.mienhv1.survey.base.BaseView;

/**
 * Created by Forev on 17/04/20.
 */

public interface InfoView extends BaseView {
    void getListProvince(DataResponse<ProvinceModel> mdataRes);

    void getListDistrict(DataResponse<DistrictModel> mdataRes);

    void getListWard(DataResponse<WardModel> mdataRes);

}

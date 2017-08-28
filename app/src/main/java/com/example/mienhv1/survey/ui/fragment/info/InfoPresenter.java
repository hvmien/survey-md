package com.example.mienhv1.survey.ui.fragment.info;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.widget.Toast;

import com.example.datasource.model.DataResponse;
import com.example.datasource.model.DistrictModel;
import com.example.datasource.model.ImageRespone;
import com.example.datasource.model.ProvinceModel;
import com.example.datasource.model.WardModel;
import com.example.datasource.repository.DataRepository;
import com.example.datasource.repository.DataRepositoryFactory;
import com.example.datasource.usercases.GetListDistrictUserCase;
import com.example.datasource.usercases.GetListProvinceUserCase;
import com.example.datasource.usercases.GetListWardUserCase;
import com.example.datasource.usercases.UpLoadImageFileUserCase;
import com.example.mienhv1.survey.MyApplication;
import com.example.mienhv1.survey.base.BasePresenter;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStatusCodes;

import java.util.ArrayList;

import io.reactivex.observers.DisposableObserver;
import okhttp3.MultipartBody;

/**
 * Created by Forev on 17/04/20.
 */

public class InfoPresenter implements BasePresenter, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, ResultCallback<LocationSettingsResult>, LocationListener {

    private static final String TAG = "InfoPresenter";
    private static final int MY_PERMISSIONS_FINE_LOCATION = 1001;
    private InfoView infoView;
    private GoogleApiClient mGoogleApiClient;
    private Location mLastLocation = null;
    private Activity mActivity;
    private UpLoadImageFileUserCase upLoadImageFileUserCase;
    private GetListProvinceUserCase getListProvinceUserCase;
    private GetListDistrictUserCase getListDistrictUserCase;
    private GetListWardUserCase getListWardUserCase;
    private LocationRequest mLocationRequest;

    public InfoPresenter(Activity activity, InfoView view) {
        this.infoView = view;
        this.mActivity = activity;
    }

    @Override
    public void create() {
        initGoogleClientApi();
    }

    private void initGoogleClientApi() {
        mGoogleApiClient = new GoogleApiClient.Builder(MyApplication.getInstance())
                .addApi(LocationServices.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();
    }

    @Override
    public void start() {
        mGoogleApiClient.connect();
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {
        stopLocationUpdates();
    }

    @Override
    public void stop() {
        if (mGoogleApiClient != null)
            mGoogleApiClient.disconnect();
    }

    @Override
    public void destroy() {
        if (getListDistrictUserCase != null)
            getListDistrictUserCase.dispose();
    }

    protected void startLocationUpdates() {
        if (ActivityCompat.checkSelfPermission(mActivity, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(mActivity, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

        if (ActivityCompat.checkSelfPermission(MyApplication.getInstance(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(MyApplication.getInstance(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {


            ActivityCompat.requestPermissions(mActivity,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},
                    MY_PERMISSIONS_FINE_LOCATION);

        }

        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(
                mGoogleApiClient);
        if (mLastLocation != null) {
            Log.d(TAG, mLastLocation.toString());
        }
        createLocationRequest();
        startLocationUpdates();

    }


    protected void stopLocationUpdates() {
        LocationServices.FusedLocationApi.removeLocationUpdates(
                mGoogleApiClient, this);
    }

    protected void createLocationRequest() {
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(20000);
        mLocationRequest.setFastestInterval(60000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
                .addLocationRequest(mLocationRequest);

        PendingResult<LocationSettingsResult> result =
                LocationServices.SettingsApi.checkLocationSettings(mGoogleApiClient,
                        builder.build());

        result.setResultCallback(this);
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        // Log.d(TAG, connectionResult.getErrorMessage());
    }

    @Override
    public void onResult(@NonNull LocationSettingsResult result) {
        final Status status = result.getStatus();
        switch (status.getStatusCode()) {
            case LocationSettingsStatusCodes.SUCCESS:
                break;
            case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:

                break;
            case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                break;
        }

    }

    public void uploadImage(ArrayList<MultipartBody.Part> files) {
        infoView.showProgress();
        DataRepository dataRepository = DataRepositoryFactory.createDataRepository(mActivity);
        upLoadImageFileUserCase = new UpLoadImageFileUserCase(dataRepository);
        UpLoadImageFileUserCase.RequestValue requestValue = new UpLoadImageFileUserCase.RequestValue(files);
        upLoadImageFileUserCase.execute(new UpLoadImageFileObserver(), requestValue);
    }

    @Override
    public void onLocationChanged(Location location) {
        Log.d(TAG, "Location Change: " + location);
    }

    public void getProvinceList() {
        infoView.showProgress();
        DataRepository dataRepository = DataRepositoryFactory.createDataRepository(mActivity);
        getListProvinceUserCase = new GetListProvinceUserCase(dataRepository);
        getListProvinceUserCase.execute(new GetListProvinceObserver(), null);
    }

    public void getDistristViaProvince(String provinceid) {
        infoView.showProgress();
        DataRepository dataRepository = DataRepositoryFactory.createDataRepository(mActivity);
        getListDistrictUserCase = new GetListDistrictUserCase(dataRepository);
        GetListDistrictUserCase.RequestValue requestValue = new GetListDistrictUserCase.RequestValue(provinceid);
        getListDistrictUserCase.execute(new GetListDistrictObserver(), requestValue);
    }

    public void getWardViaDistrict(String districtid) {
        infoView.showProgress();
        DataRepository dataRepository = DataRepositoryFactory.createDataRepository(mActivity);
        getListWardUserCase = new GetListWardUserCase(dataRepository);
        GetListWardUserCase.RequestValue requestValue = new GetListWardUserCase.RequestValue(districtid);
        getListWardUserCase.execute(new GetListWardObserver(), requestValue);
    }

    private class UpLoadImageFileObserver extends DisposableObserver<DataResponse<ImageRespone>> {
        @Override
        public void onNext(DataResponse<ImageRespone> responseBody) {
            infoView.hideProgress();
            ArrayList<ImageRespone> list = responseBody.data;
            if (list != null && list.size() > 0) {
                for (int i = 0; i < list.size(); i++) {
                    Log.d("OnNext", list.get(i).image + "");
                }
            }

            Toast.makeText(mActivity, "onNext: " + responseBody.msg, Toast.LENGTH_LONG).show();
        }

        @Override
        public void onError(Throwable e) {
            infoView.hideProgress();
            Toast.makeText(mActivity, "onError" + e.getMessage(), Toast.LENGTH_LONG).show();
        }

        @Override
        public void onComplete() {

        }
    }

    private class GetListProvinceObserver extends DisposableObserver<DataResponse<ProvinceModel>> {
        @Override
        public void onNext(DataResponse<ProvinceModel> provinceModelDataResponse) {
            infoView.getListProvince(provinceModelDataResponse);
            infoView.hideProgress();
        }

        @Override
        public void onError(Throwable e) {
            infoView.hideProgress();
            infoView.showError(TAG + e.getMessage());
        }

        @Override
        public void onComplete() {

        }
    }

    private class GetListDistrictObserver extends DisposableObserver<DataResponse<DistrictModel>> {
        @Override
        public void onNext(DataResponse<DistrictModel> districtModelDataResponse) {

            infoView.hideProgress();
            infoView.getListDistrict(districtModelDataResponse);
        }

        @Override
        public void onError(Throwable e) {
            infoView.hideProgress();
            infoView.showError(e.getMessage());
        }

        @Override
        public void onComplete() {

        }
    }

    private class GetListWardObserver extends DisposableObserver<DataResponse<com.example.datasource.model.WardModel>> {
        @Override
        public void onNext(DataResponse<WardModel> wardModelDataResponse) {
            infoView.hideProgress();
            infoView.getListWard(wardModelDataResponse);
        }

        @Override
        public void onError(Throwable e) {
            infoView.hideProgress();
            infoView.showError(e.getMessage());
        }

        @Override
        public void onComplete() {

        }
    }
}

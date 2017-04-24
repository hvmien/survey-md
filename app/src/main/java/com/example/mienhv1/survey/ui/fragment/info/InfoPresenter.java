package com.example.mienhv1.survey.ui.fragment.info;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.widget.Toast;

import com.example.datasource.model.DataResponse;
import com.example.datasource.model.ImageRespone;
import com.example.datasource.repository.DataRepository;
import com.example.datasource.repository.DataRepositoryFactory;
import com.example.datasource.usercases.UpLoadImageFileUserCase;
import com.example.mienhv1.survey.MyApplication;
import com.example.mienhv1.survey.base.BasePresenter;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
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

public class InfoPresenter implements BasePresenter, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, ResultCallback<LocationSettingsResult> {

    private static final String TAG = "InfoPresenter";
    InfoView infoView;
    private GoogleApiClient mGoogleApiClient;
    private Location mLastLocation = null;
    private Context mContext;
    private UpLoadImageFileUserCase upLoadImageFileUserCase;

    public InfoPresenter(Context context,InfoView view) {
        this.infoView = view;
        this.mContext= context;
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

    }

    @Override
    public void stop() {
        if (mGoogleApiClient != null)
            mGoogleApiClient.disconnect();
    }

    @Override
    public void destroy() {

    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

        if (ActivityCompat.checkSelfPermission(MyApplication.getInstance(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(MyApplication.getInstance(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(
                mGoogleApiClient);
        if (mLastLocation != null) {
            Log.d(TAG, mLastLocation.toString());
        }

        createLocationRequest();

    }

    protected void createLocationRequest() {
        LocationRequest mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(10000);
        mLocationRequest.setFastestInterval(5000);
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

    public void uploadImage( ArrayList<MultipartBody.Part> files) {
        infoView.showProgress();
        DataRepository dataRepository = DataRepositoryFactory.createDataRepository(mContext);
        upLoadImageFileUserCase = new UpLoadImageFileUserCase(dataRepository);
        UpLoadImageFileUserCase.RequestValue requestValue = new UpLoadImageFileUserCase.RequestValue(files);
        upLoadImageFileUserCase.execute(new UpLoadImageFileObserver(),requestValue);
    }

    private class UpLoadImageFileObserver extends DisposableObserver<DataResponse<ImageRespone>> {
        @Override
        public void onNext(DataResponse<ImageRespone> responseBody) {
            infoView.hideProgress();
            ArrayList<ImageRespone> list = responseBody.data;
            if(list!=null&&list.size()>0){
                for (int i = 0; i < list.size(); i++) {
                    Log.d("OnNext",list.get(i).image+"");
                }
            }

            Toast.makeText(mContext, "onNext: " +responseBody.msg, Toast.LENGTH_LONG).show();
        }

        @Override
        public void onError(Throwable e) {
            infoView.hideProgress();
            Toast.makeText(mContext, "onError"+e.getMessage(), Toast.LENGTH_LONG).show();
        }

        @Override
        public void onComplete() {

        }
    }
}

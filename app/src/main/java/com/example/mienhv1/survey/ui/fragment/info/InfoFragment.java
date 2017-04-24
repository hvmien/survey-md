package com.example.mienhv1.survey.ui.fragment.info;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.esafirm.imagepicker.features.ImagePicker;
import com.esafirm.imagepicker.features.camera.CameraModule;
import com.esafirm.imagepicker.features.camera.ImmediateCameraModule;
import com.esafirm.imagepicker.features.camera.OnImageReadyListener;
import com.esafirm.imagepicker.model.Image;
import com.example.datasource.usercases.UpLoadImageFileUserCase;
import com.example.mienhv1.survey.R;
import com.example.mienhv1.survey.ui.adapter.EnumSurveyFragment;
import com.example.mienhv1.survey.ui.fragment.ItemBaseSurveyFragment;
import com.example.mienhv1.survey.utils.uploadimage.ProgressRequestBody;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

import static android.app.Activity.RESULT_OK;

/**
 * Created by Forev on 17/04/20.
 */

public class InfoFragment extends ItemBaseSurveyFragment implements InfoView, View.OnClickListener,ProgressRequestBody.UploadCallbacks {

    private ArrayList<Image> images = new ArrayList<>();
    private static final int RC_CODE_PICKER = 2000;
    private static final int RC_CAMERA = 3000;
    private TextView textView;
    private CameraModule cameraModule;

    InfoPresenter presenter;
    private String TAG = "InfoFragment";
    private Button pickimageButton;
    private Button uploadimageButton;
    private Button cameraonlyButton;
    private ProgressBar mProgressBar;
    private ProgressBar mProgressBarPercent;
    private TextView txtProgress;
    private UpLoadImageFileUserCase mUpLoadImageFileUserCase;
    private List<String> mUriString = new ArrayList<>();
    private List<Uri> mUriUri = new ArrayList<>();


    @Override
    protected int getResourcesLayout() {
        return R.layout.fragment_general_information;
    }

    @Override
    protected void mapView(View view) {
        presenter = new InfoPresenter(getActivity(), this);
        pickimageButton = (Button) view.findViewById(R.id.pick_image);
        cameraonlyButton = (Button) view.findViewById(R.id.camera_only);
        uploadimageButton = (Button) view.findViewById(R.id.upload_image);
        mProgressBar = (ProgressBar) view.findViewById(R.id.progress_upload);
        mProgressBarPercent = (ProgressBar) view.findViewById(R.id.progress_bar_percent);
        txtProgress = (TextView) view.findViewById(R.id.text_progress);
        textView = (TextView) view.findViewById(R.id.text_view);
    }

    @Override
    protected void initData() {
        presenter.create();
        pickimageButton.setOnClickListener(this);
        cameraonlyButton.setOnClickListener(this);
        uploadimageButton.setOnClickListener(this);


    }

    @Override
    protected void destroyView() {

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

    }

    @Override
    public EnumSurveyFragment fragmentType() {
        return EnumSurveyFragment.Common;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.pick_image) {
            start();
        }
        if (v.getId() == R.id.camera_only) {
            final String[] permissions = new String[]{Manifest.permission.CAMERA};
            if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(getActivity(), permissions, RC_CAMERA);
            } else {
                captureImage();
            }
        }
        if (v.getId() == R.id.upload_image) {
            if (getMutilPart() != null) {
                presenter.uploadImage(getMutilPart());
                txtProgress.setText(count + "/" + mUriString.size());
            }
        }
    }

    private ArrayList<MultipartBody.Part> getMutilPart() {
        if (mUriString != null && mUriString.size() > 0) {
            ArrayList< MultipartBody.Part> listPart = new ArrayList<>();

            MultipartBody.Builder builder = new MultipartBody.Builder();
            builder.setType(MultipartBody.FORM);

            for (int i = 0; i < mUriString.size(); i++) {
                String fileiPath = mUriString.get(i);
                File files = new File(fileiPath);

//                RequestBody requestFile = RequestBody.create(MediaType.parse("image/*"), files);
                ProgressRequestBody requestFile = new ProgressRequestBody(files,this);

                MultipartBody.Part body =
                        MultipartBody.Part.createFormData("photo", files.getName(), requestFile);
                listPart.add(body);
            }

            return listPart;
        }

        return null;
    }

    private String getRealPathFromURI(Uri contentURI) {
        String result;
        Cursor cursor = getActivity().getContentResolver().query(contentURI, null, null, null, null);
        if (cursor == null) {
            result = contentURI.getPath();
        } else {
            cursor.moveToFirst();
            int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            result = cursor.getString(idx);
            cursor.close();
        }
        return result;
    }

    private void captureImage() {
        startActivityForResult(
                getCameraModule().getCameraIntent(getActivity()), RC_CAMERA);
    }

    private void start() {
//        boolean returnAfterCapture = ((Switch) findViewById(R.id.ef_switch_return_after_capture)).isChecked();
//        boolean isSingleMode = ((Switch) findViewById(R.id.ef_switch_single)).isChecked();

        ImagePicker imagePicker = ImagePicker.create(this)
                .theme(R.style.ImagePickerTheme)
                .returnAfterFirst(true) // set whether pick action or camera action should return immediate result or not. Only works in single mode for image picker
                .folderMode(true) // set folder mode (false by default)
                .folderTitle("Folder") // folder selection title
                .imageTitle("Tap to select"); // image selection title

        if (false) {
            imagePicker.single();
        } else {
            imagePicker.multi(); // multi mode (default mode)
        }

        imagePicker.limit(10) // max images can be selected (99 by default)
                .showCamera(true) // show camera or not (true by default)
                .imageDirectory("Camera")   // captured image directory name ("Camera" folder by default)
                .origin(images) // original selected images, used in multi mode
                .start(RC_CODE_PICKER); // start image picker activity with request code
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_CODE_PICKER && resultCode == RESULT_OK && data != null) {
            images = (ArrayList<Image>) ImagePicker.getImages(data);
            mUriUri = (List<Uri>) data.getData();
            for (int i = 0; i < images.size(); i++) {
                mUriString.add(images.get(i).getPath());
            }
            printImages(images);
            return;
        }

        if (requestCode == RC_CAMERA && resultCode == RESULT_OK) {
            getCameraModule().getImage(getActivity(), data, new OnImageReadyListener() {
                @Override
                public void onImageReady(List<Image> resultImages) {
                    images = (ArrayList<Image>) resultImages;
                    printImages(images);
                }
            });
        }


    }

    private void printImages(List<Image> images) {
        if (images == null) return;

        StringBuilder stringBuffer = new StringBuilder();
        for (int i = 0, l = images.size(); i < l; i++) {
            stringBuffer.append(images.get(i).getPath()).append("\n");
        }
        textView.setText(stringBuffer.toString());
    }

    private ImmediateCameraModule getCameraModule() {
        if (cameraModule == null) {
            cameraModule = new ImmediateCameraModule();
        }
        return (ImmediateCameraModule) cameraModule;
    }

    int count =0;
    //test update progress
    @Override
    public void onProgressUpdate(int percentage) {
        mProgressBarPercent.setProgress(percentage);
        count++;
    }


    @Override
    public void onError() {
        Toast.makeText(getActivity(), "Progress update error", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFinish() {
        mProgressBarPercent.setProgress(100);
    }
}

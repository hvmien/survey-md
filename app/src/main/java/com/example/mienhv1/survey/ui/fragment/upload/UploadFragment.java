package com.example.mienhv1.survey.ui.fragment.upload;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.esafirm.imagepicker.features.ImagePicker;
import com.esafirm.imagepicker.features.camera.CameraModule;
import com.esafirm.imagepicker.features.camera.ImmediateCameraModule;
import com.esafirm.imagepicker.features.camera.OnImageReadyListener;
import com.esafirm.imagepicker.model.Image;
import com.example.datasource.model.ItemAttributeModel;
import com.example.datasource.model.ItemQuestionModel;
import com.example.datasource.model.PickImageModel;
import com.example.mienhv1.survey.Constants;
import com.example.mienhv1.survey.R;
import com.example.mienhv1.survey.ui.adapter.EnumSurveyFragment;
import com.example.mienhv1.survey.ui.adapter.RecyclerViewItemListener;
import com.example.mienhv1.survey.ui.adapter.upload.UploadAdapter;
import com.example.mienhv1.survey.ui.fragment.ItemBaseSurveyFragment;
import com.example.mienhv1.survey.utils.uploadimage.ProgressRequestBody;
import com.example.mienhv1.survey.utils.view.CSTextView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MultipartBody;

import static android.app.Activity.RESULT_OK;

/**
 * Created by Forev on 17/04/28.
 */

public class UploadFragment extends ItemBaseSurveyFragment implements RecyclerViewItemListener, ProgressRequestBody.UploadCallbacks {

    private ArrayList<Image> images = new ArrayList<>();
    private List<String> mUriString = new ArrayList<>();
    private List<Uri> mUriUri = new ArrayList<>();
    private static final int RC_CODE_PICKER = 2000;
    private static final int RC_CAMERA = 3000;
    private CameraModule cameraModule;

    RecyclerView rcUploads;
    CSTextView txtTitle;
    CSTextView textView;

    public static UploadFragment newInstance(ItemQuestionModel model) {

        Bundle args = new Bundle();
        args.putParcelable(Constants.ARG_ITEM_SURVEY, model);
        UploadFragment fragment = new UploadFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getResourcesLayout() {
        return R.layout.frgament_uploads;
    }

    @Override
    protected void mapView(View view) {
        txtTitle = (CSTextView) view.findViewById(R.id.txt_title_upload_image);
        textView = (CSTextView) view.findViewById(R.id.txt_title_value_image);
        rcUploads = (RecyclerView) view.findViewById(R.id.rc_uploads);
        rcUploads.setLayoutManager(new GridLayoutManager(getContext(), 3));
        ArrayList<PickImageModel> list = new ArrayList<>();
        list.add(new PickImageModel("Mặt Tiền"));
        list.add(new PickImageModel("Đường đi đối diện"));
        list.add(new PickImageModel("Đường đi bên trái"));
        list.add(new PickImageModel("Đường đi bên phải"));
        list.add(new PickImageModel("Tổng thể bên trong"));

        UploadAdapter adapter = new UploadAdapter(getContext(), this);
        adapter.updateData(list);

        rcUploads.setAdapter(adapter);

    }

    @Override
    protected void initData() {
        super.initData();
        ItemQuestionModel item = getArguments().getParcelable(Constants.ARG_ITEM_SURVEY);
        txtTitle.setText(item.order_rank + ". " + item.title);
    }

    @Override
    protected void destroyView() {

    }

    @Override
    public EnumSurveyFragment fragmentType() {
        return EnumSurveyFragment.Uploads;
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showError(String error) {

    }

    @Override
    public void onGetDataListenner(ArrayList<ItemAttributeModel> data) {

    }

    @Override
    public void onItemClick(int position) {
        Toast.makeText(getContext(), "click item: " + position, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemClickElement(String titleElement, int position) {
        Toast.makeText(getContext(), titleElement + "at: " + position, Toast.LENGTH_SHORT).show();
        if (titleElement.equals(Constants.CHOOSE_OPTION_PICK_IMAGE)) {
            start();
        }
    }

    private void start() {
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

    private ImmediateCameraModule getCameraModule() {
        if (cameraModule == null) {
            cameraModule = new ImmediateCameraModule();
        }
        return (ImmediateCameraModule) cameraModule;
    }

    private void captureImage() {
        startActivityForResult(
                getCameraModule().getCameraIntent(getActivity()), RC_CAMERA);
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

    private ArrayList<MultipartBody.Part> getMutilPart() {
        if (mUriString != null && mUriString.size() > 0) {
            ArrayList<MultipartBody.Part> listPart = new ArrayList<>();
            ArrayList<File> listFile = new ArrayList<>();

            for (int i = 0; i < mUriString.size(); i++) {
                String fileiPath = mUriString.get(i);
                File files = new File(fileiPath);
                listFile.add(files);
                ProgressRequestBody requestFile = new ProgressRequestBody(files, this);

                MultipartBody.Part body =
                        MultipartBody.Part.createFormData("photo", files.getName(), requestFile);
                listPart.add(body);
            }
            return listPart;
        }

        return null;
    }

    int count = 0;

    //test update progress
    @Override
    public void onProgressUpdate(int percentage, int total) {
//        mProgressBarPercent.setProgress(percentage);

        Log.d("InfomationFrag", "percent : " + percentage + "/" + total);
    }

    @Override
    public void onError() {
        Toast.makeText(getActivity(), "Progress update error", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFinish() {
        count++;
//        mProgressBarPercent.setProgress(100);
//        txtProgress.setText(count + "/" + mUriString.size());
        Log.d("InfomationFrag", "finish");
    }

}

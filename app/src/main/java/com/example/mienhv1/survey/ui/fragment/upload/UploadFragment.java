package com.example.mienhv1.survey.ui.fragment.upload;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.esafirm.imagepicker.features.camera.CameraModule;
import com.esafirm.imagepicker.features.camera.ImmediateCameraModule;
import com.esafirm.imagepicker.features.camera.OnImageReadyListener;
import com.esafirm.imagepicker.model.Image;
import com.example.datasource.model.AnswerModel;
import com.example.datasource.model.ItemAttributeModel;
import com.example.datasource.model.ItemQuestionModel;
import com.example.datasource.model.PickImageModel;
import com.example.datasource.repository.DataRepository;
import com.example.datasource.repository.DataRepositoryFactory;
import com.example.mienhv1.survey.Constants;
import com.example.mienhv1.survey.R;
import com.example.mienhv1.survey.ui.adapter.EnumSurveyFragment;
import com.example.mienhv1.survey.ui.adapter.OnItemRecyclerClickListener;
import com.example.mienhv1.survey.ui.adapter.OnposItemRecyclerClickListener;
import com.example.mienhv1.survey.ui.adapter.RecyclerViewItemListener;
import com.example.mienhv1.survey.ui.adapter.upload.UploadAdapter;
import com.example.mienhv1.survey.ui.adapter.upload.UploadImageAdapter;
import com.example.mienhv1.survey.ui.fragment.ItemBaseSurveyFragment;
import com.example.mienhv1.survey.utils.Utils;
import com.example.mienhv1.survey.utils.uploadimage.ProgressRequestBody;
import com.example.mienhv1.survey.utils.view.CSButton;
import com.example.mienhv1.survey.utils.view.CSTextView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MultipartBody;

import static android.app.Activity.RESULT_OK;

/**
 * Created by Forev on 17/04/28.
 */

public class UploadFragment extends ItemBaseSurveyFragment implements RecyclerViewItemListener,
        ProgressRequestBody.UploadCallbacks, View.OnClickListener,
        OnposItemRecyclerClickListener, OnItemRecyclerClickListener, UploadView {

    private ArrayList<Image> images = new ArrayList<>();
    private List<String> mUriString = new ArrayList<>();
    private static final int RC_CODE_PICKER = 2000;
    private static final int RC_CAMERA = 3000;
    private CameraModule cameraModule;

    RecyclerView rcUploads;
    UploadAdapter adapter;
    UploadImageAdapter upLoadAdapter;
    CSTextView txtTitle;
    CSTextView textView;
    CSButton buttonHoanThanh;
    ProgressBar mProgressBarUploadImg;
    ProgressBar mProgressItemRecyc;
    private ImageAndPos imageviewPos = new ImageAndPos();
    private ArrayList<UriPostModel> bmList = new ArrayList<>();

    private UploadPresenter mUploadPresenter;

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
        mProgressBarUploadImg = (ProgressBar) view.findViewById(R.id.progress_upload);
        textView = (CSTextView) view.findViewById(R.id.txt_title_value_image);
        buttonHoanThanh = (CSButton) view.findViewById(R.id.btn_hoan_thanh);
        buttonHoanThanh.setOnClickListener(this);
        rcUploads = (RecyclerView) view.findViewById(R.id.rc_uploads);
        rcUploads.setLayoutManager(new GridLayoutManager(getContext(), 3));


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
    public boolean checkData() {
        return false;
    }

    @Override
    public AnswerModel getDataFromUserHandle() {
        return null;
    }

    @Override
    public void showProgress() {
        mProgressBarUploadImg.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        mProgressBarUploadImg.setVisibility(View.GONE);
    }

    @Override
    public void showError(String error) {
        Log.d("Upload", error);
        Toast.makeText(getActivity(), error + "", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onGetDataListenner(ArrayList<ItemAttributeModel> data) {
        if (data != null) {
            ArrayList<PickImageModel> list = new ArrayList<>();
            for (int i = 0; i < data.size(); i++) {
                PickImageModel m = new PickImageModel();
                m.title = data.get(i).name_display;
                m.pos = i + 1 + "";
                list.add(m);
            }
            adapter = new UploadAdapter(getContext(), this);
            upLoadAdapter = new UploadImageAdapter(getContext());
            upLoadAdapter.setOnPosItemRecyclerClickListener(this);
            upLoadAdapter.setOnItemRecyclerClickListener(this);
            upLoadAdapter.updatesItem(list);
            rcUploads.setAdapter(upLoadAdapter);
        }
    }

    @Override
    public void onItemClick(int position) {
        Toast.makeText(getContext(), "click item: " + position, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemClickElement(String titleElement, int position) {
        final Activity activity = getActivity();
        final String[] permissions = new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE};
        if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity, permissions, RC_CAMERA);
            ActivityCompat.requestPermissions(activity, permissions, 200);
            ActivityCompat.requestPermissions(activity, permissions, 100);
        } else {

            captureImage();
        }
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
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == RC_CAMERA) {
            if (grantResults.length != 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                captureImage();
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //from camera
        if (requestCode == RC_CAMERA && resultCode == RESULT_OK) {
            getCameraModule().getImage(getActivity(), data, new OnImageReadyListener() {
                @Override
                public void onImageReady(List<Image> resultImages) {
                    images = (ArrayList<Image>) resultImages;
                    new GetImageTask().execute();

                }
            });
        }

    }


    private void checkContainData(UriPostModel model) {
        if (bmList != null && bmList.size() > 0) {
            for (UriPostModel m : bmList) {
                if (m.positionimage == model.positionimage) {
                    bmList.remove(m);
                    break;
                }

            }
        }
    }

    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 50, bytes);
        String path;
        path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }

    public String getRealPathFromURI(Uri contentUri) {
        Cursor cursor = null;
        try {
            String[] proj = {MediaStore.Images.Media.DATA};
            cursor = getActivity().getContentResolver().query(contentUri, proj, null, null, null);
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    private ArrayList<MultipartBody.Part> getMutilPart(List<String> uriStringList) {
        if (uriStringList != null && uriStringList.size() > 0) {
            ArrayList<MultipartBody.Part> listPart = new ArrayList<>();
            ArrayList<File> listFile = new ArrayList<>();

            for (int i = 0; i < uriStringList.size(); i++) {
                String fileiPath = uriStringList.get(i);
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
    }

    @Override
    public void upload() {
        DataRepository dataRepository = DataRepositoryFactory.createDataRepository(getActivity());
        mUploadPresenter = new UploadPresenter(dataRepository, this);

        if (bmList.size() > 0) {
            for (int i = 0; i < bmList.size(); i++) {
                mUriString.add(bmList.get(i).uri.toString());
            }
            bmList.clear();
            mUploadPresenter.uploadImage(getMutilPart(mUriString));

        } else {
            mListener.warrning("Chưa có hình ảnh minh họa!");
        }
    }

    public void setOnCallbackUpload(OnCallbackUpload mListener) {
        this.mListener = mListener;
    }

    private OnCallbackUpload mListener;

    @Override
    public void onSuccessUploadImage(String msg) {
        mListener.onSuccess(msg);
    }

    @Override
    public void onErrorUploadImage(String error) {
        mListener.onError(error);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_hoan_thanh) {
            DataRepository dataRepository = DataRepositoryFactory.createDataRepository(getActivity());
            mUploadPresenter = new UploadPresenter(dataRepository, this);

            if (bmList.size() > 0) {
                for (int i = 0; i < bmList.size(); i++) {
                    mUriString.add(bmList.get(i).uri.toString());
                }
                bmList.clear();
                mUploadPresenter.uploadImage(getMutilPart(mUriString));

            }

        }
    }

    //cs upload adapter
    @Override
    public void onItemClick(Object o, int position) {

    }

    @Override
    public void onElementItemClick(ProgressBar progress, String string, int position) {
        final Activity activity = getActivity();
        final String[] permissions = new String[]{Manifest.permission.CAMERA};
        if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity, permissions, RC_CAMERA);

            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 200);
            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 100);
        } else {

            captureImage();
        }
        mProgressItemRecyc = progress;
    }

    @Override
    public void onItemClick(Object o) {

    }

    @Override
    public void onItemClickImage(String title, ImageView timage, int pos) {
        imageviewPos.titleImage = title;
        imageviewPos.imageView = timage;
        imageviewPos.posImage = pos;
    }

    @Override
    public void showProgressItemRecyc() {

    }

    class GetImageTask extends AsyncTask<String, int[], Bitmap> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mProgressItemRecyc.setVisibility(View.VISIBLE);
        }

        @Override
        protected Bitmap doInBackground(String... params) {

            Bitmap photo = Utils.loadBitmap(images.get(0).getPath());
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            photo.compress(Bitmap.CompressFormat.JPEG, 50, stream);
            //convert bitmap to uri

            Uri selectedImage = getImageUri(getActivity(), photo);
            String realPath = getRealPathFromURI(selectedImage);
            selectedImage = Uri.parse(realPath);

            //get list image
            UriPostModel m = new UriPostModel();
            m.uri = selectedImage;
            m.positionimage = imageviewPos.posImage;
            m.titleImage = imageviewPos.titleImage;

            checkContainData(m);
            bmList.add(m);
            return photo;
        }


        @Override
        protected void onPostExecute(Bitmap bitmapResult) {
            super.onPostExecute(bitmapResult);
            // This is back on your UI thread - Add your image to your view
            Toast.makeText(getActivity(), bmList.size() + "", Toast.LENGTH_SHORT).show();
            mProgressItemRecyc.setVisibility(View.GONE);
            imageviewPos.imageView.setImageBitmap(bitmapResult);
        }
    }

    public interface OnCallbackUpload {
        void onSuccess(String msg);

        void onError(String error);

        void warrning(String warn);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mUploadPresenter != null)
            mUploadPresenter.destroy();
    }
}


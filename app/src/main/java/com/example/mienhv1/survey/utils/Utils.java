package com.example.mienhv1.survey.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by MienHV1 on 4/11/2017.
 */

public class Utils {
    public static Bitmap scaleDown(Bitmap realImage, float maxImageSize,
                                   boolean filter) {
        float ratio = Math.min(
                (float) maxImageSize / realImage.getWidth(),
                (float) maxImageSize / realImage.getHeight());
        int width = Math.round((float) ratio * realImage.getWidth());
        int height = Math.round((float) ratio * realImage.getHeight());

        Bitmap newBitmap = Bitmap.createScaledBitmap(realImage, width,
                height, filter);
        return newBitmap;
    }

    public static boolean isNetworkAvailable() {
        return true;
    }

    public static void hideKeyboard(@NonNull Activity activity) {
        // Check if no view has focus:
        View view = activity.getCurrentFocus();
        if (view != null) {
            InputMethodManager inputManager = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            inputManager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }
    public static Bitmap loadBitmap(String url) {
        Bitmap bm = null;
        InputStream is = null;
        BufferedInputStream bis = null;
        try {
            URLConnection conn = new URL(url).openConnection();
            conn.connect();
            is = conn.getInputStream();
            bis = new BufferedInputStream(is, 8192);
            bm = BitmapFactory.decodeStream(bis);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return bm;
    }


//    private void start() {
//        ImagePicker imagePicker = ImagePicker.create(this)
//                .theme(R.style.ImagePickerTheme)
//                .returnAfterFirst(true) // set whether pick action or camera action should return immediate result or not. Only works in single mode for image picker
//                .folderMode(true) // set folder mode (false by default)
//                .folderTitle("Folder") // folder selection title
//                .imageTitle("Tap to select"); // image selection title
//
//        if (false) {
//            imagePicker.single();
//        } else {
//            imagePicker.multi(); // multi mode (default mode)
//        }
//
//        imagePicker.limit(10) // max images can be selected (99 by default)
//                .showCamera(true) // show camera or not (true by default)
//                .imageDirectory("Camera")   // captured image directory name ("Camera" folder by default)
//                .origin(images) // original selected images, used in multi mode
//                .start(RC_CODE_PICKER); // start image picker activity with request code
//    }

//            if (requestCode == RC_CODE_PICKER && resultCode == RESULT_OK && data != null) {
//        images = (ArrayList<Image>) ImagePicker.getImages(data);
//        for (int i = 0; i < images.size(); i++) {
//            mUriString.add(images.get(i).getPath());
//        }
//        printImages(images);
//        return;
//    }


    public static void replaceFragmentToActivity(@NonNull FragmentManager fragmentManager,
                                                 @NonNull Fragment fragment, int frameId, String tag) {
        try {
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.replace(frameId, fragment, tag);
            transaction.commit();
        } catch (Exception ignored) {
            Log.e("Utils", String.valueOf(ignored));
        }
    }
}

package com.example.mienhv1.survey.utils.uploadimage;

import android.os.Handler;
import android.os.Looper;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.BufferedSink;

/**
 * Created by HVM on 4/24/2017.
 */

public class ProgressRequestBody extends RequestBody {
    private File mFileList;
    private String mPath;
    private UploadCallbacks mListener;

    private static final int DEFAULT_BUFFER_SIZE = 2048;

    public interface UploadCallbacks {
        void onProgressUpdate(int percentage,int total);
        void onError();
        void onFinish();
    }

    public ProgressRequestBody(final File file, final  UploadCallbacks listener) {
        mFileList = file;
        mListener = listener;
    }

    @Override
    public MediaType contentType() {
        // i want to upload only images
        return MediaType.parse("image/*");
    }

    @Override
    public long contentLength() throws IOException {
//        long sum=0;
//        for (int i = 0; i < mFileList.size(); i++) {
//            sum+= mFileList.get(i).length();
//        }
        return mFileList.length();
    }

    @Override
    public void writeTo(BufferedSink sink) throws IOException {
//        for (int i = 0; i < mFileList.size(); i++) {
            long fileLength = mFileList.length();
            byte[] buffer = new byte[DEFAULT_BUFFER_SIZE];
            FileInputStream in = new FileInputStream(mFileList);
            long uploaded = 0;

            try {
                int read;
                Handler handler = new Handler(Looper.getMainLooper());
                while ((read = in.read(buffer)) != -1) {

                    // update progress on UI thread
                    handler.post(new ProgressUpdater(uploaded, fileLength));

                    uploaded += read;
                    sink.write(buffer, 0, read);
                }
            } finally {
                in.close();
            }
//        }

    }
    int count=0;
    private class ProgressUpdater implements Runnable {
        private long mUploaded;
        private long mTotal;
        public ProgressUpdater(long uploaded, long total) {
            mUploaded = uploaded;
            mTotal = total;
        }

        @Override
        public void run() {

            mListener.onProgressUpdate((int)(100 * mUploaded / mTotal),(int)mTotal);

            if((int)(100 * mUploaded / mTotal)==99&&count==0){
                mListener.onFinish();
                count++;
            }
        }
    }
}

package com.zuhra.sharemebackendv2.utils;

import android.graphics.Bitmap;
import android.os.AsyncTask;

/**
 * Created by zuhra on 16.07.2016.
 */
public class SaveQRCodeAsyncTask extends AsyncTask<Void, Void, Boolean> {

    private QR_Code qrCode;
    private int x, y;
    private Bitmap bitmap;
    private AsyncTaskListener listener;

    public SaveQRCodeAsyncTask(AsyncTaskListener listener) {
        this.listener = listener;
        qrCode = new QR_Code();
    }

    @Override
    protected Boolean doInBackground(Void... params) {
        if(bitmap != null){
            qrCode.saveChart(bitmap, x, y);
            return true;
        } else {
            return false;
        }
    }

    public void setParams(int width, int height){
        x = width;
        y = height;
    }

    public void setBitmap(Bitmap bitmap){
        this.bitmap = bitmap;
    }

    @Override
    protected void onPostExecute(Boolean result) {
        if(listener != null && result){
            listener.onFinish(result);
        }
    }
}

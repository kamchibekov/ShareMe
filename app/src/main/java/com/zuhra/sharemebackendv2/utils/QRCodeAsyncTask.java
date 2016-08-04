package com.zuhra.sharemebackendv2.utils;

import android.os.AsyncTask;

import com.backendless.Backendless;
import com.zuhra.sharemebackendv2.entyties.Codes;

import java.util.ArrayList;

/**
 * Created by zuhra on 16.07.2016.
 */
public class QRCodeAsyncTask extends AsyncTask<Integer, Void, ArrayList<String>> {

    private AsyncTaskListener listener;
    private ArrayList<String> listId = new ArrayList<>();

    public QRCodeAsyncTask(AsyncTaskListener listener) {
        this.listener = listener;
    }

    @Override
    protected ArrayList<String> doInBackground(Integer... params) {

        int finalEditTextNum = params[0] > 20 ? 20 : params[0];

        for (int i = 0; i < finalEditTextNum; i++) {
            Codes codes = new Codes();

            codes.setNum(finalEditTextNum);

            Codes newObj = Backendless.Persistence.of(Codes.class).save(codes);
            if(newObj != null){
                listId.add(i,newObj.getObjectId());
                Backendless.Persistence.of(Codes.class).remove(newObj);
            }

        }


        return listId;
    }

    @Override
    protected void onPostExecute(ArrayList<String> list) {
        if(listener!= null){
            listener.setQRCodeList(list);
        }
    }
}

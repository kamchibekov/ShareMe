package com.zuhra.sharemebackendv2.view;

/**
 * Created by zuhra on 16.07.2016.
 */

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageView;

import com.google.zxing.WriterException;
import com.zuhra.sharemebackendv2.R;
import com.zuhra.sharemebackendv2.utils.AsyncTaskListener;
import com.zuhra.sharemebackendv2.utils.QRCodeAsyncTask;
import com.zuhra.sharemebackendv2.utils.QR_Code;
import com.zuhra.sharemebackendv2.utils.SaveQRCodeAsyncTask;

import java.util.ArrayList;

public class GenerateQRCode extends BaseActivity implements AsyncTaskListener {

    private GridLayout gridView;
    private EditText number;
    private Bitmap qrImage;
    private QR_Code qrCode = new QR_Code();
    private GridLayout layout;
    private Button generateButton;
    private Button saveButton;
    private boolean hasFile = false;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        layout = (GridLayout) findViewById(R.id.gridView);
        generateButton = (Button) findViewById(R.id.qrBut);
        saveButton = (Button) findViewById(R.id.save);
        gridView = (GridLayout) findViewById(R.id.gridView);
        number = (EditText) findViewById(R.id.numberPicker);


//------------------------------------------------------------------------------------
        assert generateButton != null;
        generateButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                showHideProgressBar(true);
                generateButton.setClickable(false);
                saveButton.setClickable(false);
                gridView.removeAllViews();
                int editTextNum = 1;
                if (!TextUtils.isEmpty(number.getText()))
                    editTextNum = Integer.parseInt(number.getText().toString());


                QRCodeAsyncTask start = new QRCodeAsyncTask(GenerateQRCode.this);

                start.execute(editTextNum);
                if (editTextNum > 20)
                    Snackbar.make(findViewById(android.R.id.content),
                            "Макс генерация кода 20 шт", Snackbar.LENGTH_LONG).show();
            }


        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
                        && ContextCompat.checkSelfPermission(GenerateQRCode.this,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED){
                    requestPermission();
                    return;
                }
                saveFile();
            }
        });


    }

    private void saveFile() {
        if(!hasFile) return;
        showHideProgressBar(true);
        generateButton.setClickable(false);
        saveButton.setClickable(false);

        SaveQRCodeAsyncTask asyncTask = new SaveQRCodeAsyncTask(GenerateQRCode.this);
        asyncTask.setParams(gridView.getMeasuredHeight(), gridView.getMeasuredWidth());
        asyncTask.setBitmap(qrCode.getBitmap(gridView));

        asyncTask.execute();
    }


    private void requestPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale
                (this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            Snackbar.make(findViewById(android.R.id.content),
                    "Нужен доступ к памяти телефона", Snackbar.LENGTH_LONG).setAction("OK",
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            ActivityCompat.requestPermissions(GenerateQRCode.this,
                                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 13);
                        }
                    }).show();
        } else
            ActivityCompat.requestPermissions(GenerateQRCode.this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 13);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if (requestCode == 13 && grantResults.length > 0) {
            View view = findViewById(android.R.id.content);
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED){
                saveFile();
                Snackbar.make(
                        view, "Доступ к памяти получен, могу сохранить", Snackbar.LENGTH_SHORT).show();
            }

            else
                Snackbar.make(
                        view, "Доступ к памяти неполучен, немогу сохранить", Snackbar.LENGTH_SHORT).show();

        } else super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setQRCodeList(final ArrayList<String> list) {

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                layout.removeAllViews();
                for (int i = 0; i < list.size(); i++) {
                    try {
                        qrImage = qrCode.encodeAsBitmap(list.get(i));
                        Bitmap bt = qrCode.bitmapText(qrImage, list.get(i));
                        ImageView imageView = new ImageView(GenerateQRCode.this);
                        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                        imageView.setImageBitmap(bt);
                        GridLayout.LayoutParams params = new GridLayout.LayoutParams();

                        params.setMargins(12, 12, 12, 12);

                        imageView.setLayoutParams(params);

                        layout.addView(imageView);

                    } catch (WriterException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        showHideProgressBar(false);
        generateButton.setClickable(true);
        saveButton.setClickable(true);
        hasFile = true;
    }

    @Override
    public void onFinish(boolean result) {
        showHideProgressBar(false);
        showSnackBar("Файл сохранен");
        generateButton.setClickable(true);
        saveButton.setClickable(true);
        hasFile = false;
    }

}
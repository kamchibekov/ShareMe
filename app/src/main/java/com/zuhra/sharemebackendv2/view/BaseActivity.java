package com.zuhra.sharemebackendv2.view;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.zuhra.sharemebackendv2.R;


/**
 * Created by zuhra on 15.07.2016.
 */
public class BaseActivity extends AppCompatActivity {
    private static final String TAG = BaseActivity.class.getName();
    protected MenuItem progressbar;
    public final static String APP_ID = "92632DE0-DE22-B967-FF1F-70DB5853FA00";
    public final static String APP_SECRET_KEY = "9BA799D1-B4A5-B254-FF5F-B34868CBBD00";
    public final static String APP_VERSION = "v1";

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.progress_bar_menu, menu);
        Log.d("andy", "onCreateOptionsMenu: ");
        return true;
    }

    protected void showHideProgressBar(boolean state){
        if(progressbar != null){
            progressbar.setVisible(state);
        }
    }

    protected void showSnackBar(String message){
        View view = findViewById(android.R.id.content);
        assert view != null;
        Snackbar.make(view, message, Snackbar.LENGTH_SHORT).show();
    }


    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        progressbar = menu.findItem(R.id.progress_bar_menu_item);
        progressbar.setVisible(false);
        Log.d(TAG, "onPrepareOptionsMenu: ");
        return super.onPrepareOptionsMenu(menu);
    }



}

package com.zuhra.sharemebackendv2.view;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.zuhra.sharemebackendv2.R;
import com.zuhra.sharemebackendv2.entyties.Products;
import com.zuhra.sharemebackendv2.utils.Utils;


public class ProductView extends BaseActivity {

    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_view);

        Backendless.initApp(this, APP_ID, APP_SECRET_KEY, APP_VERSION);


        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        id = intent.getStringExtra("ProductId");

    }

    private void init(){
        Backendless.Persistence.of(Products.class).findById(id, new AsyncCallback<Products>() {
            @Override
            public void handleResponse(Products products) {

                TextView name = (TextView) findViewById(R.id.view_name);
                TextView category_id = (TextView) findViewById(R.id.category_id);
                TextView brand_id = (TextView) findViewById(R.id.brand_id);
                TextView code = (TextView) findViewById(R.id.code);
                TextView serial_no = (TextView) findViewById(R.id.serial_no);
                TextView release_date = (TextView) findViewById(R.id.release_date);
                TextView add_date = (TextView) findViewById(R.id.add_date4);
                TextView note = (TextView) findViewById(R.id.note);

                name.setText(products.getName());
                category_id.setText(products.getCategory_id().get(0).getName());
                brand_id.setText(products.getBrand_id().get(0).getName());
                code.setText(products.getCode());
                serial_no.setText(products.getSerial_no());

                if(products.getAdd_date() != null)
                    add_date.setText(Utils.getDate(products.getAdd_date().getTime()));


                if(products.getRelease_date() != null)
                    release_date.setText(Utils.getDate(products.getRelease_date().getTime()));


                note.setText(products.getNote());

                showHideProgressBar(false);

            }

            @Override
            public void handleFault(BackendlessFault e) {
                showSnackBar(e.getMessage() + " : " + e.getDetail());
                showHideProgressBar(false);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            onBackPressed();
        }

        if(item.getItemId() == R.id.edit_product){
            Intent intent = new Intent(this, NewProduct.class);
            intent.putExtra("edit_product", id);
            startActivity(intent);
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.new_product_menu, menu);

        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        progressbar = menu.findItem(R.id.progress_bar_menu_item);
        progressbar.setVisible(true);

        init();

        return true;
    }
}


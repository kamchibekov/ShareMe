package com.zuhra.sharemebackendv2.view;

/**
 * Created by zuhra on 14.07.2016.
 */

import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.backendless.Backendless;
import com.backendless.BackendlessCollection;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.persistence.BackendlessDataQuery;
import com.backendless.persistence.QueryOptions;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.zuhra.sharemebackendv2.R;
import com.zuhra.sharemebackendv2.entyties.Brands;
import com.zuhra.sharemebackendv2.entyties.Categories;
import com.zuhra.sharemebackendv2.entyties.Memberships;
import com.zuhra.sharemebackendv2.entyties.Orgs;
import com.zuhra.sharemebackendv2.entyties.Products;
import com.zuhra.sharemebackendv2.utils.DatePickerFragment;
import com.zuhra.sharemebackendv2.utils.Utils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;



public class NewProduct extends BaseActivity {

    private static final String TAG = NewProduct.class.getName();
    public static final Integer PAGE_SIZE = 50;
    //Widget wars!
    EditText editName, editSerialNo, editRelease, editAdd, editNote;
    Button buttonSave, buttonClear, buttonReleaseDate, buttonAddDate;
    Spinner spinnerCategory, spinnerBrand;
    //Data wars!
    Date dateRelease, dateAdd;
    ArrayList dataC, dataB, dataCid, dataBid;
    ArrayAdapter<String> adapterCat, adapterBra;
    String[] dataCat, dataBra;
    List<Categories> BLCategoriesList = new ArrayList<>();
    List<Brands> BLBrandList = new ArrayList<>();
    List<Orgs> BLOrgList = new ArrayList<>();


    Categories categories = new Categories();
    Brands brands = new Brands();
    Orgs orgs = new Orgs();
    private String id;
    private boolean isEdit = false;
    private String userId;
    private Products oldProduct;
    private TextView QRCode;
    private Date pickedReleaseDate;
    private Date pickedAddDate;
    private IntentIntegrator QRCodeSanner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_product);
        QRCodeSanner = new IntentIntegrator(this);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);


        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN
        );

        dataC = new ArrayList(); // Category
        dataB = new ArrayList();  // Brands
        dataCid = new ArrayList(); // Category_id
        dataBid = new ArrayList(); // Brand_id

        editName = (EditText) findViewById(R.id.editTextName);
        editSerialNo = (EditText) findViewById(R.id.editTextSerial_No);
        editAdd = (EditText) findViewById(R.id.editTextAddDate);
        editRelease = (EditText) findViewById(R.id.editTextReleaseDate);
        QRCode = (TextView) findViewById(R.id.editTextQRCode);
        editNote = (EditText) findViewById(R.id.editTextNote);

        buttonSave = (Button) findViewById(R.id.buttonSave);
        buttonClear = (Button) findViewById(R.id.buttonClear);

        spinnerCategory = (Spinner) findViewById(R.id.spinnerCategory);
        spinnerBrand = (Spinner) findViewById(R.id.spinnerBrand);

        QRCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                QRCodeSanner.initiateScan();

            }
        });

        final Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY));
        editRelease.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {  //Button to open calendar for Release Date

                Log.d(TAG, "onClick:editRelease ");
                DialogFragment datePickerFragment = new DatePickerFragment() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        calendar.set(Calendar.YEAR, year);
                        calendar.set(Calendar.MONTH, monthOfYear);
                        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                        pickedReleaseDate = calendar.getTime();
                        editRelease.setText(Utils.getDate(calendar.getTimeInMillis()));
                    }

                    @Override
                    public void onCancel(DialogInterface dialog) {
                        editRelease.setText(null);
                    }
                };
                datePickerFragment.show(getFragmentManager(), "DatePickerFragment");


            }

        });

        editAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {  // BUtton to open calendar for Add Date

                DialogFragment datePickerFragment = new DatePickerFragment() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        calendar.set(Calendar.YEAR, year);
                        calendar.set(Calendar.MONTH, monthOfYear);
                        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                        pickedAddDate = calendar.getTime();
                        editAdd.setText(Utils.getDate(calendar.getTimeInMillis()));
                    }

                    @Override
                    public void onCancel(DialogInterface dialog) {
                        editRelease.setText(null);
                    }
                };
                datePickerFragment.show(getFragmentManager(), "DatePickerFragment");

            }
        });

        adapterCat = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, dataC); //Data for Category Spinner
        spinnerCategory.setAdapter(adapterCat);

        spinnerCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                int pos = spinnerCategory.getSelectedItemPosition();
                categories = BLCategoriesList.get(pos);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        adapterBra = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, dataB); //Data for Brand Spinner
        spinnerBrand.setAdapter(adapterBra);

        spinnerBrand.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                brands = BLBrandList.get(spinnerBrand.getSelectedItemPosition());

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonSave.setEnabled(false);
                showHideProgressBar(true);
                saveToBackendless();

            }
        });

        buttonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearEditTexts();
            }
        });




    }



    private void init(){

        takeOutFromBackendlessCategories(); //uncomment if no


        if(hasIntent()){
            setTitle("Edit");
            showHideProgressBar(true);
            Backendless.Persistence.of(Products.class).findById(id, new AsyncCallback<Products>() {
                @Override
                public void handleResponse(Products products) {
                    oldProduct = products;
                    Log.d(TAG, "handleResponse: " + products);
                    editName.setText(products.getName());
                    editNote.setText(products.getNote());
                    QRCode.setText(products.getCode());
                    editSerialNo.setText(products.getSerial_no());
                    if(products.getAdd_date() != null){
                        editAdd.setText(products.getAdd_date().toString());
                    }
                    if(products.getRelease_date() != null){
                        editRelease.setText(products.getRelease_date().toString());
                    }
                    showHideProgressBar(false);
                }

                @Override
                public void handleFault(BackendlessFault e) {
                    showSnackBar(e.getMessage() +" : "  + e.getDetail());

                    Log.d(TAG, "handleFault: " + e.getDetail());
                    Log.d(TAG, "handleFault: " + e.getMessage());
                }
            });
        }

        SharedPreferences preferences = getSharedPreferences("Preference", MODE_PRIVATE);

        userId = preferences.getString("UserId", null);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null) {
            if(result.getContents() == null) {
                showSnackBar("Cancelled");
            } else {
                showSnackBar("Scanned: " + result.getContents());
                QRCode.setText(result.getContents());
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        progressbar = menu.findItem(R.id.progress_bar_menu_item);
        progressbar.setVisible(true);
        init();
        Log.d(TAG, "onPrepareOptionsMenu: ");
        return true;
    }

    private boolean hasIntent() {
        Intent intent = getIntent();
        if(intent != null && intent.hasExtra("edit_product")){
            id = intent.getStringExtra("edit_product");
            isEdit = true;
            return true;
        }

        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }

        return super.onOptionsItemSelected(item);
    }

    private void saveToBackendless() {



        if (userId != null) {
            Log.d(TAG, "saveToBackendless: userID " + userId);
            BackendlessDataQuery query = new BackendlessDataQuery();
            query.setWhereClause("user_id.objectId='" + userId + "'");
            Backendless.Persistence.of(Memberships.class).find(query, new AsyncCallback<BackendlessCollection<Memberships>>() {
                @Override
                public void handleResponse(BackendlessCollection<Memberships> memberships) {
                    Log.d(TAG, "handleResponse: " + memberships.getData().size());
                    List<Orgs> listOrg = new ArrayList<>();
                    listOrg.add(memberships.getData().get(0).getOrg_id().get(0));

                    List<Categories> listCategories = new ArrayList<>();
                    listCategories.add(categories);

                    List<Brands> listBrands = new ArrayList<>();
                    listBrands.add(brands);

                    String name = editName.getText().toString();
                    String serialNum = editSerialNo.getText().toString();
                    String QRCode = NewProduct.this.QRCode.getText().toString();
                    String note = editNote.getText().toString();

                    Products products;
                    if(isEdit && oldProduct != null){
                        products = oldProduct;
                    } else {
                        products = new Products();
                    }
                    products.setName(name);
                    products.setOrg_id(listOrg);
                    products.setCategory_id(listCategories); //stuck here. category_id data invalide type (WTF?) have no idea
                    products.setBrand_id(listBrands);
                    products.setSerial_no(serialNum);
                    products.setCode(QRCode);
                    products.setNote(note);
                    products.setAdd_date(pickedAddDate);
                    products.setRelease_date(pickedReleaseDate);


                    Backendless.Persistence.of(Products.class).save(products, new AsyncCallback<Products>() {
                        @Override
                        public void handleResponse(Products googleMapping) {

                            showSnackBar("Продукт создан");
                            Log.d(TAG, "handleResponse: saving product");

                            startActivity(new Intent(NewProduct.this, MainActivity.class));
                            finish();
                            //
                        }

                        @Override
                        public void handleFault(BackendlessFault e) {

                            showSnackBar(e.getMessage() + " : " + e.getDetail());
                            showHideProgressBar(false);
                            buttonSave.setEnabled(true);
                            Log.d(TAG, "handleFault: error " + e.getDetail() + " : " + e.getMessage());

                        }
                    });


                }

                @Override
                public void handleFault(BackendlessFault e) {
                    showSnackBar(e.getMessage() + " : " + e.getDetail());
                    showHideProgressBar(false);
                    buttonSave.setEnabled(true);
                    Log.d(TAG, "handleFault:getting " + e.getDetail());
                    Log.d(TAG, "handleFault:getting " + e.getMessage());
                }
            });
        }
    }

    private void takeOutFromBackendlessCategories() {
        BackendlessDataQuery dataQuery = new BackendlessDataQuery();
        QueryOptions queryOptions = new QueryOptions();
        queryOptions.setPageSize(PAGE_SIZE);
        dataQuery.setQueryOptions( queryOptions );
        Backendless.Data.of(Categories.class).find(dataQuery, new AsyncCallback<BackendlessCollection<Categories>>() {
            @Override
            public void handleResponse(BackendlessCollection<Categories> cats) {

                Log.d(TAG, "handleResponse: " + cats.getData().size());

                //Iteration loaded data
                Iterator<Categories> iterator = cats.getCurrentPage().iterator();
                while (iterator.hasNext()) {
                    Categories cat = iterator.next();
                    Log.d("Categories ", cat.getName() + " and id is " + cat.getObjectId());
                    BLCategoriesList.add(cat);
                    setCat(cat.getObjectId(), cat.getName()); //setCat: saving to ArrayList dataC and dataCid
                }

                refreshCat(); //to refresh spinner after loaded data
                takeOutFromBackendlessBrands();
                // all Contact instances have been found
            }

            @Override
            public void handleFault(BackendlessFault e) {
                showSnackBar(e.getMessage() +" : "  + e.getDetail());
                // an error has occurred, the error code can be retrieved with fault.getCode()
                Log.d("error_reading", e.getCode());
                showHideProgressBar(false);
            }
        });
    }

    private void takeOutFromBackendlessBrands() {
        BackendlessDataQuery dataQuery = new BackendlessDataQuery();
        QueryOptions queryOptions = new QueryOptions();
        queryOptions.setPageSize(PAGE_SIZE);
        dataQuery.setQueryOptions( queryOptions );

        showHideProgressBar(true);

        Backendless.Persistence.of(Brands.class).find(dataQuery,new AsyncCallback<BackendlessCollection<Brands>>() {
            @Override
            public void handleResponse(BackendlessCollection<Brands> the_brands) {

                //just to see what have loaded
                System.out.println("Loaded " + the_brands.getCurrentPage().size() + " Brands");
                System.out.println("Total Brands in the Backendless storage - " + the_brands.getTotalObjects());

                //iteration loaded data
                Iterator<Brands> iterator = the_brands.getCurrentPage().iterator();
                while (iterator.hasNext()) {
                    Brands brand = iterator.next();
                    BLBrandList.add(brand);
                    Log.d("Categories ", brand.getName() + "and id" + brand.getObjectId()); // just to check everything is ok

                    //dataBraId [i] = brand.getObjectId();
                    //dataBra[i] = brand.getName();
                    setBrand(brand.getObjectId(), brand.getName());//setCat: saving to ArrayList dataB and dataBid

                }
                refreshBrand();//to refresh spinner after loaded data
                showHideProgressBar(false);

            }

            @Override
            public void handleFault(BackendlessFault e) {
                // an error has occurred, the error code can be retrieved with fault.getCode()
                Log.d("error_reading", e.getCode());
                showSnackBar(e.getMessage() +" : "  + e.getDetail());
                showHideProgressBar(false);
            }
        });
    }

    private void clearEditTexts() {
        //Log.d("buttonClear"," is pressed");
        editName.setText("");
        spinnerCategory.setSelection(0);
        spinnerBrand.setSelection(0);
        QRCode.setText("");
        editSerialNo.setText("");
        editAdd.setText("");
        editRelease.setText("");
        editNote.setText("");
    }


    private void setCat(String id, String name) {
        dataC.add(name);
        dataCid.add(id);
    }

    private void setBrand(String id, String name) {
        dataB.add(name);
        dataBid.add(id);
    }

    private void refreshCat() {
        adapterCat.notifyDataSetChanged();
        spinnerCategory.setSelection(0);
    }

    private void refreshBrand() {
        adapterBra.notifyDataSetChanged();
        spinnerBrand.setSelection(0);
    }
}
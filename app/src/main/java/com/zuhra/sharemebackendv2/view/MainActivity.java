package com.zuhra.sharemebackendv2.view;

/**
 * Created by zuhra on 13.07.2016.
 */

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.backendless.Backendless;
import com.backendless.BackendlessCollection;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.persistence.BackendlessDataQuery;
import com.backendless.persistence.QueryOptions;
import com.zuhra.sharemebackendv2.R;
import com.zuhra.sharemebackendv2.entyties.Products;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private static final String TAG = MainActivity.class.getName();
    private List<Products> productsList;
    private ListView list;
    private NavigationView navigationView;
    private String userEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        productsList = new ArrayList<>();
        SharedPreferences preferences  = getSharedPreferences("Preference", MODE_PRIVATE);
        userEmail = preferences.getString("userEmail", "example@email.com");
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);


        TextView view  = (TextView) navigationView.getHeaderView(0).findViewById(R.id.nav_bar_text);
        navigationView.setNavigationItemSelectedListener(this);
        view.setText(userEmail);
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    private void init() {
        BackendlessDataQuery dataQuery = new BackendlessDataQuery();
        QueryOptions queryOptions = new QueryOptions();
        queryOptions.setPageSize(NewProduct.PAGE_SIZE);
        dataQuery.setQueryOptions(queryOptions);

        Backendless.Data.of(Products.class).find(dataQuery, new AsyncCallback<BackendlessCollection<Products>>() {
            @Override
            public void handleResponse(BackendlessCollection<Products> products) {
                //Если загрузка массива продуктов успешна то передаем ее в наш лист productList
                productsList = products.getData();

                list = (ListView) findViewById(R.id.listView);

                final MyAdapter adapter = new MyAdapter(MainActivity.this, productsList);
                list.setAdapter(adapter);

                list.setClickable(true);

                list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Log.d(TAG, "onItemClick: ");
                        Intent intent = new Intent(MainActivity.this, ProductView.class);
                        intent.putExtra("ProductId", productsList.get(position).getObjectId());
                        startActivity(intent);
                    }
                });

                list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                        new AlertDialog.Builder(MainActivity.this)
                                .setTitle("Удалить")
                                .setMessage("Удалить " + productsList.get(position).getName())
                                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        // continue with delete
                                        final Products removingProduct = productsList.get(position);


                                        Backendless.Persistence.of(Products.class).remove(removingProduct, new AsyncCallback<Long>() {
                                            @Override
                                            public void handleResponse(Long aLong) {
                                                productsList.remove(removingProduct);
                                                adapter.notifyDataSetChanged();
                                            }

                                            @Override
                                            public void handleFault(BackendlessFault e) {
                                            }
                                        });
                                    }
                                })
                                .setNegativeButton(android.R.string.no, null)
                                .show();
                        return true;
                    }
                });

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
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem addProduct = menu.findItem(R.id.edit_product);

        addProduct.setIcon(R.drawable.ic_add_black_24dp);
        progressbar = menu.findItem(R.id.progress_bar_menu_item);
        progressbar.setVisible(true);
        init();
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.edit_product)
            startActivity(new Intent(MainActivity.this, NewProduct.class));

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.new_product_menu, menu);
        return true;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.list_apply_users) {
            Intent intent = new Intent(MainActivity.this, ApplyActivity.class);

            startActivity(intent);
        } else if (id == R.id.nav_logout) {

            new Thread(new Runnable() {
                @Override
                public void run() {
                    Backendless.UserService.logout();
                    startActivity(new Intent(MainActivity.this, LoginActivity.class));
                    finish();
                }
            }).start();
        } else if (id == R.id.generate_qr_code) {
            startActivity(new Intent(MainActivity.this, GenerateQRCode.class));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    class MyAdapter extends BaseAdapter {

        private Context context;
        private List<Products> data;


        MyAdapter(Context context, List<Products> data) {
            this.data = data;
            this.context = context;
        }

        @Override
        public int getCount() {   // кол во элементов
            return data.size();
        }

        @Override
        public String getItem(int position) {   // возвращает на какой элемент из массива мы нажали
            return String.valueOf(data.get(position));
        }

        @Override
        public long getItemId(int position) {  // возврат индекса, идентификатора
            return position;
        }


        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {   // заполняет листвью

            View con = convertView;
            if (con == null) {
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                con = inflater.inflate(R.layout.myitem, parent, false);
            }


            TextView thingname = (TextView) con.findViewById(R.id.thingname);

            TextView elemPosition = (TextView) con.findViewById(R.id.position_num);

            elemPosition.setText((position + 1) + "");

            TextView thingbrand = (TextView) con.findViewById(R.id.thingbrand);
            //вывод названия текущего предмета
            thingname.setText(data.get(position).getName().toString());
            //можно добавить дополнительную информацию такую как бренд
            if (data.get(position).getBrand_id().size() > 0) {
                thingbrand.setText(data.get(position).getCategory_id().get(0).getName());
            }


            return con;
        }


    }
}

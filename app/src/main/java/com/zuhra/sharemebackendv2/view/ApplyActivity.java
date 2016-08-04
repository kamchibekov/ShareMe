package com.zuhra.sharemebackendv2.view;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.backendless.Backendless;
import com.backendless.BackendlessCollection;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.persistence.BackendlessDataQuery;
import com.zuhra.sharemebackendv2.R;
import com.zuhra.sharemebackendv2.entyties.MembershipStatus;
import com.zuhra.sharemebackendv2.entyties.Memberships;

import java.util.ArrayList;
import java.util.List;

public class ApplyActivity extends com.zuhra.sharemebackendv2.view.BaseActivity implements com.zuhra.sharemebackendv2.view.ApplyRecyclerViewAdapter.AdapterListener {

    private static final String TAG = ApplyActivity.class.getName();
    private List<Memberships> listMemberships;
    private com.zuhra.sharemebackendv2.view.ApplyRecyclerViewAdapter adapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apply);
        init();
    }

    private void init() {

        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        Backendless.initApp(this, APP_ID, APP_SECRET_KEY, APP_VERSION);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_apply);
        assert recyclerView != null;
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new ApplyRecyclerViewAdapter(this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }

        return super.onOptionsItemSelected(item);
    }

    private void fetchMemberships() {
        BackendlessDataQuery dataQuery = new BackendlessDataQuery();
        dataQuery.setWhereClause("status_id.name = 'application'");

        Backendless.Data.of(Memberships.class).find(dataQuery, new AsyncCallback<BackendlessCollection<Memberships>>() {
            @Override
            public void handleResponse(BackendlessCollection<Memberships> list) {

                listMemberships = list.getCurrentPage();
                for (int i = 0; i < listMemberships.size(); i++) {
                    adapter.addItem(listMemberships.get(i).getUser_id().get(0));
                }
                showHideProgressBar(false);
                if(list.getCurrentPage().size() < 1){
                    TextView placeHolder = (TextView) findViewById(R.id.placeholder_text);
                    assert placeHolder != null;
                    placeHolder.setVisibility(View.VISIBLE);
                } else
                    recyclerView.setBackgroundColor(Color.BLUE);
            }

            @Override
            public void handleFault(BackendlessFault e) {
                showMessage(e.getMessage() + " : " + e.getDetail());
                showHideProgressBar(false);
            }
        });
    }


    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        progressbar = menu.findItem(R.id.progress_bar_menu_item);
        progressbar.setVisible(true);
        fetchMemberships();
        return true;
    }

    @Override
    public void showMessage(String message) {
        Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void changeStatus(String type, BackendlessUser backendlessUser) {
        Log.d(TAG, "changeStatus: ");
        if (listMemberships.size() > 0) {
            Memberships memberships = null;
            for (int i = 0; i < listMemberships.size(); i++) {
                if (listMemberships.get(i).getUser_id().get(0).getObjectId()
                        .equals(backendlessUser.getObjectId())) {
                    memberships = listMemberships.get(i);
                }
            }

            if (type.equals("no") && memberships != null) {
                updateMemberships("0DB953C3-B8FE-9A21-FFF4-BC42CDAEEC00", memberships);
            }

            if (type.equals("yes") && memberships != null) {
                updateMemberships("CED279E8-91F2-869B-FF16-BE2D210F9A00", memberships);
            }
        }
    }

    private void updateMemberships(final String statusId, final Memberships oldMemberships) {

        Log.d(TAG, "updateMemberships: ");
        Backendless.Persistence.of(MembershipStatus.class).findById(statusId, new AsyncCallback<MembershipStatus>() {
            @Override
            public void handleResponse(MembershipStatus membershipStatus) {
                List<MembershipStatus> listStates = new ArrayList<>();
                listStates.add(membershipStatus);

                oldMemberships.setStatus_id(listStates);
                Backendless.Persistence.of(Memberships.class).save(oldMemberships, new AsyncCallback<Memberships>() {
                    @Override
                    public void handleResponse(Memberships memberships) {
                        showMessage("Успешно активирован");
                    }

                    @Override
                    public void handleFault(BackendlessFault e) {
                        showMessage(e.getMessage() + " : " + e.getDetail());
                    }
                });
            }

            @Override
            public void handleFault(BackendlessFault e) {
                showMessage(e.getMessage() + " : " + e.getDetail());
            }
        });
    }
}




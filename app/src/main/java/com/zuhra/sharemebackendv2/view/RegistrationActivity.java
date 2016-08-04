package com.zuhra.sharemebackendv2.view;

/**
 * Created by zuhra on 13.07.2016.
 */

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.BackendlessCollection;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.zuhra.sharemebackendv2.R;
import com.zuhra.sharemebackendv2.entyties.MembershipStatus;
import com.zuhra.sharemebackendv2.entyties.Memberships;
import com.zuhra.sharemebackendv2.entyties.Orgs;

import java.util.ArrayList;
import java.util.List;

public class RegistrationActivity extends BaseActivity {
    private static final String TAG = RegistrationActivity.class.getName() + "_1";
    private EditText regEmail, regPassword, regPhone, regName;
    private Spinner regOrganisation;
    private ArrayList<String> listOrganisation;
    private ArrayList<Orgs> listOrgs = new ArrayList<>();
    private ArrayList<String> listId;
    private int positionOrg;

    private String idUser, idOrg;
    private BackendlessUser user;
    private Orgs orgs;
    private BackendlessCollection<Orgs> organisations;
    private TextView debugView;
    private List<Orgs> activeOrgs = new ArrayList<>();
    private Orgs selectedOrg;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        regEmail = (EditText) findViewById(R.id.reg_EmailEdit);
        regName = (EditText) findViewById(R.id.reg_NameEdit);
        regPassword = (EditText) findViewById(R.id.reg_PasswordEdit);
        regPhone = (EditText) findViewById(R.id.reg_PhoneEdit);
        positionOrg = 0;
        listOrganisation = new ArrayList<>();
        listId = new ArrayList<>();
//        listOrganisation.add("");
//        listId.add("");
        debugView = (TextView) findViewById(R.id.debugView);

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, listOrganisation);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        regOrganisation = (Spinner) findViewById(R.id.reg_org);
        regOrganisation.setAdapter(adapter);
        // заголовок
        regOrganisation.setPrompt("Organisation");
        // выделяем элемент
        regOrganisation.setSelection(0);
        // устанавливаем обработчик нажатия


        regOrganisation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                positionOrg = position;
                selectedOrg = activeOrgs.get(position);

            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });
        Button regButton = (Button) findViewById(R.id.reg_ButtonRegistration);

        assert regButton != null;
        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                positionOrg = regOrganisation.getSelectedItemPosition();

                if (regName != null && regEmail != null && regPassword != null && regPhone != null
                        && !regName.getText().toString().isEmpty()
                        && !regEmail.getText().toString().isEmpty()
                        && !regPassword.getText().toString().isEmpty()
                        && !regPhone.getText().toString().isEmpty()
                        ) {
                    showHideProgressBar(true);
                    String strName, strPhone, strEmail, strPassword;
                    strName = regName.getText().toString();
                    strEmail = regEmail.getText().toString();
                    strPassword = regPassword.getText().toString();
                    strPhone = regPhone.getText().toString();
                    BackendlessUser userBack = new BackendlessUser();
                    userBack.setEmail(strEmail);
                    userBack.setPassword(strPassword);
                    userBack.setProperty("name", strName);
                    userBack.setProperty("phone", strPhone);
                    Backendless.UserService.register(userBack, new AsyncCallback<BackendlessUser>() {
                        public void handleResponse(BackendlessUser registeredUser) {
                            idUser = registeredUser.getUserId();
                            showHideProgressBar(false);
                            showSnackBar("Юзер создан");
                            saveMemberShips(registeredUser, selectedOrg); //TO DO make index compatible with ArrayList

                        }

                        public void handleFault(BackendlessFault fault) {
//                            showHideProgressBar(false);
                            showSnackBar(fault.getMessage() + " : " + fault.getDetail());
                        }
                    });


                } else {
                    showSnackBar("Заполните все поля");
                }
            }
        });


    }

    private void getOrgList() {

        showHideProgressBar(true);
        Backendless.Persistence.of(Orgs.class).find(new AsyncCallback<BackendlessCollection<Orgs>>() {
            @Override
            public void handleResponse(BackendlessCollection<Orgs> organisation) {
                showHideProgressBar(false);
                organisations = organisation;

                int size = organisation.getCurrentPage().size();

                for (int i = 0; i < size; i++) {
                    listOrgs.add(organisation.getCurrentPage().get(i));
                }
                showHideProgressBar(true);
                Backendless.Data.of(Memberships.class).find(new AsyncCallback<BackendlessCollection<Memberships>>() {
                    @Override
                    public void handleResponse(BackendlessCollection<Memberships> memberships) {
                        for (int i = 0; i < listOrgs.size(); i++) {
                            boolean isFind = false;

                            for (int k = 0; k < memberships.getData().size(); k++) {
                                List<Orgs> list = memberships.getData().get(k).getOrg_id();
                                if (list.size() > 0
                                        && listOrgs.get(i).getObjectId().equals(list.get(0).getObjectId())) {
                                    isFind = true;
                                    break;
                                }
                            }

                            if (!isFind) {
                                activeOrgs.add(listOrgs.get(i));
                                String org = listOrgs.get(i).getName();
                                String id = listOrgs.get(i).getObjectId();
                                listOrganisation.add(org);
                                listId.add(id);
                            }
                        }
                        showHideProgressBar(false);
                        adapter.notifyDataSetChanged();


                    }

                    @Override
                    public void handleFault(BackendlessFault e) {

                        showSnackBar(e.getMessage() + " : " + e.getDetail());

                        showHideProgressBar(false);
                    }
                });


            }

            @Override
            public void handleFault(BackendlessFault e) {
                showHideProgressBar(false);

                showSnackBar(e.getMessage() + " : " + e.getDetail());
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        getOrgList();
    }

    private void saveMemberShips(final BackendlessUser user, final Orgs org) {

        Backendless.Persistence.of(MembershipStatus.class)
                .findById("CED279E8-91F2-869B-FF16-BE2D210F9A00", new AsyncCallback<MembershipStatus>() {
                    @Override
                    public void handleResponse(MembershipStatus membershipStatus) {
                        Memberships userMember = new Memberships();

                        userMember.setIs_admin(1);
                        userMember.setIs_free(1);


                        List<MembershipStatus> listStatus = new ArrayList<>();
                        listStatus.add(membershipStatus);

                        ArrayList<Orgs> orgsList = new ArrayList<>();
                        orgsList.add(org);

                        ArrayList<BackendlessUser> backendlessUsers = new ArrayList<>();
                        backendlessUsers.add(user);

                        userMember.setOrg_id(orgsList);
                        userMember.setUser_id(backendlessUsers);
                        userMember.setStatus_id(listStatus);
                        //Memberships savedPhoneBook = Backendless.Persistence.save( userMember );
                        //Memberships savedContact = Backendless.Persistence.save( userMember );

                        Backendless.Persistence.of(Memberships.class).save(userMember, new AsyncCallback<Memberships>() {
                            public void handleResponse(Memberships response) {
                                showHideProgressBar(false);
                                showSnackBar("Юзер активирован");
                                Toast.makeText(RegistrationActivity.this, "+++", Toast.LENGTH_SHORT).show();
                                startActivity(
                                        new Intent(RegistrationActivity.this, LoginActivity.class)
                                                .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                                                            |Intent.FLAG_ACTIVITY_CLEAR_TASK));
                            }

                            public void handleFault(BackendlessFault fault) {
//                                showHideProgressBar(false);
                                showSnackBar(fault.getMessage() + " : " + fault.getDetail());

                            }
                        });
                    }

                    @Override
                    public void handleFault(BackendlessFault e) {
//                        showHideProgressBar(false);
                        showSnackBar(e.getMessage() + " : " + e.getDetail());
                    }
                });


    }
}
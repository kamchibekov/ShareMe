package com.zuhra.sharemebackendv2.view;

/**
 * Created by zuhra on 13.07.2016.
 */

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.persistence.local.UserTokenStorageFactory;
import com.zuhra.sharemebackendv2.R;

public class LoginActivity extends BaseActivity {

    private boolean saveUser;
    private EditText emailEdit, passwordEdit;
    private CheckBox saveUserCheck;
    private final static String TAG = LoginActivity.class.getName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        setTitle(getString(R.string.login_activity_label));
        saveUser = false;
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Backendless.initApp(this, APP_ID, APP_SECRET_KEY, APP_VERSION);
        emailEdit = (EditText) findViewById(R.id.log_EmailEdit);
        passwordEdit = (EditText) findViewById(R.id.log_PasswordEdit);
        saveUserCheck = (CheckBox) findViewById(R.id.log_SaveUser);
        saveUserCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveUser = saveUserCheck.isChecked();
            }
        });

        String userToken = UserTokenStorageFactory.instance().getStorage().get();

        if (userToken != null && !userToken.equals("")) {
            Intent mainIntent = new Intent(LoginActivity.this, com.zuhra.sharemebackendv2.view.MainActivity.class);
            startActivity(mainIntent);
            finish();
        }

        Button loginButton = (Button) findViewById(R.id.log_ButtonLogin);

        assert loginButton != null;
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strEmail, strPassword;
                showHideProgressBar(true);
                if(emailEdit != null && passwordEdit != null
                        && !emailEdit.getText().toString().isEmpty()
                        && !passwordEdit.getText().toString().isEmpty()){
                    strEmail = emailEdit.getText().toString();
                    strPassword = passwordEdit.getText().toString();

                    Backendless.UserService.login(strEmail, strPassword, new AsyncCallback<BackendlessUser>() {
                        public void handleResponse(BackendlessUser user) {

                            SharedPreferences sharedPreferences=getSharedPreferences("Preference",MODE_PRIVATE);
                            SharedPreferences.Editor editor=sharedPreferences.edit();
                            editor.putString("UserId",user.getObjectId());
                            editor.putString("userEmail", user.getEmail());
                            editor.commit();
                            Intent mainIntent = new Intent(LoginActivity.this, com.zuhra.sharemebackendv2.view.MainActivity.class);
                            mainIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                                                |Intent.FLAG_ACTIVITY_CLEAR_TASK);

                            startActivity(mainIntent);
                            finish();
                        }

                        public void handleFault(BackendlessFault fault) {
                            showSnackBar("Вы ввели неверный логин или пароль!");
                            showHideProgressBar(false);
                           }
                    }, saveUser);
                } else {
                    showHideProgressBar(false);
                    showSnackBar("Поля пустые");
                }



            }
        });



//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);//Button registration
//
//        assert fab != null;
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });
    }


    public void onClickRegist(View view) {
        startActivity(new Intent(LoginActivity.this, com.zuhra.sharemebackendv2.view.RegistrationActivity.class));
    }
}

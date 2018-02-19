package com.example.wearecoders.myfirstproject;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.wearecoders.myfirstproject.Utils.AppController;
import com.example.wearecoders.myfirstproject.Utils.Config;
import com.example.wearecoders.myfirstproject.Utils.Constants;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.LoggingBehavior;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.firebase.FirebaseApp;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.gson.Gson;

import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by We Are Coders on 2/7/2018.
 */

public class RegisterActivity extends AppCompatActivity {
    private final String TAG = RegisterActivity.class.getSimpleName();
    @BindView(R.id.firstname)
    EditText firstname;
    @BindView(R.id.lastname)
    EditText lastname;
    @BindView(R.id.dob)
    EditText dob;
    @BindView(R.id.interested)
    EditText interested;
    @BindView(R.id.country)
    EditText country;
    @BindView(R.id.zipcode)
    EditText postcode;
    @BindView(R.id.fb_login)
    ImageView fbLogin;
    @BindView(R.id.login_button)
    LoginButton loginButton;
    private CallbackManager callbackManager;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        callbackManager = CallbackManager.Factory.create();
        FacebookSdk.addLoggingBehavior(LoggingBehavior.REQUESTS);
        facebookLogin();
    }
    private String getfcm() {
        FirebaseApp.initializeApp(this);
        return FirebaseInstanceId.getInstance().getToken();//Token generation
    }

    @OnClick(R.id.fb_login)
    public void next(View view)
    {
        String token = getfcm();
        switch (view.getId()) {
            case R.id.fb_login:
                if (token != null)
                    loginButton.performClick();
                else Toast.makeText(this, R.string.appnotintial, Toast.LENGTH_SHORT).show();
                break;
    }}

    private void facebookLogin() {
        loginButton.setReadPermissions(Arrays.asList("public_profile,email"));
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                login_facebook(loginResult.getAccessToken().getToken(), null, null, null);

            }

            @Override
            public void onCancel() {
                Log.e("RegisterActivity", "cancel");
            }

            @Override
            public void onError(FacebookException error) {
                Log.e("RegisterActivity", error.getMessage());

            }
        });
    }

    public static String getdeviceid(Context context) {
        return Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);

    }
    private void validlogin(JSON_Result json_result) {
        Log.e(TAG, "validlogin" + new Gson().toJson(json_result));
        if (json_result.isStatus()) {
            if (progressDialog != null && progressDialog.isShowing()) {
                progressDialog.dismiss();
            }
            if (json_result.getData() != null) {
                Gson gson = new Gson();
                Log.e(TAG, "login" + gson.toJson(json_result.getData()));
                AppController.getInstance().getPref().edit().putString(Constants.LOGGED_IN, gson.toJson(json_result.getData())).commit();
                Intent intent = new Intent(RegisterActivity.this, SecondActivity.class);
                startActivity(intent);
                finish();
            }

        } else {
            progressDialog.dismiss();
        }
        }



    private void login_facebook(String access_token, String emial, String name, String pic) {
        progressDialog = new ProgressDialog(RegisterActivity.this);
        progressDialog.setMessage("Please wait...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        Config config = AppController.getInstance().getConfig();
        Call<JSON_Result> call = null;
        if (access_token != null) {
            call = config.login_facebook(access_token, getfcm(), RegisterActivity.getdeviceid(getApplicationContext()), Constants.DEVICE_TYPE, Constants.FROM_FACEBOOK);
        } else {
               ///nothing to write.
        }
        call.enqueue(new Callback<JSON_Result>() {
            @Override
            public void onResponse(Call<JSON_Result> call, Response<JSON_Result> response) {
              /*  {"status":true,"message":"Your Password Email Has Been Sent To Your Registered Email Please Check"}*/
                Log.e(TAG, "response:");
                try {
                    Log.e(TAG, response.body().toString());
                    validlogin(response.body());

                } catch (Exception e) {
                    Log.e(TAG, "error");
                    if (progressDialog != null && progressDialog.isShowing())
                        progressDialog.dismiss();
                    Toast.makeText(RegisterActivity.this, "Error in Json server " + e.getMessage(), Toast.LENGTH_SHORT).show();

                }


            }

            @Override
            public void onFailure(Call<JSON_Result> call, Throwable t) {
                Log.e(TAG, "error :" + t.getMessage());


            }
        });

    }
}

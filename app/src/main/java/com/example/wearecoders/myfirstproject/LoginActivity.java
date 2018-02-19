package com.example.wearecoders.myfirstproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.facebook.CallbackManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by We Are Coders on 2/7/2018.
 */

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.email)
    EditText email;
    @BindView(R.id.password)
    EditText password;
    private CallbackManager callbackManager;
    private AlertDialog dialog;
    private final String TAG = LoginActivity.class.getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

    }

    @OnClick({R.id.login_btn, R.id.forgotpassword})
    public void next(View view) {
        switch (view.getId()) {
            case R.id.forgotpassword:
                Intent in = new Intent(this,ChangePassword.class);
                startActivity(in);
                break;
            case R.id.login_btn:
                Intent i = new Intent(this,SecondActivity.class);
                startActivity(i);
                break;

        }

    }
}
package com.example.wearecoders.myfirstproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by We Are Coders on 2/13/2018.
 */
public class ChangePassword extends AppCompatActivity {
    @BindView(R.id.enteremail)
    EditText enteremail;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.change_password);
        ButterKnife.bind(this);

    }

    @OnClick(R.id.email_submit_button)
    public void Change() {
        Intent intent = new Intent(ChangePassword.this, new_password.class);
        startActivity(intent);
    }
}

package com.example.wearecoders.myfirstproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.login)
    ImageView loginbtn;
    @BindView(R.id.sign)
    ImageView signbtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.login)
    public void Login(){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.sign)
    public void Register(){
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }


}

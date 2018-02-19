package com.example.wearecoders.myfirstproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by We Are Coders on 2/13/2018.
 */

public class new_password extends AppCompatActivity {
@BindView(R.id.submit_password_button)
ImageView password_submit;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_password);
        ButterKnife.bind(this);
    }
    @OnClick(R.id.submit_password_button)
    public void new_password(){
        Toast.makeText(this,"Password changed",Toast.LENGTH_SHORT).show();
    }

}

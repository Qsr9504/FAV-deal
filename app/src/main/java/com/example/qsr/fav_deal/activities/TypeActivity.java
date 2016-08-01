package com.example.qsr.fav_deal.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.qsr.fav_deal.R;

public class TypeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_type);
    }
}

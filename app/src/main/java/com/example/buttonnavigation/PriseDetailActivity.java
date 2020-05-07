package com.example.buttonnavigation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class PriseDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prise_detail);

        String id_p = getIntent().getStringExtra("id_prog");
        String id_prise = getIntent().getStringExtra("id_prise");

        System.out.println(id_p+"************"+id_prise);
    }
}

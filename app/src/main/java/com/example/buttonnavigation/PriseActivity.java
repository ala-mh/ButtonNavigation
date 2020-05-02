package com.example.buttonnavigation;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.buttonnavigation.adapter.PriseListAdapter;
import com.example.buttonnavigation.dao.priseDAO;
import com.example.buttonnavigation.model.Prise;

import java.util.ArrayList;
import java.util.List;

public class PriseActivity extends AppCompatActivity {
    //LinearLayout ProgrammeListView;
    ListView PriseListView;
    List<Prise> listprise=new ArrayList<Prise>();
    priseDAO prisedao;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prise);
        String id_p = getIntent().getStringExtra("id_p");

        PriseListView = (ListView) findViewById(R.id.PriseListView);

        prisedao= new priseDAO(getApplicationContext());

        listprise=prisedao.getAllPrise(id_p);

        PriseListAdapter adapter = new PriseListAdapter(this, R.layout.prise_data, listprise);
        PriseListView.setAdapter(adapter);
    }
}

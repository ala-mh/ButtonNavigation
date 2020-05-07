package com.example.buttonnavigation;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.buttonnavigation.adapter.PriseListAdapter;
import com.example.buttonnavigation.dao.priseDAO;
import com.example.buttonnavigation.model.Prise;
import com.example.buttonnavigation.model.Programme;

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

        PriseListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            Intent intent = null;
            @Override
            public void onItemClick(AdapterView<?> parent,View view,int position,long id) {
                Prise prise= listprise.get(position);
                intent = new Intent(getApplicationContext(), PriseDetailActivity.class);

                intent.putExtra("id_prog", Integer.toString(prise.getNum_p()));
                intent.putExtra("id_prise", Integer.toString(prise.getNum_prise()));
                startActivity(intent);
                //System.out.println(programme.getNum_p());
            }
        });


    }
}

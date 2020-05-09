package com.example.buttonnavigation.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.buttonnavigation.PriseActivity;
import com.example.buttonnavigation.R;
import com.example.buttonnavigation.adapter.ProgrammeListAdapter;
import com.example.buttonnavigation.addProg;
import com.example.buttonnavigation.dao.ProgrammeDAO;
import com.example.buttonnavigation.model.Programme;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    ListView ProgrammeListView;
    List<Programme> listprog=new ArrayList<Programme>();
    ProgrammeDAO progdao;


    public HomeFragment(){
    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ProgrammeListView = (ListView)  view.findViewById(R.id.ProgrammeListView);
        progdao= new ProgrammeDAO(getActivity().getApplicationContext());
        listprog = progdao.getAllProg();
        ProgrammeListAdapter adapter = new ProgrammeListAdapter(getActivity(), R.layout.row, listprog);
        ProgrammeListView.setAdapter(adapter);

        ProgrammeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            Intent intent = null;
            @Override
            public void onItemClick(AdapterView<?> parent,View view,int position,long id) {
                Programme programme= listprog.get(position);
                 intent = new Intent(getActivity().getApplicationContext(), PriseActivity.class);

                 //intent.putExtra("id_p", Integer.toString(programme.getNum_p()));
                 intent.putExtra("id_p", programme.getNum_p());
                 startActivity(intent);
                //System.out.println(programme.getNum_p());
            }
        });

        FloatingActionButton fab = view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                        Intent intent1 = null;
                        intent1 = new Intent(getActivity().getApplicationContext(), addProg.class);
                        startActivity(intent1);
            }
        });

        return view;



    }

}

package com.example.buttonnavigation.adapter;

import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import com.example.buttonnavigation.R;
import com.example.buttonnavigation.model.Programme;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

public class ProgrammeListAdapter  extends ArrayAdapter<Programme> {

    private static final String TAG = "PersonListAdapter";

     private  Context context;
     private int lastPosition = -1;
     private int resource;
     private Programme programme;

     private float progress;
     private ProgressBar progressBar;
     private Handler handler = new Handler();
     private String date_fin;
     private String date_actuelle;
     private DateTimeFormatter dtf;
     private LocalDateTime now;
     private SimpleDateFormat formatter;
     private Date d_debut ;


    private static class ViewItem {
        TextView maladieView;
        TextView date_debutView;
        TextView dureeView;
    }

    public ProgrammeListAdapter(Context context, int resource, List<Programme> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
    }



    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        //get the persons information
        String maladie = getItem(position).getMaladie();
        String date_debut = getItem(position).getDate_debut();
        String duree = getItem(position).getDuree();

        //Create the person object with the information
         programme = new Programme(maladie,date_debut,duree);

        //create the view result for showing the animation
        final View result;

        //ViewHolder object
        ViewItem holder;

            if(convertView == null){
                    LayoutInflater inflater = (LayoutInflater) LayoutInflater.from(this.context);
                    convertView = inflater.inflate(this.resource, parent, false);

                    holder= new ViewItem();
                    holder.maladieView = (TextView) convertView.findViewById(R.id.MaladieView);
                    holder.date_debutView = (TextView) convertView.findViewById(R.id.DataView);
                    holder.dureeView = (TextView) convertView.findViewById(R.id.DureeView);

                    result = convertView;

                    convertView.setTag(holder);
            }
            else{
                    holder = (ViewItem) convertView.getTag();
                    result = convertView;
            }


        Animation animation = AnimationUtils.loadAnimation(this.context,
                (position > lastPosition) ? R.anim.load_down_anim : R.anim.load_up_anim);
        result.startAnimation(animation);
        lastPosition = position;


        holder.maladieView.setText(programme.getMaladie());
        holder.date_debutView.setText(programme.getDate_debut());
        holder.dureeView.setText(programme.getDuree() + " jours");


        dtf = DateTimeFormatter.ofPattern("dd");
        now = LocalDateTime.now();

        formatter = new SimpleDateFormat("dd");

        try {
            d_debut = formatter.parse(programme.getDate_debut());
            date_fin = Integer.toString(Integer.parseInt(formatter.format(d_debut)) + Integer.parseInt(programme.getDuree()));
            date_actuelle = Integer.toString(Integer.parseInt(dtf.format(now)) - Integer.parseInt(formatter.format(d_debut)));
            progress = Integer.parseInt(date_actuelle) * 100 / Integer.parseInt(date_fin) ;

            /*
            System.out.println("d_debut "+ formatter.format(d_debut));
            System.out.println("date_fin "+ date_fin);
            System.out.println("date_actuelle "+ date_actuelle);
            System.out.println("progress "+ progress);*/

            dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            now = LocalDateTime.now();

            /*
            System.out.println("d_debut "+ programme.getDate_debut());
            System.out.println("now "+ dtf.format(now));*/

            progressBar = (ProgressBar) result.findViewById(R.id.pBar);
            new Thread(new Runnable() {
                @Override
                public void run() {
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                //if (dtf.format(now).compareTo(programme.getDate_debut()) == -1) progressBar.setProgress(0);
                                progressBar.setProgress((int) progress);
                            }
                        });
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }


                }
            }).start();

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return convertView;
    }


}

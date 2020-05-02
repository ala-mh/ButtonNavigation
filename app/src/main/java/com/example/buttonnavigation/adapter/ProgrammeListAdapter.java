package com.example.buttonnavigation.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.buttonnavigation.R;
import com.example.buttonnavigation.model.Programme;

import java.util.List;

public class ProgrammeListAdapter  extends ArrayAdapter<Programme> {

    private static final String TAG = "PersonListAdapter";

     private  Context context;
     private int lastPosition = -1;
     private int resource;

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



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        //get the persons information
        String maladie = getItem(position).getMaladie();
        String date_debut = getItem(position).getDate_debut();
        String duree = getItem(position).getDuree();

        //Create the person object with the information
        Programme programme = new Programme(maladie,date_debut,duree);

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
        holder.date_debutView.setText("Date Début "+programme.getDate_debut());
        holder.dureeView.setText("Durée Maladie "+programme.getDuree());


        return convertView;
    }


}

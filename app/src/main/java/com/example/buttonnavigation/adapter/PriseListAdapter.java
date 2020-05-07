package com.example.buttonnavigation.adapter;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;

import com.example.buttonnavigation.R;
import com.example.buttonnavigation.model.Prise;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class PriseListAdapter  extends ArrayAdapter<Prise> {

    private Context context;
    private int lastPosition = -1;
    private int resource;

    private static class ViewItem {
        TextView medicamentView;
        TextView dateView;
        TextView heureView;
        TextView qtView;
    }

    public PriseListAdapter(Context context, int resource, List<Prise> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
    }



    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View getView(int position,View convertView,ViewGroup parent) {

        //get the persons information
        int num_p = getItem(position).getNum_p();
        int num_prise = getItem(position).getNum_prise();
        String medicament = getItem(position).getMedicament();
        String date = getItem(position).getDate_prise();
        String heure = getItem(position).getHeure();
        String qt = getItem(position).getQt();

        //Create the person object with the information
        Prise prise = new Prise(num_p,num_prise,medicament,date,heure,qt);

        //create the view result for showing the animation
        final View result;

        //ViewHolder object
         ViewItem holder;

        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) LayoutInflater.from(this.context);
            convertView = inflater.inflate(this.resource, parent, false);

             holder= new ViewItem();
             holder.medicamentView = (TextView) convertView.findViewById(R.id.medicamentView);
             holder.dateView = (TextView) convertView.findViewById(R.id.DatePriseView);
             holder.heureView = (TextView) convertView.findViewById(R.id.heureView);
             holder.qtView = (TextView) convertView.findViewById(R.id.qtView);

            result = convertView;

            convertView.setTag(holder);
        }
        else{
            holder = (PriseListAdapter.ViewItem) convertView.getTag();
            result = convertView;
        }


        Animation animation = AnimationUtils.loadAnimation(this.context,
                (position > lastPosition) ? R.anim.load_down_anim : R.anim.load_up_anim);
        result.startAnimation(animation);
        lastPosition = position;


          holder.medicamentView.setText(prise.getMedicament());
          holder.dateView.setText(prise.getDate_prise());
          holder.heureView.setText(prise.getHeure()+"h");
          holder.qtView.setText(prise.getQt());

        ImageView imageView = (ImageView) result.findViewById(R.id.item_info1);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        LocalDateTime now = LocalDateTime.now();

        String dd=prise.getDate_prise()+ " "+ prise.getHeure();

        System.out.println(dtf.format(now));
        System.out.println(prise.getHeure());

        if (dd.compareTo(dtf.format(now)) == 1)
            imageView.setColorFilter(ContextCompat.getColor(context, R.color.red), android.graphics.PorterDuff.Mode.MULTIPLY);
        else
            imageView.setColorFilter(ContextCompat.getColor(context, R.color.green), android.graphics.PorterDuff.Mode.MULTIPLY);

        return convertView;
    }
}

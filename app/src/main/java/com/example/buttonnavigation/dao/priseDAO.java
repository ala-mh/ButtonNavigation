package com.example.buttonnavigation.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.buttonnavigation.database.DBHelper;
import com.example.buttonnavigation.model.Prise;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class priseDAO {

    private SQLiteDatabase db;
    private DBHelper dbHelper;

    public priseDAO(Context context) {
        dbHelper = new DBHelper(context);
        dbHelper.createDataBase();
        db = dbHelper.getWritableDatabase();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public List<Prise> getAllPrise (String num_p) {

        List<Prise> temp = new ArrayList<Prise>();
        Cursor c;
        try {

            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDateTime now = LocalDateTime.now();
            //System.out.println(dtf.format(now));

            c = db.rawQuery("Select * FROM prog_prise_med where num_p=? AND date=? order by heure", new String[] {num_p , dtf.format(now)});
            if (c == null) return null;
            c.moveToFirst();
            do {
                Prise prise = new Prise(c.getString(c.getColumnIndex("ref_med")), c.getString(c.getColumnIndex("heure")), c.getString(c.getColumnIndex("qte")));
                temp.add(prise);
            } while (c.moveToNext());
            c.close();
        } catch (Exception e) {
        }
        return temp;
    }
}

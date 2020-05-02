package com.example.buttonnavigation.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.buttonnavigation.database.DBHelper;
import com.example.buttonnavigation.model.Programme;

import java.util.ArrayList;
import java.util.List;

public class ProgrammeDAO {

    private SQLiteDatabase db;
    private DBHelper dbHelper;

    public ProgrammeDAO(Context context) {
        dbHelper = new DBHelper(context);
        dbHelper.createDataBase();
        db = dbHelper.getWritableDatabase();
    }

    public List<Programme> getAllProg () {

        List<Programme> temp = new ArrayList<Programme>();
        Cursor c;
        try {
            c = db.rawQuery("Select * FROM programme", null);
            if (c == null) return null;
            c.moveToFirst();
            do {
                Programme programme = new Programme(c.getInt(c.getColumnIndex("num_p")),c.getString(c.getColumnIndex("maladie")), c.getString(c.getColumnIndex("date_debut")), c.getString(c.getColumnIndex("duree")));
                temp.add(programme);
            } while (c.moveToNext());
            c.close();
        } catch (Exception e) {
        }
        return temp;
    }
    /*
    public static List<Programme> getAllProg(SQLiteDatabase db) {

        List<Programme> temp = new ArrayList<Programme>();
        Cursor c;
        try {
            c = db.rawQuery("Select * FROM programme", null);
            if (c == null) return null;
            c.moveToFirst();
            do {
                Programme programme = new Programme(c.getString(c.getColumnIndex("maladie")), c.getString(c.getColumnIndex("date_debut")), c.getString(c.getColumnIndex("duree")));
                temp.add(programme);
            } while (c.moveToNext());
            c.close();
        } catch (Exception e) {
        }
        //db.close();
        return temp;
    }*/


}

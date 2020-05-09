package com.example.buttonnavigation.database;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.util.Log;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class DBHelper extends SQLiteOpenHelper {

    private static final String TAG ="";
    private static String DB_PATH = "";
    private static String DB_NAME ="android.db";// Database name
    private SQLiteDatabase mDataBase;
    private Context mContext=null;

    public DBHelper( Context context) {
        super(context, DB_NAME, null, 1);
        if(Build.VERSION.SDK_INT >= 17){
            DB_PATH = context.getApplicationInfo().dataDir + "/databases/";
        }
        else
        {
            DB_PATH = "/data/data/"+context.getApplicationInfo().dataDir + "/databases/";
        }
        this.mContext = context;
    }

    @Override
    public synchronized void close() {
        if(mDataBase != null)
            mDataBase.close();
        super.close();
    }

    private boolean checkDataBase(){
        SQLiteDatabase tempDB=null;
        try {
            String path=DB_PATH+DB_NAME;
            tempDB=SQLiteDatabase.openDatabase(path,null,SQLiteDatabase.OPEN_READWRITE);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        if (tempDB != null) tempDB.close();
        return tempDB!= null?true:false;
    }

    private void copyDataBase() {
        try{
            InputStream myInput = mContext.getAssets().open(DB_NAME);
            String outputFileName = DB_PATH + DB_NAME;
            OutputStream mOutput = new FileOutputStream(outputFileName);

            byte[] Buffer = new byte[1024];
            int mLength;

            while ((mLength = myInput.read(Buffer))>0)
            {
                mOutput.write(Buffer, 0, mLength);
            }
            mOutput.flush();
            mOutput.close();
            myInput.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void openDataBase() {
        String Path = DB_PATH + DB_NAME;
        mDataBase = SQLiteDatabase.openDatabase(Path, null, SQLiteDatabase.OPEN_READWRITE);
    }

    public void createDataBase() {

        //boolean isDBExist = checkDataBase();
        //if(!isDBExist)
        //{
        this.getReadableDatabase();
        try {
            //Copy the database from assests
            copyDataBase();
            Log.e(TAG, "createDatabase database created");
        }
        catch (Exception e) {
            throw new Error("ErrorCopyingDataBase");
        }
        //}
    }


   /* //getAllProg
    public List<Programme> getAllProg(){

        SQLiteDatabase db =this.getWritableDatabase();
        List<Programme> temp = new ArrayList<Programme>();

        temp = ProgrammeDAO.getAllProg(db);

        return temp;
    }*/


    @Override
    public void onCreate(SQLiteDatabase db) {

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

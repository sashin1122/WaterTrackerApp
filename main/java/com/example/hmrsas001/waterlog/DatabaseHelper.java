package com.example.hmrsas001.waterlog;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String TAG = "DatabaseHelper";

    public static final String Table_Name = "diary_table";
    public static final String col1 = "date";
    public static final String col2 = "shower";
    public static final String col3 = "toilet";
    public static final String col4 = "drinking";
    public static final String col5 = "hygiene";
    public static final String col6 = "laundry";
    public static final String col7 = "dishes";
    public static final String col8 = "cooking";
    public static final String col9 = "cleaning";
    public static final String col10 = "other";
    public static final String col11 = "total";


    public DatabaseHelper(Context context){
        super(context,Table_Name,null,1);

    }
    @Override
    public void onCreate(SQLiteDatabase db) {

        String createtable = "CREATE TABLE " + Table_Name + " (DATE DATETIME DEFAULT CURRENT_DATE, " +col2+
                " TEXT,"+col3+" TEXT," +col4+" TEXT,"+col5+" TEXT,"+col6+" TEXT,"+col7+" TEXT,"+col8+" TEXT,"
                +col9+" TEXT,"+col10+" TEXT,"+col11+" TEXT,"+"ID INTEGER PRIMARY KEY AUTOINCREMENT)";
        db.execSQL(createtable);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        db.execSQL("DROP IF TABLE EXISTS "+Table_Name);
        onCreate(db);

    }

    public boolean addData(String shower,String toilet, String drinking,String hygiene,String laundry,String dishes,String cooking, String cleaning, String other, String total){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentvalue = new ContentValues();
        contentvalue.put(col2,shower);
        contentvalue.put(col3,toilet);
        contentvalue.put(col4,drinking);
        contentvalue.put(col5,hygiene);
        contentvalue.put(col6,laundry);
        contentvalue.put(col7,dishes);
        contentvalue.put(col8,cooking);
        contentvalue.put(col9,cleaning);
        contentvalue.put(col10,other);
        contentvalue.put(col11,total);

        Log.d(TAG,"adding" + shower );
        Log.d(TAG,"adding" + total );


        long result = db.insert(Table_Name,null, contentvalue);

        if (result== -1){
            return false;
        }else{
            return true;
        }

    }

    public Cursor getData(){
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor data = db.rawQuery("SELECT * FROM " + Table_Name,null);
        return data;
    }

    public int getTotal() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT SUM(total) as Total FROM " + Table_Name, null);
        int total =0;
        if (cursor.moveToFirst()) {

            total = cursor.getInt(cursor.getColumnIndex("Total"));// get final total
            return total;
        }else{
            return total;
        }

    }

    public int getDailyAv(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT AVG(total) as Total FROM " + Table_Name, null);
        int total =0;
        if (cursor.moveToFirst()) {

            total = cursor.getInt(cursor.getColumnIndex("Total"));// get final total
            return total;
        }else{
            return total;
        }

    }
}

package com.example.nasir.myparking;
/*
 * Author: Syed Nasir Gohary
 * Date: 2018/07/15
 *Subject: Comp231
 * Project Name: myParking
 * */
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    public static final String databse_name="parking.db";
    public static final String table_name="booking";
    public static final String col_1="id";
    public static final String col_2="cname";
    public static final String col_3="pklname";
    public static final String col_4="pkaddress";
    public static final String col_5="timefrom";
    public static final String col_6 ="timeto";
    public static final String col_7="cardnumber";
    public static final String col_8="exprirydate";
    public static final String col_9="securitycode";

    public DBHelper (Context context) {
        super(context, databse_name, null, 1);


    }

    @Override
    public void onCreate (SQLiteDatabase db) {

        db.execSQL("create table "+table_name+" (id INTEGER primary key autoincrement, cname TEXT, pklname TEXT, pkaddress TEXT, timefrom TEXT, timeto TEXT , cardnumber TEXT, exprirydate TEXT, securitycode TEXT)");
    }

    @Override
    public void onUpgrade (SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("drop table if exists "+table_name);
        onCreate(db);
    }

    public boolean insertData (String CUSTNAME, String PKNAME, String PKADDRESS, String TIMEFROM, String TIMETO, String CARDNUMBER, String EXPIRYDATE, String SECURITYCODE ){
        SQLiteDatabase db=this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(col_2,CUSTNAME);
        values.put(col_3,PKNAME);
        values.put(col_4,PKADDRESS);
        values.put(col_5,TIMEFROM);
        values.put(col_6,TIMETO);
        values.put(col_7,CARDNUMBER);
        values.put(col_8,EXPIRYDATE);
        values.put(col_9,SECURITYCODE);

        long result=db.insert(table_name,null,values);

        if (result==-1){
        return false;
        }else {
            return true;
        }
    }

    //checking if Sqlite is accepting the records
    public Cursor getAllData(){

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor re = db.rawQuery("select * from "+table_name,null);
        return re;
    }


    // for updating of table
    public boolean updateData( String id, String CUSTNAME, String PKNAME, String PKADDRESS, String TIMEFROM, String TIMETO, String CARDNUMBER, String EXPIRYDATE, String SECURITYCODE){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(col_1,id);
        values.put(col_2,CUSTNAME);
        values.put(col_3,PKNAME);
        values.put(col_4,PKADDRESS);
        values.put(col_5,TIMEFROM);
        values.put(col_6,TIMETO);
        values.put(col_7,CARDNUMBER);
        values.put(col_8,EXPIRYDATE);
        values.put(col_9,SECURITYCODE);

        db.update(table_name,values,"id=?",new String[]{id});

        return true;
    }

    //for deleting records
    public Integer deleteData(String id){

        SQLiteDatabase database=this.getWritableDatabase();
        return database.delete(table_name,"id= ?", new String[]{id});
    }
}

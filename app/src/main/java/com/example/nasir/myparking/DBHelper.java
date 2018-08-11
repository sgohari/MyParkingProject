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
import android.util.Log;

import java.net.PortUnreachableException;

public class DBHelper extends SQLiteOpenHelper {

    public static final String databse_name="parking.db";
    public static final String table_name="booking";
    public static final String col_1="id";
    public static final String col_2="cname";
    public static final String col_3="pklname";
    public static final String col_4="pkaddress";
    public static final String col_5="timefrom";
    public static final String col_6 ="timeto";
    public static final String col_7="cardtype";
    public static final String col_8="cardnumber";
    public static final String col_9="exprirydate";
    public static final String col_10="securitycode";

    //Table Name
    public static String REGISTRATION_TABLE = "Registration";
    public static final String COLUMN_ID = "Registration_id";
    public static final String COLUMN_USERNAME = "_name";
    public static final String COLUMN_PASSWORD = "_password";
    public static final String COLUMN_FNAME = "_firstname";
    public static final String COLUMN_LNAME = "_lastname";
    public static final String COLUMN_ADDRESS = "_address";
    public static final String COLUMN_CITY = "_city";
    public static final String COLUMN_PCODE = "_postal_code";
    Customer_obj customer = new Customer_obj();


    public DBHelper (Context context) {
        super(context, databse_name, null, 1);
    }

    @Override
    public void onCreate (SQLiteDatabase db) {

      db.execSQL("create table "+table_name+" (id INTEGER primary key autoincrement, cname TEXT, pklname TEXT, pkaddress TEXT, timefrom TEXT, timeto TEXT , cardnumber TEXT, exprirydate TEXT, securitycode TEXT)");

        db.execSQL("create table "+REGISTRATION_TABLE+" (id INTEGER primary key autoincrement, _name TEXT, _password TEXT, _firstname TEXT, _lastname TEXT, _address TEXT , _city TEXT, _postal_code TEXT)");

    }

    @Override
    public void onUpgrade (SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("drop table if exists "+REGISTRATION_TABLE);
        db.execSQL("drop table if exists "+table_name);

        onCreate(db);
    }

    public boolean insertData (String CUSTNAME, String PKNAME, String PKADDRESS, String TIMEFROM, String TIMETO,String CARDTYPE, String CARDNUMBER, String EXPIRYDATE, String SECURITYCODE ){
        SQLiteDatabase db=this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(col_2,CUSTNAME);
        values.put(col_3,PKNAME);
        values.put(col_4,PKADDRESS);
        values.put(col_5,TIMEFROM);
        values.put(col_6,TIMETO);
        values.put(col_7,CARDTYPE);
        values.put(col_8,CARDNUMBER);
        values.put(col_8,EXPIRYDATE);
        values.put(col_10,SECURITYCODE);

        long result=db.insert(table_name,null,values);

        if (result==-1){
        return false;
        }else {
            return true;
        }
    }
    public boolean insertDataRegistration (String USERNAME, String PASSWORD, String FIRSTNAME, String LASTNAME, String ADDRESS, String CITY, String POSTALCODE){
        SQLiteDatabase db=this.getWritableDatabase();

        ContentValues RGvalues = new ContentValues();
        RGvalues.put(COLUMN_USERNAME,USERNAME);
        RGvalues.put(COLUMN_PASSWORD,PASSWORD);
        RGvalues.put(COLUMN_FNAME,FIRSTNAME);
        RGvalues.put(COLUMN_LNAME,LASTNAME);
        RGvalues.put(COLUMN_ADDRESS,ADDRESS);
        RGvalues.put(COLUMN_CITY,CITY);
        RGvalues.put(COLUMN_PCODE,POSTALCODE);

        long outCome=db.insert(REGISTRATION_TABLE,null,RGvalues);

        if (outCome==-1){

            return false;
        }
        else {
            return true;
        }
    }
    //checking if Sqlite is accepting the records
    public Cursor getAllData(){

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor re = db.rawQuery("select * from "+table_name,null);
        return re;
    }

   /* public Cursor searchOne(){

        SQLiteDatabase db= this.getWritableDatabase();

        //Cursor c =db.rawQuery("Select * From "+table_name,"id=?",new String[]{id});
        return c;
    }*/


    // for updating of table
    public boolean updateData( String id, String CUSTNAME, String PKNAME, String PKADDRESS, String TIMEFROM, String TIMETO, String CARDTYPE, String CARDNUMBER, String EXPIRYDATE, String SECURITYCODE){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(col_1,id);
        values.put(col_2,CUSTNAME);
        values.put(col_3,PKNAME);
        values.put(col_4,PKADDRESS);
        values.put(col_5,TIMEFROM);
        values.put(col_6,TIMETO);
        values.put(col_7,CARDTYPE);
        values.put(col_8,CARDNUMBER);
        values.put(col_9,EXPIRYDATE);
        values.put(col_10,SECURITYCODE);

        db.update(table_name,values,"id=?",new String[]{id});

        return true;
    }

    //for deleting records
    public Integer deleteData(String id){

        SQLiteDatabase database=this.getWritableDatabase();
        return database.delete(table_name,"id= ?", new String[]{id});
    }

    public boolean checkLogin(String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();

        String s;
        Cursor c = db.rawQuery("SELECT * FROM "+REGISTRATION_TABLE+" WHERE " + username + " =? AND" + password + " =?", null);

        if(c.getCount() <= 0) {
            c.close();
            db.close();
            return false;
        } else {
            c.close();
            db.close();
            return true;
        }
    }

    public String SearchExistingAccount(String uname){
        SQLiteDatabase db=this.getReadableDatabase();
        String query = "SELECT _name, _password FROM "+ REGISTRATION_TABLE + " where _name = ? ";
        Cursor cursor = db.rawQuery(query, new String[] {uname});
        String a, b;
        b = null;
        if(cursor.moveToLast()){
            do{
                a = cursor.getString(0);
                if(a.equals(uname)){
                    b = cursor.getString(1);
                    break;
                }
            }while(cursor.moveToNext());
        }
        return b;
    }
}


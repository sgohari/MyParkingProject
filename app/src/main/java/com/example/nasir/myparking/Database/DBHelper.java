package com.example.nasir.myparking.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import com.example.nasir.myparking.Database.*;

public class DBHelper extends SQLiteOpenHelper {

    //Database constants
    public static final String DATABASE_NAME = "parkingDB";
    static final int DATABASE_VERSION = 3; //upgrade database
    static final String TAG = "DBHelper";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Execute queries
        db.execSQL(reservationTable.DATABASE_CREATE_RESERVATIONS);
        db.execSQL(registrationTable.DATABASE_CREATE_USERS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Drop existing tables
        Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
                + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS reservation");
        db.execSQL("DROP TABLE IF EXISTS registration");
        onCreate(db);
    }
}

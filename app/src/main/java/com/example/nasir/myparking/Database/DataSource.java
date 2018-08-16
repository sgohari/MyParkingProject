package com.example.nasir.myparking.Database;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.nasir.myparking.Registration;

public class DataSource {
    private Context mContext;
    private SQLiteDatabase mDatabase;
    SQLiteOpenHelper mDBHelper;

    //Table Names
    static final String RESERVATION_TABLE = "reservation";
    static final String REGISTRATION_TABLE = "registration";


    public DataSource (Context context) {
        this.mContext = context;
        mDBHelper = new DBHelper(mContext);
        mDatabase = mDBHelper.getWritableDatabase();
    }

    public void open () {
        mDatabase = mDBHelper.getWritableDatabase();
    }

    public void close () {
        mDBHelper.close();
    }

    /*                                                              Reservation TABLE                                        */

    //---insert a reservation into the database---
    public long insertReservation (String fk_userID, String parkingName, String parkingAddress, String timeFrom, String timeTo,String cardType,
                                   String cardNumber, String expiryDate, String CVV) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(reservationTable.FK_USER_ID, fk_userID);
        initialValues.put(reservationTable.KEY_PARKING_NAME, parkingName);
        initialValues.put(reservationTable.KEY_PARKING_ADDRESS, parkingAddress);
        initialValues.put(reservationTable.KEY_TIME_FROM, timeFrom);
        initialValues.put(reservationTable.KEY_TIME_TO, timeTo);
        initialValues.put(reservationTable.KEY_CARD_TYPE, cardType);
        initialValues.put(reservationTable.KEY_CARD_NUMBER, cardNumber);
        initialValues.put(reservationTable.KEY_EXPIRY_DATE, expiryDate);
        initialValues.put(reservationTable.KEY_CVV,Integer.parseInt( CVV));
        return mDatabase.insert(RESERVATION_TABLE, null, initialValues);
    }

    //---updates a Reservation---
    public boolean updateReservation (int reservationID, String timeFrom, String timeTo) {
        //Update time of the reservation
        ContentValues args = new ContentValues();
        args.put(reservationTable.KEY_TIME_FROM, timeFrom);
        args.put(reservationTable.KEY_TIME_TO, timeTo);
        return mDatabase.update(RESERVATION_TABLE, args, reservationTable.PK_RESERVATION_ID + "=" + reservationID, null) > 0;
    }

    //---deletes a Reservation---
    public boolean deleteReservation (int reservationID) {
        return mDatabase.delete(RESERVATION_TABLE, reservationTable.PK_RESERVATION_ID + "=" + reservationID, null) > 0;
    }

    //---retrieves a particular reservation---
    public Cursor getReservation (int reservationID) throws SQLException {
        Cursor mCursor =
                mDatabase.query(true, RESERVATION_TABLE, new String[]{reservationTable.PK_RESERVATION_ID, reservationTable.FK_USER_ID,
                                reservationTable.KEY_PARKING_NAME, reservationTable.KEY_PARKING_ADDRESS,
                                reservationTable.KEY_TIME_FROM, reservationTable.KEY_TIME_TO,reservationTable.KEY_CARD_TYPE, reservationTable.KEY_CARD_NUMBER, reservationTable.KEY_CVV},
                        reservationTable.PK_RESERVATION_ID + "=" + reservationID, null,
                        null, null, null, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }

    //---retrieves all the reservations---
    public Cursor getAllReservations () {
        return mDatabase.query(RESERVATION_TABLE, new String[]{reservationTable.PK_RESERVATION_ID, reservationTable.KEY_PARKING_NAME, reservationTable.KEY_PARKING_ADDRESS,
                        reservationTable.KEY_TIME_FROM, reservationTable.KEY_TIME_TO, reservationTable.KEY_CARD_NUMBER, reservationTable.KEY_CVV},
                null, null, null, null, null);
    }

    /*                                                              Registration TABLE                                        */

    //---insert a registration into the database---
    public long insertRegistration (int pk_userID, String password, String firstName, String lastName, String address, String city, String postalCode) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(registrationTable.PK_USER_ID, pk_userID);
        initialValues.put(registrationTable.KEY_PASSWORD, password);
        initialValues.put(registrationTable.KEY_FIRST_NAME, firstName);
        initialValues.put(registrationTable.KEY_LAST_NAME, lastName);
        initialValues.put(registrationTable.KEY_ADDRESS, address);
        initialValues.put(registrationTable.KEY_CITY, city);
        initialValues.put(registrationTable.KEY_POSTAL_CODE, postalCode);

        return mDatabase.insert(REGISTRATION_TABLE, null, initialValues);
    }

    //Retrieve user
    public Cursor getNameOfUser (String username) throws SQLException {
        Cursor mCursor =
                mDatabase.query(true, REGISTRATION_TABLE, new String[]{registrationTable.KEY_FIRST_NAME,registrationTable.KEY_LAST_NAME},
                        registrationTable.PK_USER_ID + "=" + username, null,
                        null, null, null, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }

    //Verify the username and password
    public Cursor validateUserPassword (Integer userID) throws SQLException {

        Cursor mCursor =
                mDatabase.query(true, REGISTRATION_TABLE, new String[]{registrationTable.KEY_PASSWORD},
                        registrationTable.PK_USER_ID + "=" + userID, null,
                        null, null, null, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }
}
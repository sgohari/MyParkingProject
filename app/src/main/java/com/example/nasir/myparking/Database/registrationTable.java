package com.example.nasir.myparking.Database;

public class registrationTable {

    //Registration table fields
    public static final String PK_USER_ID = "userID"; //PK
    public static final String KEY_PASSWORD = "password";
    public static final String KEY_FIRST_NAME = "firstName";
    public static final String KEY_LAST_NAME = "lastName";
    public static final String KEY_ADDRESS = "address";
    public static final String KEY_CITY= "city";
    public static final String KEY_POSTAL_CODE = "postalCode";

    //TARGET
    public static final String DATABASE_CREATE_USERS =
            "create table if not exists registration (userID integer primary key , password text not null, " //username is int
                    + "firstName text not null, lastName text not null, address text, city text not null, postalCode text);";
}

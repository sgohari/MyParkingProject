package com.example.nasir.myparking.Database;

/*
Author: Jason Nguessan
Date: 2018/08/16
Future Tables not yet used this iteration
 */
public class futureTables {

    public static final String DATABASE_MAINTENANCE = "CREATE TABLE IF NOT EXISTS Maintenance ( Id INTEGER NOT NULL UNIQUE , username TEXT NOT NULL UNIQUE " +
            ", password TEXT NOT NULL, name TEXT NOT NULL, employeeNumber TEXT NOT NULL, Administrator_Id INTEGER NOT NULL, " +
            "PRIMARY KEY(Id), FOREIGN KEY(Administrator_Id) References Administrator(id));";

    public static final String DATABASE_FINANCE = "CREATE TABLE IF NOT EXISTS Finance ( Id INTEGER NOT NULL, username TEXT NOT NULL UNIQUE, password NOT NULL " +
            ", name INTEGER NOT NULL, Administrator_Id INTEGER NOT NULL, Customer_Id Integer NOT NULL, " +
            "FOREIGN KEY(Administrator_Id) References Administrator(id), Primary Key(Id), FOREIGN KEY(Customer_Id) References Customer(Id));";

}

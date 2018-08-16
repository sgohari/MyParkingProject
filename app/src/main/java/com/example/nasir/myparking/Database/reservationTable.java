package com.example.nasir.myparking.Database;

public class reservationTable {
    //Reservation table fields
    public static final String PK_RESERVATION_ID = "reservationID"; //PK
    public static final String FK_USER_ID = "userID"; //FK
    public static final String KEY_PARKING_NAME = "parkingName";
    public static final String KEY_PARKING_ADDRESS = "parkingAddress";
    public static final String KEY_TIME_FROM = "timeFrom";
    public static final String KEY_TIME_TO = "timeTo";
    public static final String KEY_CARD_TYPE = "cardType";
    public static final String KEY_CARD_NUMBER = "cardNumber";
    public static final String KEY_EXPIRY_DATE = "expiryDate";
    public static final String KEY_CVV = "cvv";

    //TARGET
    public static final String DATABASE_CREATE_RESERVATIONS =
            "create table if not exists reservation (reservationID integer primary key autoincrement,userID integer, " //username is int
                    + "parkingName text not null, parkingAddress text not null, timeFrom text, timeTo text not null,cardType text , cardNumber text not null, expiryDate text" +
                    ",cvv integer not null,foreign key(userID) references registration(userID));"; //missing FK
}

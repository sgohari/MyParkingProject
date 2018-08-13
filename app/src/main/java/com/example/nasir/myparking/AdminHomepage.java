package com.example.nasir.myparking;
/*
* Author: Syed Nasir Gohary
* Date: 2018/08/06
*Subject: Comp231
* Project Name: myParking
* */
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.example.nasir.myparking.Database.DBHelper;
import com.example.nasir.myparking.Database.DataSource;

public class AdminHomepage extends AppCompatActivity {

    DataSource myDB;

    EditText id,custNameAdminET,pkLotNameAdminET,pkLotAddressAdminET,timingFromAdminET,timingToAdminET,cardNumberAdminET,expiryDateAdminET,securityCodeAdminET;
    Button btnAdd, btnView,btnUpdate,btnDelete,btnSearch;
    String reservationID;
    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_homepage);

        myDB = new DataSource(this);

        custNameAdminET=(EditText)findViewById(R.id.custNameAdminET);
        pkLotNameAdminET=(EditText)findViewById(R.id.pkLotNameAdminET);
        pkLotAddressAdminET=(EditText)findViewById(R.id.pkLotAddressAdminET);
        timingFromAdminET=(EditText)findViewById(R.id.timingFromAdminET);
        timingToAdminET=(EditText)findViewById(R.id.timingToAdminET);
        cardNumberAdminET=(EditText)findViewById(R.id.cardNumberAdminET);
        expiryDateAdminET=(EditText)findViewById(R.id.expireDateET);
        securityCodeAdminET=(EditText)findViewById(R.id.securityCodeAdminET);
        id=(EditText)findViewById(R.id.idET);
        btnAdd=(Button)findViewById(R.id.btnAdd);

        btnView = (Button)findViewById(R.id.btnViewAll);
        btnUpdate=(Button)findViewById(R.id.btnUpdate);
        btnDelete=(Button)findViewById(R.id.btnDelete);
        btnSearch=(Button)findViewById(R.id.btnSearchAdmin);


    }

    //Search Reservation
    public void searchReservation_OnClick(View view) {
        reservationID = id.getText().toString();

        myDB.open();
        Cursor c = myDB.getReservation(Integer.parseInt(reservationID));
        if (c.moveToFirst())
        {
            //Get reservation by ID
            pkLotNameAdminET.setText(c.getString(2));
            pkLotAddressAdminET.setText(c.getString(3));
            timingFromAdminET.setText(c.getString(4));
            timingToAdminET.setText(c.getString(5));
            cardNumberAdminET.setText(c.getString(7));
            securityCodeAdminET.setText(c.getString(8));
            //Set text = reservationInfo
        }
        myDB.close();
    }

    //Update Reservation
    public void updateReservation_OnClick(View view) {
        //Assign value
        reservationID = id.getText().toString();
        String timeFrom = timingFromAdminET.getText().toString();
        String timeTo = timingToAdminET.getText().toString();

        myDB.open();
        myDB.updateReservation(Integer.parseInt(reservationID),timeFrom,timeTo);
        myDB.close();
    }

    //Delete Reservation
    public void deleteReservation_OnClick(View view) {
        //Assign ID value
        reservationID = id.getText().toString();
        myDB.open();
        myDB.deleteReservation(Integer.parseInt(reservationID));
        myDB.close();
    }

    //View All Reservations
    public void viewAllReservations_OnClick(View view) {

    }
}

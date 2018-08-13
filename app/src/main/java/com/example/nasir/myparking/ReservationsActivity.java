package com.example.nasir.myparking;
//Author
//date
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.nasir.myparking.Database.DBHelper;
import com.example.nasir.myparking.Database.DataSource;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class ReservationsActivity extends AppCompatActivity {

    EditText parkingNameET,parkingAddressET,timeFromET,timeToET,cardNumberET,expiryDateET,cvvET;
    String parkingName,parkingAddress,timeFrom,timeTO,cardNumber,expiryDate,cvv, cardType;
    Button btnSaves, btnClears,btnView;
    Date RTimeFrom, RTimeTo, result;
    RadioGroup rdGroup;
    RadioButton rdCredit,rdDebit;

    String username;

    DataSource myDb;
    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservations);

        myDb = new DataSource(this);

        //get Intent extra (username)
        username = getIntent().getStringExtra("username");

        parkingNameET = (EditText) findViewById(R.id.parkingNameET);
        parkingAddressET = (EditText) findViewById(R.id.parkingLotAddressET);
        timeFromET = (EditText) findViewById(R.id.fromET);
        timeToET = (EditText) findViewById(R.id.toET);
        cardNumberET = (EditText) findViewById(R.id.cardNumberET);
        expiryDateET = (EditText) findViewById(R.id.expireDateET);
        cvvET = (EditText) findViewById(R.id.securityCodeET);

        //Assign Values
        parkingName = parkingNameET.getText().toString();
        parkingAddress = parkingAddressET.getText().toString();
        timeFrom = timeFromET.getText().toString();
        timeTO = timeToET.getText().toString();
        cardNumber = cardNumberET.getText().toString();
        expiryDate = expiryDateET.getText().toString();
        cvv = cvvET.getText().toString();

        displaySharedInfor();
        //Radio Group
        rdGroup = (RadioGroup) findViewById(R.id.rdgGenders);

        //Assigning card type
        rdGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged (RadioGroup group, int i) {
                RadioButton checkedRadioButton = (RadioButton) group.findViewById(i);
                // This puts the value (true/false) into the variable
                boolean isChecked = checkedRadioButton.isChecked();
                // If the radiobutton that has changed in check state is now checked...
                if (isChecked) {
                    // Changes the textview's text to "Checked: example radiobutton text"
                    cardType = checkedRadioButton.getText().toString();
                }
            }
        });
    }

    public void displaySharedInfor(){
        SharedPreferences sharedPreferences=getSharedPreferences("markerContent", Context.MODE_PRIVATE);
        String title = sharedPreferences.getString("title","");
        String snipped=sharedPreferences.getString("snipped","");
        parkingNameET.setText(title);
        parkingAddressET.setText(snipped);

    }
    //Clear all fields
    public void clear_onClick(View view){
        btnClears.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v)
            {
                EditText[] editTexts = {parkingNameET,parkingAddressET,timeFromET,timeToET,cardNumberET,expiryDateET,cvvET};

                for (EditText et:editTexts) {
                    et.setText("");
                }
            }
        });
    }

    //for checking the database.


    //Confirm reservation
    public void reserve_OnClick(View view) {
        myDb.open();
        myDb.insertReservation(Integer.parseInt(username),parkingName,parkingAddress,timeFrom,timeTO,cardType,cardNumber,expiryDate,cvv);
        String name = null;

       Cursor c=  myDb.getNameOfUser(Integer.parseInt(username));
        if (c.moveToFirst())
        {
            name = c.getString(0);
        }
        myDb.close();

        //Pass name, parking lot name and address to receipt
        Intent intent = new Intent(this,ReceiptActivity.class);
        intent.putExtra("name",name);
        intent.putExtra("parkingName",parkingName);
        intent.putExtra("parkingAddress",parkingAddress);
        startActivity(intent);
    }
}

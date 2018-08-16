package com.example.nasir.myparking;

/*
Author: Jason Nguessan
Date: 2018/08/16
Description: Validated Reservation Page
 */
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.provider.MediaStore;
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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReservationsActivity extends AppCompatActivity {

    EditText parkingNameET,parkingAddressET,timeFromET,timeToET,cardNumberET,expiryDateET,cvvET;
    String parkingName,parkingAddress,timeFrom,timeTO,cardNumber,expiryDate,cvv, cardType;
    Button btnSaves, btnClears,btnView;
    Date RTimeFrom, RTimeTo, result;
    RadioGroup rdGroup;
    RadioButton rdCredit,rdDebit;

    private static final String USERNAME_PREFS = "username_prefs";

    String username;
    SharedPreferences sharedPreferences;
    DataSource myDb;
    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservations);

        myDb = new DataSource(this);

        //get Intent extra (username)
        sharedPreferences = getSharedPreferences(USERNAME_PREFS, Context.MODE_PRIVATE);

        username = sharedPreferences.getString("username_key","");

        parkingNameET = (EditText) findViewById(R.id.parkingNameET);
        parkingAddressET = (EditText) findViewById(R.id.parkingLotAddressET);
        timeFromET = (EditText) findViewById(R.id.fromET);
        timeToET = (EditText) findViewById(R.id.toET);
        cardNumberET = (EditText) findViewById(R.id.cardNumberET);
        expiryDateET = (EditText) findViewById(R.id.expireDateET);
        cvvET = (EditText) findViewById(R.id.securityCodeET);

        displaySharedInfor();
        //Radio Group
        rdGroup = (RadioGroup) findViewById(R.id.rdgGenders);
        rdDebit = findViewById(R.id.rdDebit);
        rdCredit = findViewById(R.id.rdCredit);



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
                else
                    {
                        checkedRadioButton.setChecked(false);
                    }
            }
        });
    }


    //Clear all fields
    public void clear_onClick(View view){

                EditText[] editTexts = {parkingNameET,parkingAddressET,timeFromET,timeToET,cardNumberET,expiryDateET,cvvET };
                rdDebit.setChecked(false);
                rdCredit.setChecked(false);

        for (EditText et:editTexts) {
                    et.setText("");
                }
            }


    //for checking the database.


    //Confirm reservation
    public void reserve_OnClick(View view) {
        //Assign Values
        parkingName = parkingNameET.getText().toString();
        parkingAddress = parkingAddressET.getText().toString();
        timeFrom = timeFromET.getText().toString();
        timeTO = timeToET.getText().toString();
        cardNumber = cardNumberET.getText().toString();
        expiryDate = expiryDateET.getText().toString();
        cvv = cvvET.getText().toString();



        //Set Time Format
        DateFormat fmt = new SimpleDateFormat("HH:mm");

        //Set Expired Date Format
        DateFormat mmyy = new SimpleDateFormat("MM/yyyy");
        DateFormat yy = new SimpleDateFormat("yyyy");
        DateFormat mm = new SimpleDateFormat("MM");
        mmyy.setLenient(false);

        //Setting Current year, month
        Calendar calendar = new GregorianCalendar();
        int currentYear = calendar.get(Calendar.YEAR);
        int currentMonth  = calendar.get(Calendar.MONTH) + 1;
        String year = null, month = null;
        //Checkers
        Boolean yearDateCheck = false;
        Boolean timeCheck = false;


        //Address Validation block - based on Registration page - can be reduced using a method
        String regex2 = "^[0-9]+ ?[A-Za-z\\s]+$";
        Pattern pattern2 = Pattern.compile(regex2); //Allow White Space afterwardsx
        boolean address_bool = false;
        List<String> ad = new ArrayList<String>();
        ad.add(parkingAddress);


        for (String address : ad ) {
            Matcher matcher = pattern2.matcher(address);
            if (matcher.matches()) {
                address_bool = true;
            } else {
                address_bool = false;
                ad.clear();

            }
        }

        try
        {
            //Full Result of expired Date
            result = mmyy.parse(expiryDate); // returns the full date month year day etc

            // Full Result of time
            RTimeFrom = fmt.parse(timeFrom);
            RTimeTo = fmt.parse(timeTO);

           // set inputed Year, month
             year = yy.format(result);
             month = mm.format(result);



            if(RTimeTo.after(RTimeFrom) && timeTO.length() <= 5 && timeFrom.length() <= 5)
            {
                timeCheck = true;

            }
            else {
                timeToET.setText("Enter a valid time interval");
            }


            if (Integer.parseInt(year) <= currentYear ){

                if (currentYear == Integer.parseInt(year) && yearDateCheck == false){
                    if (currentMonth + 1 > Integer.parseInt(month)){
                        expiryDateET.setError("Date must be in 'MM/yyyy and a month greater than " + currentMonth);
                        yearDateCheck = false;
                    }
                    else
                    {
                        yearDateCheck = true;
                    }

                }
                else if (Integer.parseInt(year) < currentYear && yearDateCheck && yearDateCheck )
                {
                    yearDateCheck = false;
                    expiryDateET.setError("Date must be in 'MM/yyyy and a month greater than " + currentMonth + "Date must be in 'MM/yyyy and greater than year " + year);
                }

            }

            else
            {
                yearDateCheck = true;
            }


        }catch(Exception e)
    {
        Toast.makeText(this, "Something went absolutely wrong please re-input these fields", Toast.LENGTH_SHORT).show();
        expiryDateET.setText("01/2018");
        timeFromET.setText("0:00");
        timeToET.setText("0:00");



    }


        if ( parkingName.length() < 5|| parkingAddress.length() < 3|| timeFrom.equals("")|| timeTO.equals("") || cardNumber.length() < 16|| cvv.length() < 3 || expiryDate.equals("")||cvv.equals("")
                || yearDateCheck == false || timeCheck == false || address_bool == false ) {
            if (parkingName.length() < 5 || parkingAddress.length() < 3 || address_bool == false  )
            {
                if (parkingName.length() < 5 )
                {
                    parkingNameET.setError("This field is too short");
                }

                else if(parkingAddress.length() < 5 || address_bool == false)
                    {
                        parkingAddressET.setError("This field is improperly done e.g 1 Deauville Lane");

                    }
            }

            else if (cardNumber.length() < 16) {
                Toast.makeText(ReservationsActivity.this, "card number Must be 16 digits", Toast.LENGTH_LONG).show();

            } else if (cvv.length() < 3) {
                Toast.makeText(ReservationsActivity.this, "security code must be 3 digits ", Toast.LENGTH_LONG).show();

            }

            Toast.makeText(ReservationsActivity.this,"Fields are Required",Toast.LENGTH_LONG).show();

        }

        else {
            myDb.open();
            myDb.insertReservation(username, parkingName, parkingAddress, timeFrom, timeTO, cardType,cardNumber, expiryDate, cvv);
            String name = null;

            Cursor c = myDb.getNameOfUser(username);
            if (c.moveToFirst()) {
                name = c.getString(0);
            }
            myDb.close();

            //Pass name, parking lot name and address to receipt
            Intent intent = new Intent(this, ReceiptActivity.class);
            intent.putExtra("name", name);
            intent.putExtra("parkingName", parkingName);
            intent.putExtra("parkingAddress", parkingAddress);
            startActivity(intent);
        }
    }

    public void displaySharedInfor(){
        SharedPreferences sharedPreferences=getSharedPreferences("markerContent", Context.MODE_PRIVATE);
        String title = sharedPreferences.getString("title","");
        String snipped=sharedPreferences.getString("snipped","");
        parkingNameET.setText(title);
        parkingAddressET.setText(snipped);
    }
}

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

        myDb=new DataSource(this);

        //get Intent extra (username)
        username = getIntent().getStringExtra("username");

        parkingNameET=(EditText)findViewById(R.id.parkingNameET);
        parkingAddressET=(EditText)findViewById(R.id.parkingLotAddressET);
        timeFromET=(EditText)findViewById(R.id.fromET);
        timeToET=(EditText)findViewById(R.id.toET);
        cardNumberET=(EditText)findViewById(R.id.cardNumberET);
        expiryDateET=(EditText)findViewById(R.id.expireDateET);
        cvvET=(EditText)findViewById(R.id.securityCodeET);

        //Assign Values
        parkingName = parkingNameET.getText().toString();
        parkingAddress = parkingAddressET.getText().toString();
        timeFrom = timeFromET.getText().toString();
        timeTO = timeToET.getText().toString();
        cardNumber = cardNumberET.getText().toString();
        expiryDate = expiryDateET.getText().toString();
        cvv = cvvET.getText().toString();

        //Radio Group
        rdGroup=(RadioGroup)findViewById(R.id.rdgGenders);

        //Assigning card type
        rdGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int i) {
                RadioButton checkedRadioButton = (RadioButton)group.findViewById(i);
                // This puts the value (true/false) into the variable
                boolean isChecked = checkedRadioButton.isChecked();
                // If the radiobutton that has changed in check state is now checked...
                if (isChecked)
                {
                    // Changes the textview's text to "Checked: example radiobutton text"
                   cardType = checkedRadioButton.getText().toString();
                }
            }
        });


        //insertingData();

    }

   /* public void insertingData(){

        btnSaves.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                Intent intentNext = new Intent(ReservationsActivity.this, ReceiptActivity.class);
                intentNext.putExtra("Name", custNameET.getText().toString());
                intentNext.putExtra("parkingLotName", pkLotName.getText().toString());
                intentNext.putExtra("parkingAddress", pkAddress.getText().toString());

                getName = custNameET.getText().toString();
                getPkLotName=pkLotName.getText().toString();
                getkAddress=pkAddress.getText().toString();
                getFrom=timeFrom.getText().toString();
                getTo=timeTo.getText().toString();
                getCardNumber=cardNumber.getText().toString();
                getExpireDate=expDate.getText().toString();
                getSecurity=securityCode.getText().toString();

                try
                {


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

                    //Full Result of expired Date
                    result = mmyy.parse(getExpireDate); // returns the full date month year day etc

                    //User inputed Year, month
                    String year = yy.format(result);
                    String month = mm.format(result);
                    // Full Result of time
                    RTimeFrom = fmt.parse(getFrom);
                    RTimeTo = fmt.parse(getTo);

                    //Checkers
                    Boolean yearDateCheck = false;
                    Boolean timeCheck = false;

                    if(RTimeTo.after(RTimeFrom))
                    {
                        timeCheck = true;

                    }
                    else
                        {
                            Toast.makeText(ReservationsActivity.this,"Enter a valid booking time ",Toast.LENGTH_LONG).show();

                        }

                    if (Integer.parseInt(year) <= currentYear ){

                    if (currentYear == Integer.parseInt(year) && yearDateCheck == false){
                        if (currentMonth + 1 > Integer.parseInt(month)){
                            Toast.makeText(ReservationsActivity.this,"Date must be in 'MM/yyyy and a month greater than " + currentMonth  ,Toast.LENGTH_LONG).show();
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
                        Toast.makeText(ReservationsActivity.this,"Date must be in 'MM/yyyy and greater than year " + year ,Toast.LENGTH_LONG).show();
                    }


                }
                else
                    {
                        yearDateCheck = true;
                    }

                    if (getName.equals("")|| getPkLotName.equals("")|| getkAddress.equals("")|| getFrom.equals("")|| getTo.equals("") || getCardNumber.equals("")|| getExpireDate.equals("")||getSecurity.equals("")
                         || yearDateCheck == false || timeCheck == false)
                {


                    if (cardNumber.getText().toString().length()>16){
                        Toast.makeText(ReservationsActivity.this,"card number Must be 16 digits",Toast.LENGTH_LONG).show();

                    }


                    else if (securityCode.getText().toString().length()>3){
                        Toast.makeText(ReservationsActivity.this,"security code must be 3 digits ",Toast.LENGTH_LONG).show();

                    }


                    Toast.makeText(ReservationsActivity.this,"Fields are Required",Toast.LENGTH_LONG).show();
                    expDate.setText(" ");
                    expDate.setHint("MM/YYYY");
                    timeFrom.setText("00:00");
                    timeTo.setText("00:00");



                    return;
                }
                    boolean isInserted = myDb.insertData(custNameET.getText().toString(),pkLotName.getText().toString(),pkAddress.getText().toString(),RTimeFrom.toString(),RTimeTo.toString(), cardNumber.getText().toString(),result.toString(),securityCode.getText().toString());
                    if (isInserted==true){

                        startActivity(intentNext);

                        Toast.makeText(ReservationsActivity.this,"Record Added to DB",Toast.LENGTH_LONG).show();

                    }else {
                        Toast.makeText(ReservationsActivity.this,"Record is not added to DB",Toast.LENGTH_LONG).show();
                    }

                }catch(Exception e)
                {
                    Toast.makeText(ReservationsActivity.this,"Something Went Absolutely Wrong, " +
                            "please follow the hints and make sure all fields are filled!!",Toast.LENGTH_LONG).show();
                } }

        }
        );
    }*/

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

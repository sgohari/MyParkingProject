package com.example.nasir.myparking;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReservationsActivity extends AppCompatActivity implements View.OnClickListener {

    EditText custNameET,pkLotName,pkAddress,timeFrom,timeTo,cardNumber,expDate,securityCode;
    String getName,getPkLotName,getkAddress,getFrom,getTo,getCardNumber,getExpireDate,getSecurity;
    SQLiteDatabase myParkingDatabase;

    Button btnSaves, btnClears;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservations);

        //Database created
        myParkingDatabase=openOrCreateDatabase("myParkingDB",Context.MODE_PRIVATE,null);

        myParkingDatabase.execSQL("CREATE TABLE IF NOT EXISTS Reservation(id INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR(255), lotname VARCHAR(255), parkingAddress VARCHAR (255),timeFrom VARCHAR (255),timeTo VARCHAR(255), cardNumbers VARCHAR(255), expiryDates VARCHAR(255), securityCodes VARCHAR(255));");

        btnSaves=(Button)findViewById(R.id.btnConfirmRV);
        btnClears=(Button)findViewById(R.id.btnClearRV);
        btnSaves.setOnClickListener(this);
        btnClears.setOnClickListener(this);

        custNameET= (EditText)findViewById(R.id.customerNameET);
        pkLotName=(EditText)findViewById(R.id.parkingNameET);
        pkAddress=(EditText)findViewById(R.id.parkingLotAddressET);
        timeFrom=(EditText)findViewById(R.id.fromET);
        timeTo=(EditText)findViewById(R.id.toET);
        cardNumber=(EditText)findViewById(R.id.cardNumberET);
        expDate=(EditText)findViewById(R.id.expireDateET);
        securityCode=(EditText)findViewById(R.id.securityCodeET);

        RadioButton rdCredit = (RadioButton)findViewById(R.id.rdCredit);
        RadioButton rdDebit =(RadioButton)findViewById(R.id.rdDebit);
    }

    @Override
    public void onClick (View v) {
        if (v.getId()==R.id.btnConfirmRV){

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

            if (getName.equals("")|| getPkLotName.equals("")|| getkAddress.equals("")|| getFrom.equals("")|| getTo.equals("") || getCardNumber.equals("")|| getExpireDate.equals("")||getSecurity.equals(""))
            {
                Toast.makeText(this,"Fields are Required",Toast.LENGTH_LONG).show();
                return;
            }else {

                myParkingDatabase.execSQL("Insert Into Reservation(name,lotname,parkingAddress,timeFrom,timeTo,cardNumbers,expiryDates,securityCodes)VALUES('" +getName+ "','" +getPkLotName+ "','" +getkAddress+ "','"+getFrom+"','"+getTo+"','"+getCardNumber+"','"+getExpireDate+"','"+getSecurity+"'); ");
                //sqLiteDatabase.execSQL("Insert Into Reg(name, phone, address)VALUES('" + _name + "','" + _phone + "','" + _address + "'); ");
                Toast.makeText(this,"Record Saved ",Toast.LENGTH_LONG).show();

                startActivity(intentNext);

                return;

            }

        }

        if (v.getId()==R.id.btnClearRV){
            EditText custNameET= (EditText)findViewById(R.id.customerNameET);
            EditText pkLotName=(EditText)findViewById(R.id.parkingNameET);
            EditText pkAddress=(EditText)findViewById(R.id.parkingLotAddressET);
            EditText from=(EditText)findViewById(R.id.fromET);
            EditText to=(EditText)findViewById(R.id.toET);
            EditText cardNumber=(EditText)findViewById(R.id.cardNumberET);
            EditText expDate=(EditText)findViewById(R.id.expireDateET);
            EditText securityCode=(EditText)findViewById(R.id.securityCodeET);

            EditText[] editTexts = {custNameET,pkLotName,pkAddress,from,to,cardNumber,expDate,securityCode};

            for (EditText et:editTexts
                    ) {
                et.setText("");
            }
        }
    }
}

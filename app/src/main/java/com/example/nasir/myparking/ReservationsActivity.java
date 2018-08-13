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
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import com.example.nasir.myparking.Database.DBHelper;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.example.nasir.myparking.Database.DataSource;


public class ReservationsActivity extends AppCompatActivity {

    EditText custNameET,pkLotName,pkAddress,timeFrom,timeTo,cardNumber,expDate,securityCode;
    String getName,getPkLotName,getkAddress,getFrom,getTo,getCardNumber,getExpireDate,getSecurity, cardTyps;
    Button btnSaves, btnClears,btnView;
    Date RTimeFrom, RTimeTo, result;
    RadioGroup rdGroup;
    RadioButton rdCredit,rdDebit;

    DBHelper myDb;
    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservations);

        myDb=new DBHelper(this);

        btnSaves=(Button)findViewById(R.id.btnConfirmRV);
        btnClears=(Button)findViewById(R.id.btnClearRV);
        btnView =(Button)findViewById(R.id.btnAll);

        custNameET= (EditText)findViewById(R.id.customerNameET);

        pkLotName=(EditText)findViewById(R.id.parkingNameET);
        pkLotName.setInputType(InputType.TYPE_CLASS_TEXT);
        pkAddress=(EditText)findViewById(R.id.parkingLotAddressET);
        timeFrom=(EditText)findViewById(R.id.fromET);
        timeTo=(EditText)findViewById(R.id.toET);
        cardNumber=(EditText)findViewById(R.id.cardNumberET);
        expDate=(EditText)findViewById(R.id.expireDateET);
        securityCode=(EditText)findViewById(R.id.securityCodeET);

        rdGroup=(RadioGroup)findViewById(R.id.rdgGenders);



    }


}

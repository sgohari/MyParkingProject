package com.example.nasir.myparking;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class ReservationsActivity extends AppCompatActivity {

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservations);


    }

    public void confirm_Onclick (View view) {

        EditText custNameET= (EditText)findViewById(R.id.customerNameET);
        EditText pkLotName=(EditText)findViewById(R.id.parkingNameET);
        EditText pkAddress=(EditText)findViewById(R.id.parkingLotAddressET);
        EditText from=(EditText)findViewById(R.id.fromET);
        EditText to=(EditText)findViewById(R.id.toET);
        EditText cardNumber=(EditText)findViewById(R.id.cardNumberET);
        EditText expDate=(EditText)findViewById(R.id.expireDateET);
        EditText securityCode=(EditText)findViewById(R.id.securityCodeET);

        RadioButton rdCredit = (RadioButton)findViewById(R.id.rdCredit);
        RadioButton rdDebit =(RadioButton)findViewById(R.id.rdDebit);

        Intent intentNext = new Intent(ReservationsActivity.this, ReceiptActivity.class);

        intentNext.putExtra("Name", custNameET.getText().toString());
        intentNext.putExtra("parkingLotName", pkLotName.getText().toString());
        intentNext.putExtra("parkingAddress", pkAddress.getText().toString());


        String getName = custNameET.getText().toString();
        String getPkLotName=pkLotName.getText().toString();
        String getkAddress=pkAddress.getText().toString();
        String getFrom=from.getText().toString();
        String getTo=to.getText().toString();
        String getCardNumber=cardNumber.getText().toString();
        String getExpireDate=expDate.getText().toString();
        String getSecurity=securityCode.getText().toString();


        if(TextUtils.isEmpty(getName)){

            Toast.makeText(ReservationsActivity.this, "EditText is Empty", Toast.LENGTH_LONG).show();
            custNameET.setFocusable(true);
        }
        else if (TextUtils.isEmpty(getPkLotName))
        {
            Toast.makeText(ReservationsActivity.this, "Type Parking Lot Name", Toast.LENGTH_LONG).show();
            pkLotName.setFocusable(true);
        }

        else if (TextUtils.isEmpty(getkAddress))
        {
            Toast.makeText(ReservationsActivity.this, "Type the Address of Lot", Toast.LENGTH_LONG).show();
            pkAddress.setFocusable(true);
        }

        else if (TextUtils.isEmpty(getFrom))
        {
            Toast.makeText(ReservationsActivity.this, "Type Timing", Toast.LENGTH_LONG).show();
            from.setFocusable(true);
        }

        else if (TextUtils.isEmpty(getTo))
        {
            Toast.makeText(ReservationsActivity.this, "Type Timing", Toast.LENGTH_LONG).show();
            to.setFocusable(true);
        }

        else if (TextUtils.isEmpty(getCardNumber))
        {
            Toast.makeText(ReservationsActivity.this, "Type Credit Card Number", Toast.LENGTH_LONG).show();
            cardNumber.setFocusable(true);
        }

        else if (TextUtils.isEmpty(getExpireDate))
        {
            Toast.makeText(ReservationsActivity.this, "Type Expire Date", Toast.LENGTH_LONG).show();
            expDate.setFocusable(true);
        }

        else if (TextUtils.isEmpty(getSecurity))
        {
            Toast.makeText(ReservationsActivity.this, "Type Security Code", Toast.LENGTH_LONG).show();
            securityCode.setFocusable(true);
        }
        else {

            startActivity(intentNext);
        }

    }
}

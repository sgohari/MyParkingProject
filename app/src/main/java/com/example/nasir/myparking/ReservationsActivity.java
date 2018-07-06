package com.example.nasir.myparking;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

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

        Intent intentNext = new Intent(ReservationsActivity.this,ReceiptActivity.class);

        intentNext.putExtra("Name",custNameET.getText().toString());
        intentNext.putExtra("parkingLotName",pkLotName.getText().toString());
        intentNext.putExtra("parkingAddress",pkAddress.getText().toString());

        startActivity(intentNext);

    }
}

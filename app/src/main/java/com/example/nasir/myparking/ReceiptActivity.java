package com.example.nasir.myparking;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Random;

public class ReceiptActivity extends AppCompatActivity {

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receipt);


        Random random= new Random();

        int number=random.nextInt(10000)+1000000;

        TextView display=(TextView)findViewById(R.id.displayTxt);
        String myString=String.valueOf(number);
        display.setText("Customer Name: "+"\n\n"+"Receipt ID"+"\t\t\t\t"+myString+"\n\n"+"Parking Lot Name:"+"\n\n"+"Parking Lot Address:");
    }
}

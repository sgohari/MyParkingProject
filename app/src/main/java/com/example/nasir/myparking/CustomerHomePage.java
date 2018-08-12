package com.example.nasir.myparking;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CustomerHomePage extends AppCompatActivity {

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_home_page);

        Button btnAroundMe=(Button)findViewById(R.id.btnAroundMe);
        btnAroundMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                startActivity(new Intent(CustomerHomePage.this,NearByParkingsActivity.class));
            }
        });

        Button btnParkingAtDestination=(Button)findViewById(R.id.btnAtDestination);
        btnParkingAtDestination.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                startActivity(new Intent(CustomerHomePage.this,DestinationActivity.class));
            }
        });

        Button btnBk = (Button)findViewById(R.id.btnBacks);
        btnBk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                startActivity(new Intent(CustomerHomePage.this,Login.class));
            }
        });
    }
}

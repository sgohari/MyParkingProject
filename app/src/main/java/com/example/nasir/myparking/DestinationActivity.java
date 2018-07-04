package com.example.nasir.myparking;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class DestinationActivity extends AppCompatActivity {

    private EditText streetName;
    private EditText city;
    private EditText postalCode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_destination);
        streetName = findViewById(R.id.streetET);
        city=findViewById(R.id.cityET);
        postalCode=findViewById(R.id.postalCodeET);
    }

    public void findDestination_OnClick(View view) {

        String street_Name = streetName.getText().toString();
        String city_Name = city.getText().toString();
        String postal_code = postalCode.getText().toString();

        String address = street_Name +" "+city_Name+" Ontario Canada "+postal_code;

        Intent intent = new Intent(this,DestinationMapsActivity.class);
        intent.putExtra("address",address);
        startActivity(intent);

    }
}

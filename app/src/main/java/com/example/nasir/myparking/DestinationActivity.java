package com.example.nasir.myparking;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
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

        Spinner spinner = (Spinner) findViewById(R.id.provincesSP);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.provinces, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);
    }

    public void findDestination_OnClick(View view) {

        if (streetName.getText().toString().isEmpty()) {
            streetName.setError("Enter a street name");
            return;

        }
        if (city.getText().toString().isEmpty()) {
            city.setError("Enter a city name");
            return;
        }
        if (postalCode.getText().toString().isEmpty()) {
            postalCode.setError("Enter a postal code");
            return;
        }
        
        String street_Name = streetName.getText().toString();
        String city_Name = city.getText().toString();
        String postal_code = postalCode.getText().toString();

        String address = street_Name +" "+city_Name+" Ontario Canada "+postal_code;

        Intent intent = new Intent(DestinationActivity.this,DestinationMapsActivity.class);
        intent.putExtra("address",address);
        startActivity(intent);

    }

    public void clearAddress_OnClick(View view) {
        streetName.setText("");
        city.setText("");
        postalCode.setText("");
    }
}

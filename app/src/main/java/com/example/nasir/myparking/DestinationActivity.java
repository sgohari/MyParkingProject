package com.example.nasir.myparking;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DestinationActivity extends AppCompatActivity {

    private EditText streetName;
    private EditText city;
    private EditText postalCode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_destination);

        streetName = findViewById(R.id.streetET);
        city = findViewById(R.id.cityET);
        postalCode = findViewById(R.id.postalCodeET);

        Spinner spinner = (Spinner) findViewById(R.id.provincesSP);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.provinces
                , android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        //Street name validation
        streetName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (!(isNumeric(streetName.getText().toString())) && !(TextUtils.isEmpty(streetName.getText()))) {
                    streetName.setError("Enter a valid street number");
                }
            }
        });

        //City validation
        city.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if ((isNumeric(city.getText().toString())) && !(TextUtils.isEmpty(city.getText()))) {
                    city.setError("Enter a valid city name");
                }
            }
        });

        //Postal code validation
        //Postal code regex
        String regex = "^(?!.*[DFIOQU])[A-VXY][0-9][A-Z] ?[0-9][A-Z][0-9]$";

        final Pattern pattern = Pattern.compile(regex);
        final Matcher[] matcher = new Matcher[1];

        postalCode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {


            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                matcher[0] = pattern.matcher(charSequence);
            }

            @Override
            public void afterTextChanged(Editable editable) {
              boolean postalCodeIsValid = matcher[0].matches();
                if (!(postalCodeIsValid))
                        {
                            postalCode.setError("Enter valid postal code ");
                        }
            }
        });

    }



    //Chech is numeric
    public boolean isNumeric(String str)
    {
        try
        {
            double d = Double.parseDouble(str);
        }
        catch(NumberFormatException nfe)
        {
            return false;
        }
        return true;
    }

    public void findDestination_OnClick(View view) {

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

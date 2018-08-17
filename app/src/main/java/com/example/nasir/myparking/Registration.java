package com.example.nasir.myparking;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TabHost;
import android.widget.Toast;

import com.example.nasir.myparking.Database.DBHelper;
import com.example.nasir.myparking.Database.DataSource;

import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
Author: Jason Nguessan
Date: 2018/08/16
Description: Validated Registration Page
 */

public class Registration extends AppCompatActivity {
    //Call Database
    DataSource myDB;
    //Initialized Button
    Button registerRG;
    //Initialized Edit Text
    EditText UserName, Password, FirstName, LastName, Address, City, Postal_Code;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        myDB = new DataSource(this);
        UserName = (EditText) findViewById(R.id.editUserName);
        Password = (EditText) findViewById(R.id.editPassword);
        FirstName = (EditText) findViewById(R.id.editFName);
        LastName = (EditText) findViewById(R.id.editLName);
        Address = (EditText) findViewById(R.id.editAddress);
        City = (EditText) findViewById(R.id.editCity);
        Postal_Code = (EditText) findViewById(R.id.editPostCode);

        //Initialized Button
        registerRG = (Button) findViewById(R.id.btnSave);
        fieldsValidation_onClick();

    }


    public void registerUser_OnClick (View view) {
        //City and Postal Code key Variables
        String _city = City.getText().toString();
        String _address = Address.getText().toString();

        String _cityJson = null;
        List<String> pc = new ArrayList<String>();
        List<String> ad = new ArrayList<String>();

        pc.add(Postal_Code.getText().toString());
        ad.add(Address.getText().toString());

        //Postal Code Validation Regex
        String regex = "[ABCEGHJKLMNPRSTVXY][0-9][ABCEGHJKLMNPRSTVWXYZ][0-9][ABCEGHJKLMNPRSTVWXYZ][0-9]";
        Boolean postalCode_bool = false;
        Pattern pattern = Pattern.compile(regex);

        //Address Validation Regex
        String regex2 = "^\\s+[0-9]+ ?[A-Za-z\\s]+$";
        Pattern pattern2 = Pattern.compile(regex2); //Allow White Space afterwardsx
        boolean address_bool = false;

        for (String address : ad )
        {
            Matcher matcher = pattern2.matcher(address);
            if(matcher.matches())
            {
                address_bool = true;
            }
            else
            {
                address_bool = false;
                ad.clear();

            }
        }

        for (String postalCodes : pc )
        {
            Matcher matcher = pattern.matcher(postalCodes);
            if(matcher.matches())
            {
                postalCode_bool = true;
            }
            else
                {
                    postalCode_bool = false;
                    pc.clear();

                }
        }

        //Simple City checker ~ datamined using python
        try{
            //Create JSON Object
            JSONObject obj = new JSONObject(loadJSONFromAsset(this));
            //Search for North key under the Canada Name to get Value
             _cityJson = obj.getJSONObject("North").getString("Canada");

        }

        catch(Exception e)
        {
            Log.i("error", "something went wrong");
        }

        if (UserName.length()==0 || UserName.length()>4 && UserName.length()<4){
            UserName.setError("UserName is Required");
            if (UserName.length()>4 && UserName.length()<4)
            {
                UserName.setError("Only 4 Digit UserName");

            }
        }

        else if (Password.length()==0 || Password.length()>9){
            Password.setError("Password is Required");
            if (Password.length()>9)
            {
                Password.setError("Only 8 character or number");

            }
        }


        else if (FirstName.length()==0 || LastName.length()==0){
            FirstName.setError("First Name is Required");
            if (LastName.length()==0){
                LastName.setError("Last Name is Required");

            }}


        else if (Address.length() < 2 || address_bool == false )
        {
            Address.setError("Address Name is Required e.g 1 Deauville Lane");
        }


         else if (!_cityJson.contains(City.getText().toString()) || _city.length() < 6)
         {
                City.setError("Try another City name. e.g North York, Alberta (First Letter Capitalized)");

          }
          else if (postalCode_bool == false)
            {
                Postal_Code.setError("Enter a proper Canadian Postal Code. e.g M3C1Z6 ");

            }

        else {
            //create instance
            String _username = UserName.getText().toString();
            String _password = Password.getText().toString();
            String _fname = FirstName.getText().toString();
            String _lname = LastName.getText().toString();
            String _postalcode = Postal_Code.getText().toString();

            int username = Integer.parseInt(_username);


            //insert student after registering;
            try{
                myDB.open();
                myDB.insertRegistration(username, _password, _fname, _lname, _address, _city, _postalcode);
                Toast.makeText(Registration.this, "Data Inserted", Toast.LENGTH_LONG).show();
                myDB.close();
            }catch(Exception e)
            {
                Toast.makeText(this, "Something went absolutely wrong, try refreshing page!", Toast.LENGTH_SHORT).show();
            }


            startActivity(new Intent(this, Login.class));
            //pass username to Student page
        }
    }


    public String loadJSONFromAsset(Context context) {
        String json = null;
        try {
            InputStream city = context.getAssets().open("countriesToCities.json");

            int size = city.available();

            byte[] buffer = new byte[size];
            city.read(buffer);
            city.close();

            json = new String(buffer, "UTF-8");

        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;

    }

    public void fieldsValidation_onClick(){

    }
    //simple concatenation test for JUnit
    public String concatenate( String one, String two){
        return one+two;
    }
}

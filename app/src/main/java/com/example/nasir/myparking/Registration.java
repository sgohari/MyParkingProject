package com.example.nasir.myparking;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TabHost;
import android.widget.Toast;

import com.example.nasir.myparking.Database.DBHelper;
import com.example.nasir.myparking.Database.DataSource;

public class Registration extends AppCompatActivity {
    //Call Database
    DataSource myDB;
    //Initialized Button
    Button registerRG;
    //Initialized Edit Text
    EditText UserName,Password,FirstName,LastName,Address,City,Postal_Code;
    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        myDB=new DataSource(this);
        UserName = (EditText)findViewById(R.id.editUserName);
        Password = (EditText)findViewById(R.id.editPassword);
        FirstName = (EditText)findViewById(R.id.editFName);
        LastName = (EditText)findViewById(R.id.editLName);
        Address = (EditText)findViewById(R.id.editAddress);
        City = (EditText)findViewById(R.id.editCity);
        Postal_Code = (EditText)findViewById(R.id.editPostCode);

        //Initialized Button
        registerRG = (Button)findViewById(R.id.btnSave);

    }


    public void registerUser_OnClick(View view) {
        //create instance
        String _username = UserName.getText().toString();
        String _password = Password.getText().toString();
        String _fname = FirstName.getText().toString();
        String _lname = LastName.getText().toString();
        String _address = Address.getText().toString();
        String _city = City.getText().toString();
        String _postalcode = Postal_Code.getText().toString();

        int username =Integer.parseInt(_username);


        //insert student after registering;
        myDB.open();
        myDB.insertRegistration(username,_password,_fname,_lname,_address,_city,_postalcode);
        Toast.makeText(Registration.this,"Data Inserted", Toast.LENGTH_LONG).show();
        myDB.close();

        startActivity(new Intent(this,Login.class));
        //pass username to Student page
    }


    }


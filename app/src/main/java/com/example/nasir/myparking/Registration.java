package com.example.nasir.myparking;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Registration extends AppCompatActivity {
    //Call Database
    DBHelper myDB;
    //Initialized Button
    Button registerRG;
    //Initialized Edit Text
    EditText UserName,Password,FirstName,LastName,Address,City,Postal_Code;
    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        UserName = (EditText)findViewById(R.id.editUserName);
        Password = (EditText)findViewById(R.id.editPassword);
        FirstName = (EditText)findViewById(R.id.editFName);
        LastName = (EditText)findViewById(R.id.editLName);
        Address = (EditText)findViewById(R.id.editAddress);
        City = (EditText)findViewById(R.id.editCity);
        Postal_Code = (EditText)findViewById(R.id.editPostCode);
        
        //Initialized Button
        registerRG = (Button)findViewById(R.id.btnSave);

        //Initialized Database
        myDB = new DBHelper(this);

        //Called AddData
       registerRG.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick (View v) {
               boolean inserted = myDB.insertDataRegistration(UserName.getText().toString(), Password.getText().toString(), FirstName.getText().toString(), LastName.getText().toString(), Address.getText().toString(), City.getText().toString(), Postal_Code.getText().toString());
               if (inserted == true) {

                   EditText[] editTexts = {UserName, Password, FirstName, LastName, Address, City, Postal_Code};
                   for (EditText et : editTexts) {
                       if (et.getText().toString().equals("")) {
                           Toast.makeText(Registration.this, "Fields are required", Toast.LENGTH_LONG).show();
                           return;
                       }
                   }
                   Toast.makeText(Registration.this, "Record Added to DB", Toast.LENGTH_LONG).show();

                   startActivity(new Intent(Registration.this, Login.class));
               }
           }
       });
    }


    private void ToastMessage(String message){
        Toast.makeText(Registration.this,message, Toast.LENGTH_LONG).show();
    }

}

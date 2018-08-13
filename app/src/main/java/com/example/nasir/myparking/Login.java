package com.example.nasir.myparking;
/*
 * Author: Syed Nasir Gohary
 * Date: 2018/08/06
 *Subject: Comp231
 * Project Name: myParking
 * */
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.service.autofill.FillEventHistory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.nasir.myparking.Database.DBHelper;
import com.example.nasir.myparking.Database.DataSource;
public class Login extends AppCompatActivity {


    DataSource myDatabaseHelper;
    Button btnLogin, btnRegister;
    EditText edtUserName, edtPassword;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtUserName = (EditText) findViewById(R.id.userNameET);
        edtPassword = (EditText) findViewById(R.id.passwordET);

        myDatabaseHelper = new DataSource(this);

        btnRegister = (Button) findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                startActivity(new Intent(Login.this, Registration.class));
            }
        });

    }

    public void btnclick () {

        final String user = edtUserName.getText().toString();
        final String pass = edtPassword.getText().toString();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {


                if (edtUserName.getText().toString().equals("")) {
                    Toast.makeText(Login.this, "Type a User Name!!", Toast.LENGTH_LONG).show();

                }
                else if (edtPassword.getText().toString().isEmpty()) {
                    Toast.makeText(Login.this, "Type your password", Toast.LENGTH_LONG).show();
                }
                //checking from database
                else if(validate(user,pass))
                {
                    //view student activity
                    Toast.makeText(Login.this, "valid", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Login.this,CustomerHomePage.class));
                }
                else if (user.equals("admin") || pass.equals("password")) {
                    startActivity(new Intent(Login.this, AdminHomepage.class));
                }
                else
                {
                    //throw error
                    Toast.makeText(Login.this, "user-name and password does not exist", Toast.LENGTH_SHORT).show();

                }
            }
        });



    }
    //validate credentials
    public boolean validate(String username, String password) {
        myDatabaseHelper.open();
        Cursor c = myDatabaseHelper.validateUserPassword(Integer.parseInt(username));
        myDatabaseHelper.close();
        if (c.moveToFirst())
        {
            return c.getString(0).equals(password);
        }
        return false;
    }
}




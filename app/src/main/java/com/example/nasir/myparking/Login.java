package com.example.nasir.myparking;
/*
 * Author: Syed Nasir Gohary
 * Date: 2018/08/06
 *Subject: Comp231
 * Project Name: myparking
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

import static com.example.nasir.myparking.DBHelper.COLUMN_PASSWORD;
import static com.example.nasir.myparking.DBHelper.COLUMN_USERNAME;
import static com.example.nasir.myparking.DBHelper.REGISTRATION_TABLE;

public class Login extends AppCompatActivity {


    DBHelper myDatabaseHelper;
    Button btnLogin, btnRegister;
    EditText edtUserName, edtPassword;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtUserName = (EditText) findViewById(R.id.userNameET);
        edtPassword = (EditText) findViewById(R.id.passwordET);


        myDatabaseHelper = new DBHelper(getApplicationContext());


        btnRegister = (Button) findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                startActivity(new Intent(Login.this, Registration.class));
            }
        });

    }

    public void btnclick (View view) {

        final String user = edtUserName.getText().toString();
        final String pass = edtPassword.getText().toString();

        SharedPreferences userLogin= getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = userLogin.edit();
        editor.putString("username",edtUserName.getText().toString());
        editor.apply();

        final String currentuser = myDatabaseHelper.SearchExistingAccount(user);


        if (edtUserName.getText().toString().equals("")) {
            edtUserName.setError("Please Enter Username");

        } else if (edtPassword.getText().toString().isEmpty()) {
            edtPassword.setError("Please Enter Password");

        } else if (pass.equals(currentuser)) {
            Toast.makeText(Login.this, "Login Successfuly", Toast.LENGTH_LONG).show();
            startActivity(new Intent(Login.this, CustomerHomePage.class));
        } else if (edtUserName.getText().toString().equals("admin") || edtPassword.getText().toString().equals("password")) {
            startActivity(new Intent(Login.this, AdminHomepage.class));

        } else {

            Toast.makeText(Login.this, "Account Does not Exist" + currentuser, Toast.LENGTH_LONG).show();
        }
    }

}




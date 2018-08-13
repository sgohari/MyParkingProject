package com.example.nasir.myparking;
/*
 * Author: Syed Nasir Gohary
 * Date: 2018/08/06
 *Subject: Comp231
 * Project Name: myparking
 * */
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.nasir.myparking.Database.DataSource;


public class Login extends AppCompatActivity {

    //Shared preference
    private static final String STUDENT_USERNAME_PREFS = "student_username_prefs";

    DataSource myDB;
    Button btnLogin, btnRegister;
    EditText usernameET,passwordET;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Database
        myDB = new DataSource(this);

    }

    public void login_OnClick (View view) {

         String username = usernameET.getText().toString();
         String password = passwordET.getText().toString();

        if(validate(username, password))
        {
            SharedPreferences.Editor editor =
                    getSharedPreferences(STUDENT_USERNAME_PREFS, MODE_PRIVATE).edit();
            editor.putString("username_key",username);
            editor.apply();

            //view student activity
            Toast.makeText(this, "valid", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this,ReservationsActivity.class);
            intent.putExtra("username",Integer.parseInt(username));
            startActivity(intent);
        }
        else
        {
            //throw error
            Toast.makeText(this, "invalid", Toast.LENGTH_SHORT).show();
            passwordET.setFocusable(true);
            passwordET.setError("Password and/or username is wrong...");
        }
    }
    //validate credentials
    public boolean validate(String username, String password) {
        myDB.open();
        Cursor c = myDB.validateUserPassword(Integer.parseInt(username));
        myDB.close();
        return c.moveToFirst() && c.getString(0).equals(password);
    }
}




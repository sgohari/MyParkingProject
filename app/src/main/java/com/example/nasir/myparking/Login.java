package com.example.nasir.myparking;
/*
 * Author: Syed Nasir Gohary
 * Date: 2018/08/06
 *Subject: Comp231
 * Project Name: myParking
 * */
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    Button btnLogin, btnRegister;
    EditText edtUserName, edtPassword;
    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtUserName=(EditText)findViewById(R.id.userNameET);
        edtPassword=(EditText)findViewById(R.id.passwordET);

       btnLogin =(Button)findViewById(R.id.btnLogin);
       btnLogin.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick (View v) {

               String userName = edtUserName.getText().toString();
               String pass =edtPassword.getText().toString();

               if (userName.equals("customer") || pass.equals("password")){
                   startActivity(new Intent(Login.this,CustomerHomePage.class));
               }
               if (userName.equals("admin") || pass.equals("password")){
                   startActivity(new Intent(Login.this,AdminHomepage.class));

               }

               else {
                   Toast.makeText(Login.this,"User and password does not exist",Toast.LENGTH_LONG).show();
               }
           }
       });
    }
}

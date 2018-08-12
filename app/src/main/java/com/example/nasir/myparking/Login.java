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


    DBHelper myDatabaseHelper;
    Button btnLogin, btnRegister;
    EditText edtUserName, edtPassword;
    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtUserName=(EditText)findViewById(R.id.userNameET);
        edtPassword=(EditText)findViewById(R.id.passwordET);
        myDatabaseHelper=new DBHelper(this);

       btnLogin =(Button)findViewById(R.id.btnLogin);
       btnRegister=(Button)findViewById(R.id.btnRegister);
       btnRegister.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick (View v) {
               startActivity(new Intent(Login.this,Registration.class));
           }
       });

       btnLogin.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick (View v) {
               String user = edtUserName.getText().toString();
               String pass = edtPassword.getText().toString();

               //String currentUser = myDatabaseHelper.SearchExistingAccount(user);

               if (user.equals("customer")|| pass.equals("pass")){
                   Toast.makeText(Login.this, "Login Successfuly", Toast.LENGTH_LONG).show();
                   startActivity(new Intent(Login.this,CustomerHomePage.class));
               }
               if (user.equals("admin")||pass.equals("password")){
                   startActivity(new Intent(Login.this,AdminHomepage.class));

               }
               else{
                   Toast.makeText(Login.this, "Account Does not Exist", Toast.LENGTH_LONG).show();
               }
           }
       });
    }
}

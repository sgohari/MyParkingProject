package com.example.nasir.myparking;
/*
* Author: Syed Nasir Gohary
* Date: 2018/08/06
*Subject: Comp231
* Project Name: myParking
* */
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AdminHomepage extends AppCompatActivity {

    DBHelper myDB;

    EditText id,custNameAdminET,pkLotNameAdminET,pkLotAddressAdminET,timingFromAdminET,timingToAdminET,cardNumberAdminET,expiryDateAdminET,securityCodeAdminET;
    Button btnAdd, btnView,btnUpdate,btnDelete,btnSearch;
    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_homepage);

        myDB = new DBHelper(this);

        custNameAdminET=(EditText)findViewById(R.id.custNameAdminET);
        pkLotNameAdminET=(EditText)findViewById(R.id.pkLotNameAdminET);
        pkLotAddressAdminET=(EditText)findViewById(R.id.pkLotAddressAdminET);
        timingFromAdminET=(EditText)findViewById(R.id.timingFromAdminET);
        timingToAdminET=(EditText)findViewById(R.id.timingToAdminET);
        cardNumberAdminET=(EditText)findViewById(R.id.cardNumberAdminET);
        expiryDateAdminET=(EditText)findViewById(R.id.expireDateET);
        securityCodeAdminET=(EditText)findViewById(R.id.securityCodeAdminET);
        id=(EditText)findViewById(R.id.idET);
        btnAdd=(Button)findViewById(R.id.btnAdd);

        btnView = (Button)findViewById(R.id.btnViewAll);
        btnUpdate=(Button)findViewById(R.id.btnUpdate);
        btnDelete=(Button)findViewById(R.id.btnDelete);
        btnSearch=(Button)findViewById(R.id.btnSearchAdmin);

        addData();
        viewAll();
        updateData();
        deleteData();
    }

    public void updateData(){

       /* btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {

                boolean isUpdated=myDB.updateData(id.getText().toString(),
                        names.getText().toString(),address.getText().toString(),marks.getText().toString());

                if (isUpdated==true){
                    Toast.makeText(AdminHomepage.this,"Data is updated",Toast.LENGTH_LONG).show();

                }else {
                    Toast.makeText(AdminHomepage.this,"Data is not update",Toast.LENGTH_LONG).show();

                }
            }
        });
*/
    }
    public void deleteData(){

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                Integer deletedRows=myDB.deleteData(id.getText().toString());

                if (deletedRows>0){
                    Toast.makeText(AdminHomepage.this,"Data is Deleted",Toast.LENGTH_LONG).show();
                    id.setText("");

                }else {
                    Toast.makeText(AdminHomepage.this,"Data is not Deleted",Toast.LENGTH_LONG).show();

                }
            }
        });
    }
    public void addData(){

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {

                /*boolean isInserted= myDB.insertData(names.getText().toString(),address.getText().toString(),marks.getText().toString());

                if (isInserted==true){
                    Toast.makeText(AdminHomepage.this,"Data is Inserted",Toast.LENGTH_LONG).show();
                    EditText[] editTexts = {names,address,marks,id};

                    for (EditText et:editTexts
                            ) {
                        et.setText("");
                    }

                }else {
                    Toast.makeText(AdminHomepage.this,"Data is not Inserted",Toast.LENGTH_LONG).show();

                }*/

            }
        });
    }

    public void viewAll(){


        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                Cursor res= myDB.getAllData();

                if (res.getCount()==0){
                    showMessages("Error","Nothing is found");

                    return;
                }else {

                    StringBuffer buffer = new StringBuffer();
                    while (res.moveToNext()){
                        buffer.append("id :"+res.getString(0)+"\n");
                        buffer.append("Name"+res.getString(1)+"\n");
                        buffer.append("Parking Lot Name: "+res.getString(2)+"\n");
                        buffer.append("Parking lot Address: "+res.getString(3)+"\n");

                    }

                    //show all

                    showMessages("Data",buffer.toString());
                }
            }
        });
    }


    public void showMessages(String title, String message){

        AlertDialog.Builder builder= new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
}

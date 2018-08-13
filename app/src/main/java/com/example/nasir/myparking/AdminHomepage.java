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
import com.example.nasir.myparking.Database.DBHelper;
import com.example.nasir.myparking.Database.DataSource;

public class AdminHomepage extends AppCompatActivity {

    DataSource myDB;

    EditText id,custNameAdminET,pkLotNameAdminET,pkLotAddressAdminET,timingFromAdminET,timingToAdminET,cardNumberAdminET,expiryDateAdminET,securityCodeAdminET;
    Button btnAdd, btnView,btnUpdate,btnDelete,btnSearch;
    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_homepage);

        myDB = new DataSource(this);

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

    /*public void viewAll(){


        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                Cursor res= myDB();

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
    }*/


    //Search Reservation
    public void searchReservation_OnClick(View view) {
        int pk_reservationID = 0;


        myDB.open();
        Cursor c = myDB.getReservation(pk_reservationID); if (c.moveToFirst())
        {
            //Get reservation by ID
            String reservationlInfo = "ID: "+c.getInt(0)+"\nParking name: "+c.getString(1)+"\nParking address "
                    +c.getString(2)+"\nFrom: "+c.getString(3)+"\nTo: "+c.getString(4);
            //Set text = reservationInfo
        }
        myDB.close();
    }

    //Update Reservation
    public void updateReservation_OnClick(View view) {
        //Assign value
        int pk_reservationID = 0;
        String timeFrom = null;
        String timeTo = null;

        myDB.open();
        myDB.updateReservation(pk_reservationID,timeFrom,timeTo);
        myDB.close();
    }

    //Delete Reservation
    public void deleteReservation_OnClick(View view) {
        //Assign ID value
        int pk_reservationID = 0;

        myDB.open();
        myDB.deleteReservation(pk_reservationID);
        myDB.close();
    }

    //View All Reservations
    public void viewAllReservations_OnClick(View view) {

    }

    //Add reservation
    public void addReservation_OnClick(View view) {

        //Assign EditTexts to corresponding variables
        int fk_userID = 0;  //Convert to Integer
        String parkingName = null;
        String parkingAddress = null;
        String timeFrom = null;
        String timeTo = null;
        String cardNumber = null;
        String expiryDate = null;
        String CVV = null;
        String cardType = null;

        //Open and insert
        myDB.open();
        myDB.insertReservation(fk_userID,parkingName,parkingAddress,timeFrom,timeTo,cardType,cardNumber,expiryDate,CVV);
        myDB.close();
    }
}

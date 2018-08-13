package com.example.nasir.myparking;
//Author
//date
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class ReservationsActivity extends AppCompatActivity {

    EditText custNameET,pkLotName,pkAddress,timeFrom,timeTo,cardNumber,expDate,securityCode;
    String getName,getPkLotName,getkAddress,getFrom,getTo,getCardNumber,getExpireDate,getSecurity, cardTyps;
    Button btnSaves, btnClears,btnView;
    Date RTimeFrom, RTimeTo, result;
    RadioGroup rdGroup;
    RadioButton rdCredit,rdDebit;

    DBHelper myDb;
    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservations);

        myDb=new DBHelper(this);

        btnSaves=(Button)findViewById(R.id.btnConfirmRV);
        btnClears=(Button)findViewById(R.id.btnClearRV);
        btnView =(Button)findViewById(R.id.btnAll);

        custNameET= (EditText)findViewById(R.id.customerNameET);

        pkLotName=(EditText)findViewById(R.id.parkingNameET);
        pkLotName.setInputType(InputType.TYPE_CLASS_TEXT);
        pkAddress=(EditText)findViewById(R.id.parkingLotAddressET);
        timeFrom=(EditText)findViewById(R.id.fromET);
        timeTo=(EditText)findViewById(R.id.toET);
        cardNumber=(EditText)findViewById(R.id.cardNumberET);
        expDate=(EditText)findViewById(R.id.expireDateET);
        securityCode=(EditText)findViewById(R.id.securityCodeET);

        rdGroup=(RadioGroup)findViewById(R.id.rdgGenders);



        insertingData();
        viewAllRecords();
        rdButton_View();

        displaySharedInfor();

    }

    public void insertingData(){

        btnSaves.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                Intent intentNext = new Intent(ReservationsActivity.this, ReceiptActivity.class);
                intentNext.putExtra("Name", custNameET.getText().toString());
                intentNext.putExtra("parkingLotName", pkLotName.getText().toString());
                intentNext.putExtra("parkingAddress", pkAddress.getText().toString());

                getName = custNameET.getText().toString();
                getPkLotName=pkLotName.getText().toString();
                getkAddress=pkAddress.getText().toString();
                getFrom=timeFrom.getText().toString();
                getTo=timeTo.getText().toString();
                getCardNumber=cardNumber.getText().toString();
                getExpireDate=expDate.getText().toString();
                getSecurity=securityCode.getText().toString();

                try
                {


                   //Set Time Format
                    DateFormat fmt = new SimpleDateFormat("HH:mm");

                    //Set Expired Date Format
                    DateFormat mmyy = new SimpleDateFormat("MM/yyyy");
                    DateFormat yy = new SimpleDateFormat("yyyy");
                    DateFormat mm = new SimpleDateFormat("MM");
                    mmyy.setLenient(false);

                    //Setting Current year, month
                    Calendar calendar = new GregorianCalendar();
                    int currentYear = calendar.get(Calendar.YEAR);
                    int currentMonth  = calendar.get(Calendar.MONTH) + 1;

                    //Full Result of expired Date
                    result = mmyy.parse(getExpireDate); // returns the full date month year day etc

                    //User inputed Year, month
                    String year = yy.format(result);
                    String month = mm.format(result);
                    // Full Result of time
                    RTimeFrom = fmt.parse(getFrom);
                    RTimeTo = fmt.parse(getTo);

                    //Checkers
                    Boolean yearDateCheck = false;
                    Boolean timeCheck = false;

                    if(RTimeTo.after(RTimeFrom))
                    {
                        timeCheck = true;

                    }
                    else
                        {
                            Toast.makeText(ReservationsActivity.this,"Enter a valid booking time ",Toast.LENGTH_LONG).show();

                        }

                    if (Integer.parseInt(year) <= currentYear ){

                    if (currentYear == Integer.parseInt(year) && yearDateCheck == false){
                        if (currentMonth + 1 > Integer.parseInt(month)){
                            Toast.makeText(ReservationsActivity.this,"Date must be in 'MM/yyyy and a month greater than " + currentMonth  ,Toast.LENGTH_LONG).show();
                            yearDateCheck = false;
                        }
                        else
                            {
                                yearDateCheck = true;
                            }

                    }
                    else if (Integer.parseInt(year) < currentYear && yearDateCheck && yearDateCheck )
                    {
                        yearDateCheck = false;
                        Toast.makeText(ReservationsActivity.this,"Date must be in 'MM/yyyy and greater than year " + year ,Toast.LENGTH_LONG).show();
                    }


                }
                else
                    {
                        yearDateCheck = true;
                    }

                    if (getName.equals("")|| getPkLotName.equals("")|| getkAddress.equals("")|| getFrom.equals("")|| getTo.equals("") || getCardNumber.equals("")|| getExpireDate.equals("")||getSecurity.equals("")
                         || yearDateCheck == false || timeCheck == false)
                {


                    if (cardNumber.getText().toString().length()>16){
                        Toast.makeText(ReservationsActivity.this,"card number Must be 16 digits",Toast.LENGTH_LONG).show();

                    }


                    else if (securityCode.getText().toString().length()>3){
                        Toast.makeText(ReservationsActivity.this,"security code must be 3 digits ",Toast.LENGTH_LONG).show();

                    }


                    Toast.makeText(ReservationsActivity.this,"Fields are Required",Toast.LENGTH_LONG).show();
                    expDate.setText(" ");
                    expDate.setHint("MM/YYYY");
                    timeFrom.setText("00:00");
                    timeTo.setText("00:00");



                    return;
                }
                    boolean isInserted = myDb.insertData(custNameET.getText().toString(),pkLotName.getText().toString(),pkAddress.getText().toString(),RTimeFrom.toString(),RTimeTo.toString(), cardNumber.getText().toString(),result.toString(),securityCode.getText().toString());
                    if (isInserted==true){

                        startActivity(intentNext);

                        Toast.makeText(ReservationsActivity.this,"Record Added to DB",Toast.LENGTH_LONG).show();

                    }else {
                        Toast.makeText(ReservationsActivity.this,"Record is not added to DB",Toast.LENGTH_LONG).show();
                    }

                }catch(Exception e)
                {
                    Toast.makeText(ReservationsActivity.this,"Something Went Absolutely Wrong, " +
                            "please follow the hints and make sure all fields are filled!!",Toast.LENGTH_LONG).show();
                } }

        }
        );
    }

    public void clear_onClick(View view){
        btnClears.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v)
            {
                EditText[] editTexts = {custNameET,pkLotName,pkAddress,timeFrom,timeTo,cardNumber,expDate,securityCode};

                for (EditText et:editTexts) {
                    et.setText("");
                }
            }
        });
    }

    //for checking the database.
    public void viewAllRecords(){

        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                Cursor res= myDb.getAllData();

                if (res.getCount()==0){
                    showMessages("Error","Nothing is found");

                    return;
                }else {

                    StringBuffer buffer = new StringBuffer();
                    while (res.moveToNext()){
                        buffer.append("id :"+res.getString(0)+"\n");
                        buffer.append("\t"+"Customer Name :"+res.getString(1)+"\n");
                        buffer.append("\t"+"Parking Lot Name :"+res.getString(2)+"\n");
                        buffer.append("\t"+"Parking Lot Address : "+res.getString(3)+"\n");
                        buffer.append("\t"+"Timing From : "+res.getString(4)+"\n");
                        buffer.append("\t"+"Timing To : "+res.getString(5)+"\n");
                        buffer.append("\t"+"Card Number : "+res.getString(6)+"\n");
                        buffer.append("\t"+"Exprity Date : "+res.getString(7)+"\n");
                        buffer.append("\t"+"Security Code : "+res.getString(8)+"\n");
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

public void rdButton_View(){
        rdGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged (RadioGroup group, int checkedId) {
                rdCredit = (RadioButton)findViewById(R.id.rdCredit);
                rdDebit =(RadioButton)findViewById(R.id.rdDebit);

                if (checkedId==R.id.rdCredit) {
                    cardTyps =rdCredit.getText().toString();
                }
                else if (checkedId==R.id.rdDebit){
                    cardTyps =rdDebit.getText().toString();
                }
            }
        });

}
    public void displaySharedInfor(){

        SharedPreferences sharedPreferences=getSharedPreferences("markerContent", Context.MODE_PRIVATE);
        String title = sharedPreferences.getString("title","");
        String snipped=sharedPreferences.getString("snipped","");
        pkLotName.setText(title);
        pkAddress.setText(snipped);
    }
}

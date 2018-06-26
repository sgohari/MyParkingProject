package com.example.nasir.myparking;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class NearByParkingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_near_by_parkings);

        //Initializing string array and Assigning the CollegeName array into it.
        ListView mainContentLsView = (ListView) findViewById(R.id.centennialCollegeLSV);
        final String[] CentennialCollege = getResources().getStringArray(R.array.centennial_college);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, CentennialCollege);
        mainContentLsView.setAdapter(adapter);

        mainContentLsView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick (AdapterView<?> parent, View view, int position, long id) {

                switch (position)
                {
                    case 0:

                        startActivity(new Intent(NearByParkingsActivity.this,ParkingAroundActivity.class));
                        break;
                    case 1:
                        startActivity(new Intent(NearByParkingsActivity.this,ParkingAroundActivity.class));
                        break;
                    case 2:
                        startActivity(new Intent(NearByParkingsActivity.this,ParkingAroundActivity.class));
                        break;
                    case 3:
                        startActivity(new Intent(NearByParkingsActivity.this,ParkingAroundActivity.class));
                        break;
                }
            }
        });
    }
}

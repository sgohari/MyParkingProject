package com.example.nasir.myparking;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class DestinationMapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_destination_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        //Available Parking-spots
        final LatLng downtown = new LatLng(43.648188, -79.392968);
        final LatLng richmond_hill = new LatLng(43.835833, -79.413896);
        final LatLng pickering = new LatLng(43.837087, -79.086135);
        final LatLng sheppard = new LatLng(43.795130,-79.281837);

        //Address input by the user
        String inputAddress = getIntent().getStringExtra("address");

        Geocoder geoCoder = new Geocoder(
                getBaseContext(), Locale.getDefault());
        try {List<Address> addresses = geoCoder.getFromLocationName(
                inputAddress, 5);
            if (addresses.size() > 0) {
                LatLng destinedAddress = new LatLng(
                        (addresses.get(0).getLatitude()),
                        (addresses.get(0).getLongitude()));

                //Check for null value
                try {
                    LatLng NEAREST_PARKING_LOT = NearestParking(destinedAddress);
                    double LATITUDE_TO_CHECK = NEAREST_PARKING_LOT.latitude;
                    String nearestParkingLatitude = Double.toString(LATITUDE_TO_CHECK);

                    switch (nearestParkingLatitude) {
                        case "43.648188":
                            mMap.addMarker(new MarkerOptions().position(downtown).title("Best Parking GO"));
                            mMap.moveCamera(CameraUpdateFactory.zoomTo(12.3f));
                            mMap.moveCamera(CameraUpdateFactory.newLatLng(downtown));
                            break;
                        case "43.835833":
                            mMap.addMarker(new MarkerOptions().position(richmond_hill).title("Viva Free Parking"));
                            mMap.moveCamera(CameraUpdateFactory.zoomTo(12.3f));
                            mMap.moveCamera(CameraUpdateFactory.newLatLng(richmond_hill));
                            break;
                        case "43.837087":
                            mMap.addMarker(new MarkerOptions().position(sheppard).title("Pickering GO"));
                            mMap.moveCamera(CameraUpdateFactory.zoomTo(12.3f));
                            mMap.moveCamera(CameraUpdateFactory.newLatLng(sheppard));
                            break;
                        case "43.795130":
                            mMap.addMarker(new MarkerOptions().position(pickering).title("Sheppard Parking Lot"));
                            mMap.moveCamera(CameraUpdateFactory.zoomTo(12.3f));
                            mMap.moveCamera(CameraUpdateFactory.newLatLng(pickering));


                    }
                } catch (NullPointerException e)
                {
                    Log.e("Null distance", "The distance to the nearest parking lot failed to be calculated");
                }

            }



        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public LatLng NearestParking(LatLng inputAddress)
    {
        LatLng downtown = new LatLng(43.648188, -79.392968);
        LatLng richmond_hill = new LatLng(43.835833, -79.413896);
        LatLng pickering = new LatLng(43.837087, -79.086135);
        LatLng sheppard = new LatLng(43.795130, -79.281837);

        LatLng[] parkings = {downtown, richmond_hill,pickering,sheppard};

        //Distance(s) between the destination and the nearest parking spot.
        ArrayList<Double> distances = new ArrayList<>();

        for (LatLng coords:parkings
             ) {


            distances.add(calculateDistance(coords,inputAddress));

        }

        if (distances.size() != parkings.length)
            return null;

        int shortestDistancePosition = distances.indexOf(Collections.min(distances));

        return parkings[shortestDistancePosition];
    }

    public double calculateDistance(LatLng parkingLot, LatLng inputAddress1)
    {
        return Math.sqrt( Math.pow(parkingLot.latitude - inputAddress1.latitude,2) + Math.pow(parkingLot.longitude - inputAddress1.longitude,2));
    }

    public void Moving_Reseration (View view) {

        startActivity(new Intent(DestinationMapsActivity.this,ReservationsActivity.class));
    }
}

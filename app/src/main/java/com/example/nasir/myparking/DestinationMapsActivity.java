package com.example.nasir.myparking;

import android.location.Address;
import android.location.Geocoder;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

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

        final LatLng downtown = new LatLng(43.648188, -79.392968);
        final LatLng richmond_hill = new LatLng(43.835833, -79.413896);
        final LatLng pickering = new LatLng(43.837087, -79.086135);
        final LatLng sheppard = new LatLng(43.795130, -79.281837);

        LatLng[] parkings = {downtown, richmond_hill,pickering,sheppard};

        String inputAddress = getIntent().getStringExtra("address");
        Geocoder geoCoder = new Geocoder(
                getBaseContext(), Locale.getDefault());
        try {List<Address> addresses = geoCoder.getFromLocationName(
                inputAddress, 5);
            if (addresses.size() > 0) {
                LatLng destinedAddress = new LatLng(
                        (int) (addresses.get(0).getLatitude()),
                        (int) (addresses.get(0).getLongitude()));

                String nearestParkingLatitude = Double.toString(NearestParking(destinedAddress).latitude);

                switch (nearestParkingLatitude)
                {
                    case "43.648188":
                        mMap.addMarker(new MarkerOptions().position(downtown).title("Best Parking GO"));
                        mMap.moveCamera(CameraUpdateFactory.newLatLng(downtown));
                        break;
                    case "43.835833":
                        mMap.addMarker(new MarkerOptions().position(richmond_hill).title("Best Parking GO"));
                        mMap.moveCamera(CameraUpdateFactory.newLatLng(richmond_hill));
                        break;
                    case "43.795130":
                        mMap.addMarker(new MarkerOptions().position(sheppard).title("Best Parking GO"));
                        mMap.moveCamera(CameraUpdateFactory.newLatLng(sheppard));
                        break;
                    case "43.837087":
                        mMap.addMarker(new MarkerOptions().position(pickering).title("Best Parking GO"));
                        mMap.moveCamera(CameraUpdateFactory.newLatLng(pickering));



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

        for (LatLng coords:parkings
             ) {

            ArrayList<Double> distances = new ArrayList<>();
            distances.add(calculateDistance(coords,inputAddress));

            int shortestDistancePosition = distances.indexOf(Collections.min(distances));

            return parkings[shortestDistancePosition];

        }

        return null;
    }

    public double calculateDistance(LatLng parkingLot, LatLng inputAddress1)
    {
        return Math.sqrt( Math.pow(parkingLot.latitude - inputAddress1.latitude,2) + Math.pow(parkingLot.longitude - inputAddress1.longitude,2));
    }

}

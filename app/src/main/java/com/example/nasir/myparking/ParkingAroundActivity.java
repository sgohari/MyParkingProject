package com.example.nasir.myparking;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

public class ParkingAroundActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.InfoWindowAdapter {

    private GoogleMap mMap;

    LocationManager locationManager;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parking_around);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        Button btnReserve=(Button)findViewById(R.id.btnReserveNow);
        btnReserve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                startActivity(new Intent(ParkingAroundActivity.this,ReservationsActivity.class));
            }
        });

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
    public void onMapReady (GoogleMap googleMap) {
        mMap = googleMap;
        //marker in Progress Compus and move the camera
        LatLng progressCompus = new LatLng(43.784401, -79.229276);
        mMap.addMarker(new MarkerOptions().position(progressCompus).title("Progress Compus").snippet("941 Progress Ave, Scarborough, ON M1G 3T8")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(progressCompus));
        //marker for morning side compus
        LatLng trontMorningSide= new LatLng(43.786668,-79.192882);
        mMap.addMarker(new MarkerOptions().position(trontMorningSide).title("Morning Side Compus").snippet("755 Morningside Ave, Scarborough,ON M1C 4Z4")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(trontMorningSide));
        //marker for Ashtonbee compuse
        LatLng ashtonbee=new LatLng(43.730703,-79.290422);
        mMap.addMarker(new MarkerOptions().position(ashtonbee).title("Ashtonbee Compus")
                .snippet("75 Ashtonbee Rd B2-11 75, Scarborough,ON M1L 4C9").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ROSE)));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(ashtonbee));

        //marker for Center for art
        LatLng artStory= new LatLng(43.684367,-79.348863);
        mMap.addMarker(new MarkerOptions().position(artStory).title("Story Arts Compus ").snippet("951 Carlaw Ave, Toronto, ON M4K 3M2")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));

        //moves the camera focus to reffered latite and Altitute
        mMap.moveCamera(CameraUpdateFactory.newLatLng(artStory));

        //marker for Center for art
        LatLng pickering= new LatLng(43.833037,-79.086306);
        mMap.addMarker(new MarkerOptions().position(pickering).title("Pickering Compus").snippet("1340 Pickering Pkwy, Pickering, ON L1V")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));

        //moves the camera focus to reffered latite and Altitute
        mMap.moveCamera(CameraUpdateFactory.newLatLng(pickering));
        mMap.setInfoWindowAdapter(this);


        mMap.animateCamera(CameraUpdateFactory.zoomTo(12),700, null);
        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.moveCamera(CameraUpdateFactory.zoomTo(10.5f));
    }

    @Override
    public View getInfoWindow (Marker marker) {

        View view=getLayoutInflater().inflate(R.layout.custom_window,null);

        TextView title = (TextView) view.findViewById(R.id.txtTitle);
        TextView snipped = (TextView) view.findViewById(R.id.txtSnipped);

        title.setText(marker.getTitle());
        snipped.setText(marker.getSnippet());
        return view;
    }

    @Override
    public View getInfoContents (Marker marker) {
        return null;
    }
}

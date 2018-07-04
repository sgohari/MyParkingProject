package com.example.nasir.myparking;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

public class ParkingAroundActivity extends FragmentActivity implements OnMapReadyCallback {

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
        mMap.addMarker(new MarkerOptions().position(progressCompus).title("Progress Compus").icon(BitmapDescriptorFactory.fromResource(R.mipmap.centennial))).setSnippet("941 Progress Ave, Scarborough, ON M1G 3T8");
        mMap.moveCamera(CameraUpdateFactory.newLatLng(progressCompus));
        //marker for morning side compus
        LatLng trontMorningSide= new LatLng(43.786668,-79.192882);
        mMap.addMarker(new MarkerOptions().position(trontMorningSide).title("Morning Side Compus").icon(BitmapDescriptorFactory.fromResource(R.mipmap.centennial))).setSnippet("755 Morningside Ave, Scarborough,ON M1C 4Z4");
        mMap.moveCamera(CameraUpdateFactory.newLatLng(trontMorningSide));
        //marker for Ashtonbee compuse
        LatLng ashtonbee=new LatLng(43.730703,-79.290422);
        mMap.addMarker(new MarkerOptions().position(ashtonbee).title("Ashtonbee Compus").icon(BitmapDescriptorFactory.fromResource(R.mipmap.centennial))).setSnippet("75 Ashtonbee Rd B2-11 75, Scarborough,ON M1L 4C9");
        mMap.moveCamera(CameraUpdateFactory.newLatLng(ashtonbee));

        //marker for Center for art
        LatLng artStory= new LatLng(43.684367,-79.348863);
        mMap.addMarker(new MarkerOptions().position(artStory).title("Story Arts Compus ").icon(BitmapDescriptorFactory.fromResource(R.mipmap.centennial))).setSnippet("951 Carlaw Ave, Toronto, ON M4K 3M2");

        //moves the camera focus to reffered latite and Altitute
        mMap.moveCamera(CameraUpdateFactory.newLatLng(artStory));

        //marker for Center for art
        LatLng pickering= new LatLng(43.833037,-79.086306);
        mMap.addMarker(new MarkerOptions().position(pickering).title("Pickering Compus").icon(BitmapDescriptorFactory.fromResource(R.mipmap.centennial))).setSnippet("1340 Pickering Pkwy, Pickering, ON L1V");

        //moves the camera focus to reffered latite and Altitute
        mMap.moveCamera(CameraUpdateFactory.newLatLng(pickering));


        mMap.animateCamera(CameraUpdateFactory.zoomTo(12),700, null);
        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.moveCamera(CameraUpdateFactory.zoomTo(10.5f));
    }
}

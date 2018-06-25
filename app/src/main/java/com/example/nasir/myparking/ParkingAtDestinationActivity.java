package com.example.nasir.myparking;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class ParkingAtDestinationActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parking_at_destination);
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

        // Add a marker in Toronto and move the camera
        LatLng gbrownCasaLoma = new LatLng(43.676120, -79.410520);
        mMap.addMarker(new MarkerOptions().position(gbrownCasaLoma).title("Casa Loma Compus").
                icon(BitmapDescriptorFactory.fromResource(R.mipmap.gbrown))).
                setSnippet("160 Kendal Ave, Toronto, ON M5R 1M3");
        //moves the camera focus to reffered latite and Altitute
        mMap.moveCamera(CameraUpdateFactory.newLatLng(gbrownCasaLoma));

        // Add a marker in Toronto and move the camera
        LatLng gbrownStJames = new LatLng(43.650934, -79.370200);
        mMap.addMarker(new MarkerOptions().position(gbrownStJames).title("St. James Campus").
                icon(BitmapDescriptorFactory.fromResource(R.mipmap.gbrown))).
                setSnippet("200 King St E, Toronto, ON M5A 3W8");
        //moves the camera focus to reffered latite and Altitute
        mMap.moveCamera(CameraUpdateFactory.newLatLng(gbrownStJames));

        // Add a marker in Toronto and move the camera
        LatLng gbrownWaterFront = new LatLng(43.644070, -79.365427);
        mMap.addMarker(new MarkerOptions().position(gbrownWaterFront).title("Waterfront Campus").
                icon(BitmapDescriptorFactory.fromResource(R.mipmap.gbrown))).
                setSnippet("51 Dockside Dr, Toronto, ON M5A 1B");
        //moves the camera focus to reffered latite and Altitute
        mMap.moveCamera(CameraUpdateFactory.newLatLng(gbrownWaterFront));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(14),700, null);
        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.moveCamera(CameraUpdateFactory.zoomTo(12.3f));
    }
}

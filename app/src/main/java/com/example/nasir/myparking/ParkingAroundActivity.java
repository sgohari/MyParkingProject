package com.example.nasir.myparking;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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

public class ParkingAroundActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.InfoWindowAdapter, GoogleMap.OnInfoWindowClickListener {

    private GoogleMap mMap;

    LocationManager locationManager;
    private String username;
    Button back;
    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parking_around);
        username = getIntent().getStringExtra("username");

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        back = findViewById(R.id.backBack);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ParkingAroundActivity.this,CustomerHomePage.class));
            }
        });

        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }

        // Check if the networkig provider is enabled.
        if (locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)){

            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, new LocationListener() {
                @Override
                public void onLocationChanged (Location location) {

                    double latitude=location.getLatitude();
                    double longitude =location.getLongitude();

                    LatLng latLng=new LatLng(latitude, longitude);

                    Geocoder geocoder= new Geocoder(getApplicationContext());
                    try {
                        List<Address> addressList=geocoder.getFromLocation(latitude,longitude,3);
                        String str = addressList.get(0).getLocality()+" , ";
                        str+=addressList.get(0).getCountryName();

                        mMap.addMarker(new MarkerOptions().position(latLng).title(str));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,10.2f));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                }

                @Override
                public void onStatusChanged (String provider, int status, Bundle extras) {

                }

                @Override
                public void onProviderEnabled (String provider) {

                }

                @Override
                public void onProviderDisabled (String provider) {

                }
            });

        }
else if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)){
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0,
            new LocationListener() {
                @Override
                public void onLocationChanged (Location location) {

                    double latitude=location.getLatitude();
                    double longitude =location.getLongitude();

                    LatLng latLng=new LatLng(latitude, longitude);

                    Geocoder geocoder= new Geocoder(getApplicationContext());
                    try {
                        List<Address> addressList=geocoder.getFromLocation(latitude,longitude,3);
                        String str = addressList.get(0).getLocality()+" , ";
                        str+=addressList.get(0).getCountryName();

                        mMap.addMarker(new MarkerOptions().position(latLng).title(str));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,10.2f));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }

                @Override
                public void onStatusChanged (String provider, int status, Bundle extras) {

                }

                @Override
                public void onProviderEnabled (String provider) {

                }

                @Override
                public void onProviderDisabled (String provider) {

                }
            });
        }

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
        mMap.addMarker(new MarkerOptions().position(progressCompus).title("Progress Lot")
                .snippet("941 Progress Ave,"+"\n"+" Scarborough, ON M1G 3T8")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(progressCompus));
        //marker for morning side compus
        LatLng trontMorningSide= new LatLng(43.786668,-79.192882);
        mMap.addMarker(new MarkerOptions().position(trontMorningSide).title("Morning Side Lot")
                .snippet("755 Morningside Ave,"+"\n"+
                " Scarborough,ON M1C 4Z4")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(trontMorningSide));
        //marker for Ashtonbee compuse
        LatLng ashtonbee=new LatLng(43.730703,-79.290422);
        mMap.addMarker(new MarkerOptions().position(ashtonbee).title("Ashtonbee Lot")
                .snippet("75 Ashtonbee Rd"+"\n"+"B2-11 75,"+"\n"+"Scarborough,ON M1L 4C9")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ROSE)));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(ashtonbee));

        //marker for Center for art
        LatLng artStory= new LatLng(43.684367,-79.348863);
        mMap.addMarker(new MarkerOptions().position(artStory).title("Story Arts Lot")
                .snippet("951 Carlaw Ave,"+"\n"+" Toronto, ON M4K 3M2")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));


        //moves the camera focus to reffered latite and Altitute
        mMap.moveCamera(CameraUpdateFactory.newLatLng(artStory));

        //marker for Center for art
        LatLng pickering= new LatLng(43.833037,-79.086306);
        mMap.addMarker(new MarkerOptions().position(pickering).title("Pickering Lot")
                .snippet("1340 Pickering Pkwy,"+"\n"+"Pickering, ON L1V")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));

        //moves the camera focus to reffered latite and Altitute
        mMap.moveCamera(CameraUpdateFactory.newLatLng(pickering));
        mMap.setInfoWindowAdapter(this);

        mMap.setOnInfoWindowClickListener(this);

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

    @Override
    public void onInfoWindowClick (Marker marker) {

        SharedPreferences marketInfo= getSharedPreferences("markerContent", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = marketInfo.edit();

        if (marker.getTitle().equals("Pickering Lot")){
            editor.putString("title",marker.getTitle().toString());
            editor.putString("snipped",marker.getSnippet().toString());
            editor.apply();

            startActivity(new Intent(ParkingAroundActivity.this,ReservationsActivity.class));
        }
        if (marker.getTitle().equals("Story Arts Lot")){
            editor.putString("title",marker.getTitle().toString());
            editor.putString("snipped",marker.getSnippet().toString());
            editor.apply();
            startActivity(new Intent(ParkingAroundActivity.this,ReservationsActivity.class));

        }
        if (marker.getTitle().equals("Ashtonbee Lot")){
            editor.putString("title",marker.getTitle().toString());
            editor.putString("snipped",marker.getSnippet().toString());
            editor.apply();
            startActivity(new Intent(ParkingAroundActivity.this,ReservationsActivity.class));

        }
        if (marker.getTitle().equals("Morning Side Lot")){
            editor.putString("title",marker.getTitle().toString());
            editor.putString("snipped",marker.getSnippet().toString());
            editor.apply();
            startActivity(new Intent(ParkingAroundActivity.this,ReservationsActivity.class));

        }
        if (marker.getTitle().equals("Progress Lot")){
            editor.putString("title",marker.getTitle().toString());
            editor.putString("snipped",marker.getSnippet().toString());
            editor.apply();

            startActivity(new Intent(ParkingAroundActivity.this,ReservationsActivity.class));

        }

        if (marker.getTitle().equals("Morning Side Lot")){
            editor.putString("title",marker.getTitle().toString());
            editor.putString("snipped",marker.getSnippet().toString());
            editor.apply();

            startActivity(new Intent(ParkingAroundActivity.this,ReservationsActivity.class));

        }
    }
}

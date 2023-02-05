package com.example.mygeoapp;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;
import android.Manifest;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.mygeoapp.databinding.ActivityResultMapsBinding;

public class ResultMapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityResultMapsBinding binding;
    private Button buttonStop;
    private Button buttonStart;
    public int id;
    private ContentResolver resolver;
    private final  static String AUTHORITY = "com.example.mygeoapp";
    private final static String PATH = "user";
    private int K = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityResultMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        // Request Permission for locations
        requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},4);


        resolver= this.getContentResolver();

        buttonStart= findViewById(R.id.startButton2);
        buttonStop = findViewById(R.id.stopButton2);

        // Listeners on start and stop buttons
        runOnUiThread(()-> buttonStop.setOnClickListener(v -> {
            Intent intent = new Intent(ResultMapsActivity.this, MainActivity.class);
            startActivity(intent);
        }));

        runOnUiThread(()-> buttonStart.setOnClickListener(v -> showCircles()));
    }


    public void showCircles(){
        runOnUiThread(()->{
            Uri uri = Uri.parse("content://"+AUTHORITY+"/"+PATH);
            Cursor cursor = resolver.query(uri, null, null, null, null);
            if(cursor.moveToFirst()){
                do{
                   CircleOptions circleOptions = new CircleOptions();
                   LatLng location= new LatLng(cursor.getDouble(K+1),cursor.getDouble(K+2));

                   Log.d("MESSAGE", cursor.getString(K));
                   Log.d("MESSAGE", cursor.getString(K+1));
                   Log.d("MESSAGE", cursor.getString(K+2));
                   K=K+3;
                   circleOptions.center(location);
                   circleOptions.radius(100);
                   circleOptions.visible(true);
                   circleOptions.strokeColor(Color.RED);
                   mMap.addCircle(circleOptions);
                }while(cursor.moveToNext());
            }
            cursor.close();
        });
    }
    // Show user's position
    public void showPosition() {
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            return;
        }
        mMap.setMyLocationEnabled(true);
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
        showPosition();
        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }


}
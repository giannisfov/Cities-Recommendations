package com.example.mygeoapp;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.room.Room;
import android.Manifest;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.mygeoapp.databinding.ActivityMapsBinding;

import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;
    private Button buttonStop;
    private Button buttonStart;
    private Database db;
    private  UserDao userDao;
    public int id=0;
    private  Circle mapCircle;
    private static int STATUS=0;
    private ContentResolver resolver;
    private boolean F = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        // Request Permission for locations
       requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},4);

       resolver= this.getContentResolver();

       createDatabase();

        buttonStart= findViewById(R.id.startButton);
        buttonStop = findViewById(R.id.stopButton);

        // Listeners on start and stop buttons
        runOnUiThread(()-> buttonStop.setOnClickListener(v -> {
            Intent intent = new Intent(MapsActivity.this, MainActivity.class);
            startActivity(intent);
        }));

        runOnUiThread(()-> buttonStart.setOnClickListener(v -> circleDraw()));
    }

    // Show user's position
    public void showPosition() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        mMap.setMyLocationEnabled(true);
    }

    // Create Database
    private void createDatabase(){
        new Thread(()->{
           db= Room.databaseBuilder(getApplicationContext(),Database.class, "users").allowMainThreadQueries().build();
           userDao= db.userDao();
        }).start();
    }


    // Save on database user Id , latitude and longitude where he clicked on the map
    private void saveUser(LatLng latLng){
        double latitude  = latLng.latitude;
        double longitude = latLng.longitude;

        runOnUiThread(()->{
            User user= new User();
            user.latitude=latitude;
            user.longitude=longitude;
            user.id=id++;
            userDao.insertUser(user);
        });
    }


    // User click on a location and draws a circle
    private void circleDraw(){
            runOnUiThread(()-> mMap.setOnMapLongClickListener(latLng -> {

                CircleOptions circleOptions= new CircleOptions();
                circleOptions.center(latLng);
                circleOptions.radius(100);
                circleOptions.strokeColor(Color.RED);
                circleOptions.visible(true);

                if(STATUS==0){
                    mMap.addCircle(circleOptions);
                    saveUser(latLng);
                    STATUS++;
                }
                List<User> users = userDao.getAll();
                if(users != null){
                    for(User user:users){
                        if(user.latitude==latLng.latitude && user.longitude==latLng.longitude){
                            mapCircle= mMap.addCircle(circleOptions);
                            mapCircle.remove();
                           // userDao.deleteUser(user.latitude, user.longitude);
                        }else{
                            mMap.addCircle(circleOptions);
                            saveUser(latLng);
                        }
                    }
                }
            }));
    }

    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        showPosition();

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

    }

}













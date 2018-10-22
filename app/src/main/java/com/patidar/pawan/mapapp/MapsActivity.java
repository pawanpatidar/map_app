package com.patidar.pawan.mapapp;

import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.patidar.pawan.mapapp.Adapter.WindowAdapter;
import com.patidar.pawan.mapapp.modal.Events;
import com.patidar.pawan.mapapp.modal.User;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback,GoogleMap.OnInfoWindowClickListener {

    private GoogleMap mMap;
    private ChildEventListener childEventListener;
    Marker marker;
    DatabaseReference databaseReference;
    private String  name;
    private String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        FirebaseApp.initializeApp(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        // Sets the Toolbar to act as the ActionBar for this Activity window.
        // Make sure the toolbar exists in the activity and is not null

        databaseReference= FirebaseDatabase.getInstance().getReference("event");
//        AddUser();
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        databaseReference.push().setValue(marker);

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
    public void onMapReady(final GoogleMap googleMap) {
        mMap = googleMap;
//        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
//            @Override
//            public boolean onMarkerClick(Marker marker) {
//
//                Intent i= new Intent(getApplicationContext(),EventDetails.class);
//                i.putExtra("id",marker.getId());
//                i.putExtra("eventname",marker.getTitle());
//                i.putExtra("location",marker.getSnippet());
//                startActivity(i);
//                return false;
//            }
//        });
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot s: dataSnapshot.getChildren()){
                    Events events= s.getValue(Events.class);

                    LatLng sydney = new LatLng(events.longitude, events.latitude);
                    mMap.addMarker(new MarkerOptions().position(sydney)
                            .title(events.name)).setSnippet(events.place);
                    mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

                    mMap.setInfoWindowAdapter(new WindowAdapter(MapsActivity.this));
                    mMap.setOnInfoWindowClickListener(MapsActivity.this);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    @Override
    public void onInfoWindowClick(Marker marker) {
        Intent i= new Intent(getApplicationContext(),EventDetails.class);
        i.putExtra("id",marker.getId());
        i.putExtra("eventname",marker.getTitle());
        i.putExtra("location",marker.getSnippet());
        startActivity(i);
    }
}

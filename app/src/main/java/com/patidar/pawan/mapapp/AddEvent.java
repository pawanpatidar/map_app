package com.patidar.pawan.mapapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.patidar.pawan.mapapp.modal.EventDescription;
import com.patidar.pawan.mapapp.modal.Events;

public class AddEvent extends AppCompatActivity implements View.OnClickListener {
    private EditText name,place,description;
    EditText longitude, latitude;
    DatabaseReference databaseReference_event;
    DatabaseReference databaseReference_detail;
    private Button button;
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);
        name =findViewById(R.id.event_name);
        place =findViewById(R.id.event_place);
        description =findViewById(R.id.event_desc);
        longitude =findViewById(R.id.longitude);
        latitude =findViewById(R.id.latitude);
        databaseReference_event = FirebaseDatabase.getInstance().getReference("event");

        button = findViewById(R.id.save);
        btn = findViewById(R.id.view);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SaveEvent();
                name.getText().clear();
                place.getText().clear();;
                description.getText().clear();
                longitude.getText().clear();
                latitude.getText().clear();
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MapsActivity.class);
                startActivity(intent);
            }
        });

    }

    public void SaveEvent(){
        String name_evt = name.getText().toString();
        String place_evt = place.getText().toString();
        String event_desc = description.getText().toString();
        double longitude_evt = Double.parseDouble( longitude.getText().toString());
        double latitude_evt = Double.parseDouble( latitude.getText().toString());
        String id_event= databaseReference_event.push().getKey();
        Events events= new Events(id_event,name_evt,place_evt,longitude_evt,latitude_evt);
        databaseReference_event.child(id_event).setValue(events);
        databaseReference_detail = FirebaseDatabase.getInstance().getReference("event_description").child(id_event);
        String id_desc= databaseReference_detail.push().getKey();
        EventDescription eventDescription= new EventDescription(id_event,event_desc,0);
        databaseReference_detail.child(id_desc).setValue(eventDescription);

    }

    @Override
    public void onClick(View v) {
        if(v==button){

        }else if(v==btn){
            finish();
        }

    }
}

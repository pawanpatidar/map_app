package com.patidar.pawan.mapapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.patidar.pawan.mapapp.modal.EventDescription;
import com.patidar.pawan.mapapp.modal.Events;

public class EventDetails extends AppCompatActivity {

    TextView name,description_event,place;
    DatabaseReference databaseReference;
    ImageButton imageButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_details);
        name =findViewById(R.id.event_name);
        place =findViewById(R.id.event_location);
        description_event =findViewById(R.id.event_description);
        imageButton = findViewById(R.id.favourite);
        databaseReference = FirebaseDatabase.getInstance().getReference("event_description");
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        Intent intent= this.getIntent();
        final String id=intent.getExtras().getString("id");
        String ev_name=intent.getExtras().getString("eventname");
        String  location =intent.getExtras().getString("location");
        name.setText(ev_name);
        place.setText(location);
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot s: dataSnapshot.getChildren()) {
                    try {
                        EventDescription details = s.getValue(EventDescription.class);
                        description_event.setText(details.getEvent_desc());
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}

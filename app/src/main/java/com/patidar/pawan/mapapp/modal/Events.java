package com.patidar.pawan.mapapp.modal;

public class Events {

    public String name;
    public String place;
    public double longitude;
    public double latitude;
    public String event_id;

    public Events(){
        
    }



    public Events(String event_id, String name, String place, double longitude, double latitude){
        this.event_id=event_id;
        this.name=name;
        this.place=place;
        this.longitude=longitude;
        this.latitude= latitude;

    }
    public String getEvent_id() {
        return event_id;
    }
    public String getName() {
        return name;
    }

    public String getPlace() {
        return place;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getLatitude() {
        return latitude;
    }
}

package com.patidar.pawan.mapapp.modal;

public class EventDescription {

    String event_id;
    String event_desc;
    int favourite;

    public EventDescription(String event_id,String event_desc, int favourite){
        this.event_id = event_id;
        this.event_desc=event_desc;
        this.favourite= favourite;
    }

    public String getEvent_id() {
        return event_id;
    }

    public String getEvent_desc() {
        return event_desc;
    }

    public int getFavourite() {
        return favourite;
    }
}

package com.example.clean_uptribe;



public class EventModel {
    private int id;
    private String eventName, location, date, time, description;

    public EventModel(int id, String eventName, String location, String date, String time, String description) {
        this.id = id;
        this.eventName = eventName;
        this.location = location;
        this.date = date;
        this.time = time;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getEventName() {
        return eventName;
    }

    public String getLocation() {
        return location;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getDescription() {
        return description;
    }
}

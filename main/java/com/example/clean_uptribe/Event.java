package com.example.clean_uptribe;

public class Event {
    private String name, date, time, description;  // Fields to store event details

    // Constructor to initialize the event details
    public Event(String name, String date, String time, String description) {
        this.name = name;
        this.date = date;
        this.time = time;
        this.description = description;
    }

    // Getter method for the event name
    public String getName() {
        return name;
    }

    // Getter method for the event date
    public String getDate() {
        return date;
    }

    // Getter method for the event time
    public String getTime() {
        return time;
    }

    // Getter method for the event description
    public String getDescription() {
        return description;
    }
}

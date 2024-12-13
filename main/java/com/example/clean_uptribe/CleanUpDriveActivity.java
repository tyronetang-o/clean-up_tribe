
package com.example.clean_uptribe;


import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;



import android.database.Cursor;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class
CleanUpDriveActivity extends AppCompatActivity {

    private RecyclerView eventsRecyclerView;
    private EventAdapter eventAdapter;
    private List<EventModel> eventList;
    private CreateEventDBHelper createEventDBHelper;
    String role;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clean_up_drive);

        eventsRecyclerView = findViewById(R.id.eventsRecyclerView);
        createEventDBHelper = new CreateEventDBHelper(this);

        // Initialize list and fetch events
        eventList = new ArrayList<>();
        fetchEventsFromDatabase();

        // Set up RecyclerView
        eventsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        eventAdapter = new EventAdapter(this, eventList);
        eventsRecyclerView.setAdapter(eventAdapter);

         role = getIntent().getStringExtra("role");

        eventAdapter.setOnItemClickListener(event -> {
           if ("admin".equals(role)) {
               Intent intent = new Intent(this, EditEventActivity.class);
               intent.putExtra("event_id", event.getId());
               intent.putExtra("event_name", event.getEventName());
               intent.putExtra("event_location", event.getLocation());
               intent.putExtra("event_date", event.getDate());
               intent.putExtra("event_time", event.getTime());
               intent.putExtra("event_description", event.getDescription());
               intent.putExtra("role", role);
               startActivity(intent);
               // Finish current activity to prevent going back to it
               finish();
           } else {
               Intent intent = new Intent(this, ViewEventOnly.class);
               intent.putExtra("event_id", event.getId());
               intent.putExtra("event_name", event.getEventName());
               intent.putExtra("event_location", event.getLocation());
               intent.putExtra("event_date", event.getDate());
               intent.putExtra("event_time", event.getTime());
               intent.putExtra("event_description", event.getDescription());
               startActivity(intent);
               // Finish current activity to prevent going back to it
               finish();
           }
        });
    }
    private void fetchEventsFromDatabase() {
        Cursor cursor = createEventDBHelper.getAllEvents();
        if (cursor != null) {
            while (cursor.moveToNext()) {
                int id = cursor.getInt(0);
                String eventName = cursor.getString(1);
                String location = cursor.getString(2);
                String date = cursor.getString(3);
                String time = cursor.getString(4);
                String description = cursor.getString(5);

                eventList.add(0, new EventModel(id, eventName, location, date, time, description));
            }
            cursor.close();
        }
    }
}

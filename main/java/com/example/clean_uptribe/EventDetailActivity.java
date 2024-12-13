package com.example.clean_uptribe;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class EventDetailActivity extends AppCompatActivity {

    // Declare UI components: TextViews for event details and EditTexts for user input
    private TextView eventNameTxtv, dateTxtv, timeTxtv, descriptionTxtv;
    private EditText nameEdt, emailEdt, contactEdt;
    private Button joinEventBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_detail);


        // Link the UI components to the respective views in the layout
        eventNameTxtv = findViewById(R.id.eventNameTxtv);
        dateTxtv = findViewById(R.id.dateTxtv);
        timeTxtv = findViewById(R.id.timeTxtv);
        descriptionTxtv = findViewById(R.id.descriptionTxtv);
        nameEdt = findViewById(R.id.eventDetailsNameEdt);
        emailEdt = findViewById(R.id.eventDetailEmailEdt);
        contactEdt = findViewById(R.id.eventDetailContactEdt);
        joinEventBtn = findViewById(R.id.joinEventBtn);

        // Retrieve the event name passed from the previous activity

    }
}

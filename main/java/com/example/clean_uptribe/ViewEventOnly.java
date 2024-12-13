package com.example.clean_uptribe;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ViewEventOnly extends AppCompatActivity {
    private TextView eventNameTxt, locationTxt, dateTimeTxt, descriptionTxt;
    private String timeEdt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_view_event_only);

        eventNameTxt = findViewById(R.id.eventNameTxt);
        locationTxt = findViewById(R.id.locationTxt);
        dateTimeTxt = findViewById(R.id.dateTimeTxt);
        descriptionTxt = findViewById(R.id.descriptionTxt);

        eventNameTxt.setText(getIntent().getStringExtra("event_name"));
        locationTxt.setText(getIntent().getStringExtra("event_location"));
        dateTimeTxt.setText(getIntent().getStringExtra("event_date") + "at " + getIntent().getStringExtra("event_time"));

        descriptionTxt.setText(getIntent().getStringExtra("event_description"));

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}
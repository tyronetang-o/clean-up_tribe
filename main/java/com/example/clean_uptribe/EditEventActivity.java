package com.example.clean_uptribe;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class EditEventActivity extends AppCompatActivity {
    private EditText eventNameEdt, locationEdt, dateEdt, timeEdt, descriptionEdt;
    private Button updateEventBtn, endEventBtn;
    private CreateEventDBHelper createEventDBHelper;
    private int eventId;
    private String role;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_edit_event);

        eventNameEdt = findViewById(R.id.eventNameEdt);
        locationEdt = findViewById(R.id.locationEdt);
        dateEdt = findViewById(R.id.dateEdt);
        timeEdt = findViewById(R.id.timeEdt);
        descriptionEdt = findViewById(R.id.descriptionEdt);
        updateEventBtn = findViewById(R.id.updateEventBtn);
        endEventBtn = findViewById(R.id.endEventBtn);
        createEventDBHelper = new CreateEventDBHelper(this);

        eventId = getIntent().getIntExtra("event_id", -1);
        eventNameEdt.setText(getIntent().getStringExtra("event_name"));
        locationEdt.setText(getIntent().getStringExtra("event_location"));
        dateEdt.setText(getIntent().getStringExtra("event_date"));
        timeEdt.setText(getIntent().getStringExtra("event_time"));
        descriptionEdt.setText(getIntent().getStringExtra("event_description"));

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.editEvent), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        role = getIntent().getStringExtra("role");

        updateEventBtn.setOnClickListener(v -> {
            String eventName = eventNameEdt.getText().toString().trim();
            String location = locationEdt.getText().toString().trim();
            String date = dateEdt.getText().toString().trim();
            String time = timeEdt.getText().toString().trim();
            String description = descriptionEdt.getText().toString().trim();

            boolean isUpdated = createEventDBHelper.updateEvent(eventId, eventName, location, date, time, description);
            if (isUpdated) {
                Toast.makeText(EditEventActivity.this, "Event Updated Successfully!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(EditEventActivity.this, CleanUpDriveActivity.class);
                intent.putExtra("role", role);
                startActivity(intent);

                finish();
            } else {
                Toast.makeText(EditEventActivity.this, "Failed to Update Event", Toast.LENGTH_SHORT).show();
            }
        });

        endEventBtn.setOnClickListener(v -> {
            boolean isArchived = archiveEvent(eventId, eventNameEdt.getText().toString().trim(),
                    locationEdt.getText().toString().trim(),
                    dateEdt.getText().toString().trim(),
                    timeEdt.getText().toString().trim(),
                    descriptionEdt.getText().toString().trim());

            if (isArchived) {
                boolean isDeleted = createEventDBHelper.deleteEvent(eventId);
                if (isDeleted) {
                    Toast.makeText(EditEventActivity.this, "Event Ended Successfully!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(EditEventActivity.this, CleanUpDriveActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(EditEventActivity.this, "Failed to Remove Event After Archiving", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(EditEventActivity.this, "Failed to Archive Event", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private boolean archiveEvent(int id, String eventName, String location, String date, String time, String description) {
        ArchiveEventDBHelper archiveEventDBHelper = new ArchiveEventDBHelper(this);
        return archiveEventDBHelper.insertArchivedEvent(id, eventName, location, date, time, description);
    }
}

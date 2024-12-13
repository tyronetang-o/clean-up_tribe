package com.example.clean_uptribe;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.widget.DatePicker;
import android.widget.TimePicker;

import java.util.Calendar;

public class CreateCleanUpEventActivity extends AppCompatActivity {

    // Initialize UI components
    private EditText eventNameEdt, locationEdt, dateEdt, timeEdt, descriptionEdt;
    private Button createEventBtn, gotoeventbtn, donationHistoryBtn, joinhistorybtn, eventarchivebtn, logoutBtn;
    private CreateEventDBHelper createEventDBHelper;
    private String role;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);

        // Initialize components
        eventNameEdt = findViewById(R.id.eventNameEdt);
        locationEdt = findViewById(R.id.locationEdt);
        dateEdt = findViewById(R.id.dateEdt);
        timeEdt = findViewById(R.id.timeEdt);
        descriptionEdt = findViewById(R.id.descriptionEdt);
        createEventBtn = findViewById(R.id.createEventBtn);
        gotoeventbtn = findViewById(R.id.gotoeventbtn);
        donationHistoryBtn = findViewById(R.id.donationHistoryBtn);
        joinhistorybtn = findViewById(R.id.joinhistorybtn);
        eventarchivebtn = findViewById(R.id.eventarchivebtn);
        logoutBtn = findViewById(R.id.logoutBtn);

        createEventDBHelper = new CreateEventDBHelper(this);

        role = getIntent().getStringExtra("role");

        // Set up DatePicker for dateEdt
        dateEdt.setOnClickListener(v -> {
            final Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    (view, selectedYear, selectedMonth, selectedDay) -> {
                        // Set selected date
                        String formattedDate = selectedDay + "/" + (selectedMonth + 1) + "/" + selectedYear;
                        dateEdt.setText(formattedDate);
                    }, year, month, day);
            datePickerDialog.show();
        });

        // Set up TimePicker for timeEdt
        timeEdt.setOnClickListener(v -> {
            final Calendar calendar = Calendar.getInstance();
            int hour = calendar.get(Calendar.HOUR_OF_DAY);
            int minute = calendar.get(Calendar.MINUTE);

            TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                    (view, selectedHour, selectedMinute) -> {
                        // Format and set selected time
                        String formattedTime = String.format("%02d:%02d", selectedHour, selectedMinute);
                        timeEdt.setText(formattedTime);
                    }, hour, minute, true);
            timePickerDialog.show();
        });

        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CreateCleanUpEventActivity.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
            }
        });


        gotoeventbtn.setOnClickListener(view -> {
            Intent eventList = new Intent(CreateCleanUpEventActivity.this, CleanUpDriveActivity.class);
            eventList.putExtra("role", role);
            startActivity(eventList);
        });

        eventarchivebtn.setOnClickListener(view -> {
            Intent intent = new Intent(CreateCleanUpEventActivity.this, ArchiveActivity.class);
            startActivity(intent);
        });

        joinhistorybtn.setOnClickListener(view -> {
            Intent intent = new Intent(CreateCleanUpEventActivity.this, JoinHistory.class);
            startActivity(intent);
        });

        donationHistoryBtn.setOnClickListener(view -> {
            Intent intent = new Intent(CreateCleanUpEventActivity.this, DonationHistory.class);
            startActivity(intent);
        });

        createEventBtn.setOnClickListener(v -> {
            String eventName = eventNameEdt.getText().toString().trim();
            String location = locationEdt.getText().toString().trim();
            String date = dateEdt.getText().toString().trim();
            String time = timeEdt.getText().toString().trim();
            String description = descriptionEdt.getText().toString().trim();

            if (TextUtils.isEmpty(eventName) || TextUtils.isEmpty(location) ||
                    TextUtils.isEmpty(date) || TextUtils.isEmpty(time) || TextUtils.isEmpty(description)) {
                Toast.makeText(CreateCleanUpEventActivity.this, "Please fill out all fields", Toast.LENGTH_SHORT).show();
            } else {
                boolean isInserted = createEventDBHelper.insertEvent(eventName, location, date, time, description);
                if (isInserted) {
                    Toast.makeText(CreateCleanUpEventActivity.this, "Event Created Successfully!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(CreateCleanUpEventActivity.this, CleanUpDriveActivity.class);
                    intent.putExtra("role", role);
                    startActivity(intent);
                } else {
                    Toast.makeText(CreateCleanUpEventActivity.this, "Failed to create event", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}

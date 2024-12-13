package com.example.clean_uptribe;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class HomeActivity extends AppCompatActivity {

    // Declare buttons for donate, join, and event list actions
    Button donate, join, eventlist, logoutBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this); // Enable edge-to-edge for immersive experience
        setContentView(R.layout.activity_home);

        // Adjust window insets to avoid UI elements being hidden behind system bars
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Link buttons to their respective XML IDs
        donate = findViewById(R.id.btndonate);
        join = findViewById(R.id.btnjoin);
        eventlist = findViewById(R.id.btneventlist);
        logoutBtn = findViewById(R.id.logoutBtn);

        // for Logout button
        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
            }
        });

        // for the Donate button
        donate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // When the donate button is clicked, navigate to DonateActivity
                Intent intent = new Intent(getApplicationContext(), DonateActivity.class);
                startActivity(intent); // Start DonateActivity
            }
        });

        // for the Join button
        join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // When the join button is clicked, navigate to JoinActivity
                Intent intent = new Intent(getApplicationContext(), JoinActivity.class);
                startActivity(intent); // Start JoinActivity
            }
        });

        // for the Event List button
        eventlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // When the event list button is clicked, navigate to CleanUpDriveActivity
                Intent intent = new Intent(getApplicationContext(), CleanUpDriveActivity.class);
                startActivity(intent); // Start CleanUpDriveActivity
            }
        });
    }
}

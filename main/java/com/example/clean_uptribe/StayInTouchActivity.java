package com.example.clean_uptribe;

// Import necessary classes for Android functionality
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class StayInTouchActivity extends AppCompatActivity {

    // Declare EditText fields for user input (name and email)
    EditText sitnameedit, sitemailedit;
    // Declare Button for the subscription action
    Button subscribebutton;

    // Declare the database helper
    StayInTouchDatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stay_in_touch);

        // Linking to their XML IDs (user input fields and button)
        sitnameedit = (EditText) findViewById(R.id.sitnameedt);
        sitemailedit = (EditText) findViewById(R.id.editTextTextEmailAddress);
        subscribebutton = (Button) findViewById(R.id.subscribebtn);

        // Initialize the database helper
        databaseHelper = new StayInTouchDatabaseHelper(this);

        // Set OnClickListener for the Subscribe button
        subscribebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = sitnameedit.getText().toString().trim();
                String email = sitemailedit.getText().toString().trim();

                if (name.isEmpty() || email.isEmpty()) {
                    Toast.makeText(StayInTouchActivity.this, "Please fill in both fields.", Toast.LENGTH_SHORT).show();
                } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    Toast.makeText(StayInTouchActivity.this, "Please enter a valid email address.", Toast.LENGTH_SHORT).show();
                } else {
                    // Save to database
                    long result = databaseHelper.insertSubscription(name, email);

                    if (result != -1) {
                        Toast.makeText(StayInTouchActivity.this, "Subscription successful! Thank you, " + name + ".", Toast.LENGTH_SHORT).show();
                        sitnameedit.setText("");
                        sitemailedit.setText("");

                        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(StayInTouchActivity.this, "Failed to save subscription. Please try again.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }
}

package com.example.clean_uptribe;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class JoinActivity extends AppCompatActivity {

    // Declare EditText fields for name, email, and location
    EditText jnameedit, jemailedit, jlocationedit;

    // Declare CheckBox fields for different environmental issues and volunteer activities
    CheckBox climatechangecheckbox, plasticpollutioncheckbox, deforestationcheckbox, volunteercheckbox, attendcheckbox, hostcheckbox;

    // Declare Button for joining the organization
    Button joinbutton;

    // Declare Database Helper
    JoinDatabaseHelper joinDatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        // Initialize Database Helper
        joinDatabaseHelper = new JoinDatabaseHelper(this);

        // Linking EditText fields to XML IDs
        jnameedit = findViewById(R.id.jnameedt);
        jemailedit = findViewById(R.id.jemailedt);
        jlocationedit = findViewById(R.id.jlocationedt);

        // Linking CheckBox fields to XML IDs for environmental issues
        climatechangecheckbox = findViewById(R.id.climatechangeckb);
        plasticpollutioncheckbox = findViewById(R.id.plasticpollutionckb);
        deforestationcheckbox = findViewById(R.id.deforestationckb);

        // Linking CheckBox fields to XML IDs for volunteer activities
        volunteercheckbox = findViewById(R.id.volunteerckb);
        attendcheckbox = findViewById(R.id.attendckb);
        hostcheckbox = findViewById(R.id.hostckb);

        // Linking Button to XML ID for the join button
        joinbutton = findViewById(R.id.joinbtn1);

        // Set an OnClickListener for the Join button
        joinbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Retrieve the input values from EditText fields
                String name = jnameedit.getText().toString().trim();
                String email = jemailedit.getText().toString().trim();
                String location = jlocationedit.getText().toString().trim();

                // Validation: Check if all fields are filled
                if (name.isEmpty() || email.isEmpty() || location.isEmpty()) {
                    Toast.makeText(JoinActivity.this, "Please fill in all the fields.", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Validate email format
                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    Toast.makeText(JoinActivity.this, "Please enter a valid email address.", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Build a message summarizing the user's interests
                StringBuilder interests = new StringBuilder("Interest:\n");
                if (climatechangecheckbox.isChecked()) interests.append("- Climate Change\n");
                if (plasticpollutioncheckbox.isChecked()) interests.append("- Plastic Pollution\n");
                if (deforestationcheckbox.isChecked()) interests.append("- Deforestation\n");

                // Build a message summarizing the user's contributions
                StringBuilder contributions = new StringBuilder("Contribution:\n");
                if (volunteercheckbox.isChecked()) contributions.append("- Volunteer in person\n");
                if (attendcheckbox.isChecked()) contributions.append("- Attend local events\n");
                if (hostcheckbox.isChecked()) contributions.append("- Host an event\n");

                // Save data to the database
                boolean isInserted = joinDatabaseHelper.insertMember(
                        name,
                        email,
                        location,
                        interests.toString(),
                        contributions.toString()
                );

                // Show a message based on the insertion result
                if (isInserted) {
                    Toast.makeText(JoinActivity.this, "Successfully registered!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(JoinActivity.this, "Failed to register. Try again.", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Clear the form for the next entry
                jnameedit.setText("");
                jemailedit.setText("");
                jlocationedit.setText("");
                climatechangecheckbox.setChecked(false);
                plasticpollutioncheckbox.setChecked(false);
                deforestationcheckbox.setChecked(false);
                volunteercheckbox.setChecked(false);
                attendcheckbox.setChecked(false);
                hostcheckbox.setChecked(false);

                // Navigate to the WelcomeActivity after successful submission
                Intent intent = new Intent(getApplicationContext(), WelcomeActivity.class);
                startActivity(intent);
            }
        });
    }
}

package com.example.clean_uptribe;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class DonateActivity extends AppCompatActivity {

    // Declare EditText fields for user input
    EditText namedonateedt, amountdonateedt, contactnumberedt;

    // Declare Button for donation action
    Button btndonate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_donate); // Set the layout for this activity

        // Link the EditText fields to their corresponding views in the XML
        namedonateedt = findViewById(R.id.namedonate);
        amountdonateedt = findViewById(R.id.amountdonate);
        contactnumberedt = findViewById(R.id.contactnumberdonate);

        // Link the donate button to its corresponding view
        btndonate = findViewById(R.id.buttondonate);

        // Set an OnClickListener for the donate button
        btndonate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Retrieve user input
                String name = namedonateedt.getText().toString();
                String amount = amountdonateedt.getText().toString();
                String contactnumber = contactnumberedt.getText().toString();

                // Validate inputs
                if (name.isEmpty() || amount.isEmpty() || contactnumber.isEmpty()) {
                    Toast.makeText(DonateActivity.this, "Please fill in all fields.", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Validate donation amount
                try {
                    double donationAmount = Double.parseDouble(amount);
                    if (donationAmount <= 0) {
                        Toast.makeText(DonateActivity.this, "Please enter a valid donation amount", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    // Save data into the database
                    DonationDatabaseHelper dbHelper = new DonationDatabaseHelper(DonateActivity.this);
                    boolean isInserted = dbHelper.insertDonation(name, donationAmount, contactnumber);

                    if (isInserted) {
                        Toast.makeText(DonateActivity.this, "Donation recorded successfully!", Toast.LENGTH_SHORT).show();

                        // Optionally proceed to the PaymentActivity
                        Intent intent = new Intent(getApplicationContext(), AcknowledgementActivity.class);
                        intent.putExtra("donationAmount", amount);
                        startActivity(intent);
                    } else {
                        Toast.makeText(DonateActivity.this, "Failed to record donation.", Toast.LENGTH_SHORT).show();
                    }
                } catch (NumberFormatException e) {
                    Toast.makeText(DonateActivity.this, "Donation amount must be a number.", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}

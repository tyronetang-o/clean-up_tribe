package com.example.clean_uptribe;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class AcknowledgementActivity extends AppCompatActivity {

    // Declare TextViews to display donation amount, date, and total
    TextView donationamounttextview, datetextview, totaltextview, thankYouForYourDonationtextview;

    // Declare buttons for navigating to different activities
    Button acknowledgebackButton, stayintouchButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acknowledgement);

        // Link XML views to their Java counterparts
        thankYouForYourDonationtextview = (TextView) findViewById(R.id.thankYouForYourDonationtxtv);
        donationamounttextview = (TextView) findViewById(R.id.donationamounttxv);
        datetextview = (TextView) findViewById(R.id.datetxv);
        totaltextview = (TextView) findViewById(R.id.totaltxv);

        acknowledgebackButton = (Button) findViewById(R.id.acknowledgebackbtn);
        stayintouchButton = (Button) findViewById(R.id.stayintouchbtn);

        // Retrieve the donation amount passed via intent
        Intent intent = getIntent();
        String amountString = intent.getStringExtra("donationAmount");

        if(amountString != null) {
            // Parse the amount string into double and format as currency
            double amount = Double.parseDouble(amountString);
            DecimalFormat currencyFormat = new DecimalFormat("₱#,##0.00");

            // Display the formatted amount in the TextViews
            donationamounttextview.setText(currencyFormat.format(amount));
            totaltextview.setText(currencyFormat.format(amount));
        }

        // Set current date in the specified format
        String currentDate = new SimpleDateFormat("MMMM dd, yyyy", Locale.getDefault()).format(new Date());
        datetextview.setText(currentDate);

        // Set up 'Back' button listener to navigate to HomeActivity
        acknowledgebackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create an Intent to navigate back to the HomeActivity
                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                startActivity(intent); // Start the HomeActivity
            }
        });

        // Set up 'Stay in Touch' button listener to navigate to StayInTouchActivity
        stayintouchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create an Intent to navigate to StayInTouchActivity
                Intent intent = new Intent(getApplicationContext(), StayInTouchActivity.class);
                startActivity(intent); // Start the StayInTouchActivity
            }
        });

        thankYouForYourDonationtextview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                startActivity(intent);
            }
        });

    }
}

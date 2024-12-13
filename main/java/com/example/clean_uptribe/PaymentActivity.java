package com.example.clean_uptribe;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class PaymentActivity extends AppCompatActivity {

    // Declaration of UI components
    private Spinner paymentInstrumentSpinner;
    private Spinner cardTypeSpinner;
    private EditText cardNumberEdt;
    private EditText expirationEdt;
    private EditText verificationEdt;
    private Button continueBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        // Linking UI components to their XML IDs
        paymentInstrumentSpinner = findViewById(R.id.paymentInstrumentSpnr);
        cardTypeSpinner = findViewById(R.id.cardTypeSpnr);
        cardNumberEdt = findViewById(R.id.cardNumberEdt);
        expirationEdt = findViewById(R.id.expirationEdt);
        verificationEdt = findViewById(R.id.verificationEdt);
        continueBtn = findViewById(R.id.continueBtn);

        // Populate the payment instrument spinner with available options
        ArrayAdapter<CharSequence> paymentInstrumentAddapter = ArrayAdapter.createFromResource(
                this,
                R.array.payment_instruments,
                android.R.layout.simple_spinner_item // Default spinner item layout
        );
        paymentInstrumentAddapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        paymentInstrumentSpinner.setAdapter(paymentInstrumentAddapter);

        // Populate the card type spinner with available options
        ArrayAdapter<CharSequence> cardTypeAdapter = ArrayAdapter.createFromResource(
                this,
                R.array.card_types,
                android.R.layout.simple_spinner_item
        );
        cardTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        cardTypeSpinner.setAdapter(cardTypeAdapter);

        // Set listener for continue button
        continueBtn.setOnClickListener(view -> {
            // Retrieve user inputs for card number, expiration date, and verification code
            String cardNumber = cardNumberEdt.getText().toString();
            String expirationDate = expirationEdt.getText().toString();
            String verificationCode = verificationEdt.getText().toString();

            // Validate that all fields are filled
            if (cardNumber.isEmpty() || expirationDate.isEmpty() || verificationCode.isEmpty()) {
                // Show error message if any field is empty
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            } else {
                // Show success message after validation
                Toast.makeText(this, "Payment Processed", Toast.LENGTH_SHORT).show();

                // Retrieve the donation amount from the previous activity (DonateActivity)
                Intent intent = getIntent();
                String amount = intent.getStringExtra("donationAmount");

                // Validate that a donation amount was passed from the previous activity
                if (amount != null) {
                    // Pass the donation amount to the AcknowledgementActivity
                    Intent intentToAcknowledgement = new Intent(getApplicationContext(), AcknowledgementActivity.class);
                    intentToAcknowledgement.putExtra("donationAmount", amount);
                    startActivity(intentToAcknowledgement);
                } else {
                    // Show error if no donation amount is found
                    Toast.makeText(this, "Error: Donation amount not found", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}

package com.example.clean_uptribe;

import android.app.AlertDialog;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class DonationHistory extends AppCompatActivity {

    private RecyclerView donationHistoryRecyclerView;
    private DonationAdapter donationAdapter;
    private List<DonationModel> donationList;
    private DonationDatabaseHelper donationDatabaseHelper;
    private Button btnClearHistory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donation_history);

        donationHistoryRecyclerView = findViewById(R.id.donationHistoryRecyclerView);
        btnClearHistory = findViewById(R.id.btnClearHistory);

        donationDatabaseHelper = new DonationDatabaseHelper(this);
        donationList = new ArrayList<>();

        // Fetch data from the database
        fetchDonationHistory();

        // Set up RecyclerView
        donationAdapter = new DonationAdapter(this, donationList);
        donationHistoryRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        donationHistoryRecyclerView.setAdapter(donationAdapter);

        // Handle "Clear Donation History" button click
        btnClearHistory.setOnClickListener(view -> clearDonationHistory());
    }

    private void fetchDonationHistory() {
        Cursor cursor = donationDatabaseHelper.getAllDonations();
        if (cursor != null) {
            while (cursor.moveToNext()) {
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                double amount = cursor.getDouble(2);
                String contactNumber = cursor.getString(3);

                donationList.add(0, new DonationModel(id, name, amount, contactNumber));
            }
            cursor.close();
        }
    }

    private void clearDonationHistory() {
        // Show confirmation dialog
        new AlertDialog.Builder(this)
                .setTitle("Clear Donation History")
                .setMessage("Are you sure you want to clear all donation history? This action cannot be undone.")
                .setPositiveButton("Yes", (dialog, which) -> {
                    // Perform the clear operation
                    SQLiteDatabase db = donationDatabaseHelper.getWritableDatabase();
                    db.delete("donations", null, null); // Clear all data
                    donationList.clear(); // Clear the list
                    donationAdapter.notifyDataSetChanged(); // Notify adapter
                    Toast.makeText(this, "Donation history cleared!", Toast.LENGTH_SHORT).show();
                })
                .setNegativeButton("Cancel", (dialog, which) -> {
                    // Dismiss the dialog
                    dialog.dismiss();
                })
                .show();
    }

}

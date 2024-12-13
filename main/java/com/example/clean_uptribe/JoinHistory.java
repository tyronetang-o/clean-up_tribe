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

public class JoinHistory extends AppCompatActivity {
    private RecyclerView joinHistoryRecyclerView;
    private JoinAdapter joinAdapter;
    private List<JoinModel> joinList;
    private JoinDatabaseHelper joinDatabaseHelper;

    private Button btnClearHistory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_history);

        joinHistoryRecyclerView = findViewById(R.id.joinHistoryRecyclerView);
        joinDatabaseHelper = new JoinDatabaseHelper(this);
        joinList = new ArrayList<>();

        btnClearHistory = findViewById(R.id.btnClearHistory);

        // Fetch join history data from the database
        fetchJoinHistory();

        // Set up RecyclerView
        joinAdapter = new JoinAdapter(this, joinList);
        joinHistoryRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        joinHistoryRecyclerView.setAdapter(joinAdapter);

        btnClearHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearJoinHistory();
            }
        });
    }


    private void fetchJoinHistory() {
        Cursor cursor = joinDatabaseHelper.getAllMembers();
        if (cursor != null) {
            while (cursor.moveToNext()) {
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                String email = cursor.getString(2);
                String location = cursor.getString(3);
                String interests = cursor.getString(4);
                String contributions = cursor.getString(5);

                joinList.add(0, new JoinModel(id, name, email, location, interests, contributions));
            }
            cursor.close();
        }
    }
    private void clearJoinHistory() {
        // Show confirmation dialog
        new AlertDialog.Builder(this)
                .setTitle("Clear Join History")
                .setMessage("Are you sure you want to clear all Join history? This action cannot be undone.")
                .setPositiveButton("Yes", (dialog, which) -> {
                    // Perform the clear operation
                    SQLiteDatabase db = joinDatabaseHelper.getWritableDatabase(); // Use the correct database helper
                    db.delete("join_members", null, null); // Clear all data in the join_members table
                    joinList.clear(); // Clear the list
                    joinAdapter.notifyDataSetChanged(); // Notify adapter
                    Toast.makeText(this, "Join history cleared!", Toast.LENGTH_SHORT).show();
                })
                .setNegativeButton("Cancel", (dialog, which) -> {
                    // Dismiss the dialog
                    dialog.dismiss();
                })
                .show();
    }



}

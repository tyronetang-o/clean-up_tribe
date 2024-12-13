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

public class ArchiveActivity extends AppCompatActivity {

    private RecyclerView archiveRecyclerView;
    private ArchiveAdapter archiveAdapter;
    private List<EventModel> archivedEventList;
    private ArchiveEventDBHelper archiveEventDBHelper;
    private Button btnClearHistory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_archive);

        archiveRecyclerView = findViewById(R.id.archiveRecyclerView);
        archiveEventDBHelper = new ArchiveEventDBHelper(this);

        archivedEventList = new ArrayList<>();
        fetchArchivedEvents();

        btnClearHistory = findViewById(R.id.btnClearHistory);

        archiveAdapter = new ArchiveAdapter(this, archivedEventList);
        archiveRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        archiveRecyclerView.setAdapter(archiveAdapter);

        btnClearHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearArchive();
            }
        });
    }

    private void fetchArchivedEvents() {
        Cursor cursor = archiveEventDBHelper.getAllArchivedEvents();
        if (cursor != null) {
            while (cursor.moveToNext()) {
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                String location = cursor.getString(2);
                String date = cursor.getString(3);
                String time = cursor.getString(4);
                String description = cursor.getString(5);

                archivedEventList.add(0, new EventModel(id, name, location, date, time, description));
            }
            cursor.close();
        } else {
            Toast.makeText(this, "No Archived Events Found", Toast.LENGTH_SHORT).show();
        }
    }
    private void clearArchive() {
        // Show confirmation dialog
        new AlertDialog.Builder(this)
                .setTitle("Clear Donation History")
                .setMessage("Are you sure you want to clear all donation history? This action cannot be undone.")
                .setPositiveButton("Yes", (dialog, which) -> {
                    // Perform the clear operation
                    SQLiteDatabase db = archiveEventDBHelper.getWritableDatabase();
                    db.delete("ArchivedEvents", null, null); // Clear all data
                    archivedEventList.clear(); // Clear the list
                    archiveAdapter.notifyDataSetChanged(); // Notify adapter
                    Toast.makeText(this, "Donation history cleared!", Toast.LENGTH_SHORT).show();
                })
                .setNegativeButton("Cancel", (dialog, which) -> {
                    // Dismiss the dialog
                    dialog.dismiss();
                })
                .show();
    }
}

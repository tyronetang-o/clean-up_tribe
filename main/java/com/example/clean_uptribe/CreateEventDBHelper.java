package com.example.clean_uptribe;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CreateEventDBHelper extends SQLiteOpenHelper {

    // Database Information
    private static final String DATABASE_NAME = "CleanUpTribeEvents.db";
    private static final int DATABASE_VERSION = 1;

    // Table and Columns
    public static final String TABLE_EVENTS = "Events";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_EVENT_NAME = "event_name";
    public static final String COLUMN_LOCATION = "location";
    public static final String COLUMN_DATE = "date";
    public static final String COLUMN_TIME = "time";
    public static final String COLUMN_DESCRIPTION = "description";

    // SQL to Create Table
    private static final String CREATE_TABLE_EVENTS =
            "CREATE TABLE " + TABLE_EVENTS + "("
                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + COLUMN_EVENT_NAME + " TEXT, "
                    + COLUMN_LOCATION + " TEXT, "
                    + COLUMN_DATE + " TEXT, "
                    + COLUMN_TIME + " TEXT, "
                    + COLUMN_DESCRIPTION + " TEXT"
                    + ")";

    public CreateEventDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_EVENTS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EVENTS);
        onCreate(db);
    }

    // Insert Event
    public boolean insertEvent(String eventName, String location, String date, String time, String description) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_EVENT_NAME, eventName);
        values.put(COLUMN_LOCATION, location);
        values.put(COLUMN_DATE, date);
        values.put(COLUMN_TIME, time);
        values.put(COLUMN_DESCRIPTION, description);

        long result = db.insert(TABLE_EVENTS, null, values);
        db.close();
        return result != -1; // Return true if insertion was successful
    }

    // Fetch all events
    public Cursor getAllEvents() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_EVENTS, null);
    }
    public boolean updateEvent(int id, String eventName, String location, String date, String time, String description) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_EVENT_NAME, eventName);
        contentValues.put(COLUMN_LOCATION, location);
        contentValues.put(COLUMN_DATE, date);
        contentValues.put(COLUMN_TIME, time);
        contentValues.put(COLUMN_DESCRIPTION, description);

        int result = db.update(TABLE_EVENTS, contentValues, COLUMN_ID + " = ?", new String[]{String.valueOf(id)});
        return result > 0; // Returns true if at least one row is updated
    }
    public boolean deleteEvent(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        int result = db.delete(TABLE_EVENTS, COLUMN_ID + " = ?", new String[]{String.valueOf(id)});
        return result > 0; // Returns true if at least one row is deleted
    }

}

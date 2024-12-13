package com.example.clean_uptribe;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ArchiveEventDBHelper extends SQLiteOpenHelper {

    // Database Info
    private static final String DATABASE_NAME = "ArchivedEvents.db";
    private static final int DATABASE_VERSION = 1;

    // Table and Columns
    public static final String TABLE_ARCHIVED_EVENTS = "ArchivedEvents";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_EVENT_NAME = "event_name";
    public static final String COLUMN_LOCATION = "location";
    public static final String COLUMN_DATE = "date";
    public static final String COLUMN_TIME = "time";
    public static final String COLUMN_DESCRIPTION = "description";

    // SQL to Create Table
    private static final String CREATE_TABLE_ARCHIVED_EVENTS =
            "CREATE TABLE " + TABLE_ARCHIVED_EVENTS + "("
                    + COLUMN_ID + " INTEGER PRIMARY KEY, "
                    + COLUMN_EVENT_NAME + " TEXT, "
                    + COLUMN_LOCATION + " TEXT, "
                    + COLUMN_DATE + " TEXT, "
                    + COLUMN_TIME + " TEXT, "
                    + COLUMN_DESCRIPTION + " TEXT"
                    + ")";

    public ArchiveEventDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_ARCHIVED_EVENTS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ARCHIVED_EVENTS);
        onCreate(db);
    }

    // Insert Archived Event
    public boolean insertArchivedEvent(int id, String eventName, String location, String date, String time, String description) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, id); // Keep the original event ID
        values.put(COLUMN_EVENT_NAME, eventName);
        values.put(COLUMN_LOCATION, location);
        values.put(COLUMN_DATE, date);
        values.put(COLUMN_TIME, time);
        values.put(COLUMN_DESCRIPTION, description);

        long result = db.insert(TABLE_ARCHIVED_EVENTS, null, values);
        db.close();
        return result != -1;
    }

    // Fetch Archived Events
    public Cursor getAllArchivedEvents() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_ARCHIVED_EVENTS, null);
    }
}

package com.example.clean_uptribe;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    // Define the database name
    public static final String DBNAME = "Login.db";

    // Constructor for DBHelper class
    public DBHelper(Context context) {
        // Call the superclass constructor to create the database with the given name and version
        super(context, "Login.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        // Create the "users" table with columns: username (primary key) and password
        MyDB.execSQL("create Table users(username TEXT primary key, password TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
        // Drop the "users" table if it exists (for upgrading the database)
        MyDB.execSQL("drop Table if exists users");
        onCreate(MyDB); // Recreate the table
    }

    // Method to insert new user data into the "users" table
    public Boolean insertData(String username, String password) {
        SQLiteDatabase MyDB = this.getWritableDatabase(); // Get writable database for inserting data
        ContentValues contentValues = new ContentValues(); // Create a ContentValues object to hold key-value pairs

        // Put the data (username and password) into the contentValues
        contentValues.put("username", username);
        contentValues.put("password", password);

        // Insert the data into the "users" table
        long result = MyDB.insert("users", null, contentValues);

        // Return true if the insert operation was successful (i.e., the result is not -1)
        return result != -1;
    }

    // Method to check if the username already exists in the database
    public Boolean checkusername(String username) {
        SQLiteDatabase MyDB = this.getWritableDatabase(); // Get writable database

        // Query the database to check if the username exists
        Cursor cursor = MyDB.rawQuery("Select * from users where username = ?", new String[]{username});

        // Return true if the username is found in the database
        return cursor.getCount() > 0;
    }

    // Method to check if the username and password combination is valid
    public Boolean checkusernamepassword(String username, String password) {
        SQLiteDatabase MyDB = this.getWritableDatabase(); // Get writable database

        // Query the database to check if the username and password combination is correct
        Cursor cursor = MyDB.rawQuery("Select * from users where username = ? and password = ?", new String[]{username, password});

        // Return true if the username and password match
        return cursor.getCount() > 0;
    }
}

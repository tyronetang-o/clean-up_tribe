package com.example.clean_uptribe;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    // Declare Image view for PasswordToggle
    ImageView passwordToggle;

    // Declare EditText fields for username and password
    EditText username, password;

    // Declare Button for login
    Button btnlogin;

    // Admin username and password for admin login
    private static final String ADMIN_USERNAME = "admin";
    private static final String ADMIN_PASSWORD = "admin123";
    private static final String ADMIN_ROLE = "admin";

    // Database helper to interact with the SQLite database
    DBHelper DB;

    // Flag to track password visibility
    private boolean isPasswordVisible = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Linking ImageView fields for passwordtoogle
        passwordToggle = findViewById(R.id.passwordToggle);

        // Linking EditText fields to XML IDs
        username = findViewById(R.id.username1);
        password = findViewById(R.id.password1);

        // Linking Button to XML ID for the login button
        btnlogin = findViewById(R.id.btnsignin1);

        // Initialize the DBHelper instance to interact with the database
        DB = new DBHelper(this);

        // Set the initial icon for the password toggle
        passwordToggle.setImageResource(R.drawable.password_hide);

        // Set an OnClickListener for the password toggle functionality
        passwordToggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                togglePasswordVisibility();
            }
        });

        // Set an OnClickListener for the login button
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Retrieve input values for username and password
                String user = username.getText().toString();
                String pass = password.getText().toString();

                // Check if any of the fields are empty
                if (user.equals("") || pass.equals("")) {
                    // Show a toast message if fields are empty
                    Toast.makeText(LoginActivity.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                } else {
                    // Check if the entered username and password match admin credentials
                    if (user.equals(ADMIN_USERNAME) && pass.equals(ADMIN_PASSWORD)) {
                        // If admin credentials are valid, go to CreateCleanUpEventActivity
                        Toast.makeText(LoginActivity.this, "Admin login successful", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(LoginActivity.this, CreateCleanUpEventActivity.class);
                        intent.putExtra("role", ADMIN_ROLE);
                        startActivity(intent);
                    } else {
                        // If not admin, check if the entered username and password match any record in the database
                        Boolean checkuserpass = DB.checkusernamepassword(user, pass);
                        if (checkuserpass) {
                            // If credentials are valid, show success message and navigate to HomeActivity
                            Toast.makeText(LoginActivity.this, "Sign in successful", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                            startActivity(intent);
                        } else {
                            // If credentials are invalid, show an error message
                            Toast.makeText(LoginActivity.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });
    }

    //Toggles the visibility of the password field
    private void togglePasswordVisibility() {
        if (isPasswordVisible) {
            // If password is visible, hide it
            password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            passwordToggle.setImageResource(R.drawable.password_hide); // Set the icon for hide password
        } else {
            // If password is hidden, show it
            password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
            passwordToggle.setImageResource(R.drawable.password_show); // Set the icon for show password
        }
        // Move cursor to the end of the text
        password.setSelection(password.getText().length());
        isPasswordVisible = !isPasswordVisible;
    }
}

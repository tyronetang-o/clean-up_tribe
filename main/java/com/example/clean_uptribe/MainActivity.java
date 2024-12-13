package com.example.clean_uptribe;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    // Declare EditText fields for username, password, and confirm password
    EditText username, password, repassword;
    // Declare Button for signup and signin
    Button signup, signin;
    // Database helper to interact with the SQLite database
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Linking EditText fields to their XML IDs
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        repassword = findViewById(R.id.repassword);
        // Linking Buttons to their XML IDs
        signup = findViewById(R.id.btnsignup);
        signin = findViewById(R.id.btnsignin);
        // Initialize the DBHelper instance to interact with the database
        DB = new DBHelper(this);

        // Set an OnClickListener for the signup button
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Retrieve input values for username, password, and confirm password
                String user = username.getText().toString();
                String pass = password.getText().toString();
                String repass = repassword.getText().toString();

                // Check if any field is empty
                if (user.equals("") || pass.equals("") || repass.equals("")) {
                    // Show a toast message if any field is empty
                    Toast.makeText(MainActivity.this,
                            "Please enter all the fields",
                            Toast.LENGTH_SHORT).show();
                    // Validate email format
                } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(user).matches()) {
                    // Show a toast message if email format is invalid
                    Toast.makeText(MainActivity.this,
                            "Please enter a valid email address.",
                            Toast.LENGTH_SHORT).show();
                } else {
                    // If passwords match
                    if (pass.equals(repass)) {
                        // Check if the username already exists in the database
                        Boolean checkuser = DB.checkusername(user);
                        if (checkuser == false) {
                            // If user doesn't exist, insert new user data into the database
                            Boolean insert = DB.insertData(user, pass);
                            if (insert == true) {
                                // Show success message and navigate to HomeActivity
                                Toast.makeText(MainActivity.this,
                                        "Registered successfully",
                                        Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(),
                                        HomeActivity.class);
                                startActivity(intent);
                            } else {
                                // Show failure message if registration fails
                                Toast.makeText(MainActivity.this,
                                        "Registration failed",
                                        Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            // If the username already exists, show a message to the user
                            Toast.makeText(MainActivity.this,
                                    "User already exists! Please sign in",
                                    Toast.LENGTH_LONG).show();
                        }
                    } else {
                        // If passwords don't match, show a message to the user
                        Toast.makeText(MainActivity.this,
                                "Passwords do not match",
                                Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        // Set an OnClickListener for the signin button
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Navigate to LoginActivity when the signin button is clicked
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}

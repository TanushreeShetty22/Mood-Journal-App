package com.example.moodjournal;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SignUp extends AppCompatActivity {

    private EditText usernameField;
    private EditText emailField;
    private EditText passwordField;
    private Button signupButton;
    private TextView loginText;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        usernameField = findViewById(R.id.username_field);
        emailField = findViewById(R.id.email_field);
        passwordField = findViewById(R.id.password_field);
        signupButton = findViewById(R.id.signup_button);
        loginText = findViewById(R.id.login_text);

        loginText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameField.getText().toString();
                String email = emailField.getText().toString();
                String password = passwordField.getText().toString();

                // Replace with your sign-up logic and handle errors
                if (signup(username, email, password)) {
                    // Start main activity or other appropriate action
                   goToHomePage();
                } else {
                    // Show error message to user
                    Toast.makeText(SignUp.this, "Invalid Details", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    // Implement your sign-up logic here
    private boolean signup(String username, String email, String password) {
        // ... save user data and handle sign-up process
        return true; // Replace with actual logic
    }
    private void login(){
        startActivity(new Intent(this,Login.class));
        finish();
    }

    private void goToHomePage() {
        // Implement the logic to start the main activity or navigate to the home page
        startActivity(new Intent(SignUp.this, WelcomePage.class));
        // Make sure to finish the current activity if you don't want to come back to it when pressing the back button
        finish();
    }

}
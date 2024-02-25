package com.example.moodjournal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class Login extends AppCompatActivity {

    private EditText emailField;
    private EditText passwordField;
    private Button loginButton;
    private TextView signupText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailField = findViewById(R.id.email_field);
        passwordField = findViewById(R.id.password_field);
        loginButton = findViewById(R.id.login_button);
        signupText = findViewById(R.id.signup_text); // Assuming you have a TextView with an ID "signup_text" in your layout

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailField.getText().toString().trim();
                String password = passwordField.getText().toString();

                if (isValidUsername(email) && isValidPassword(password)) {
                    // Proceed with login
                    if (login(email, password)) {
                        // Start main activity or other appropriate action
                        goToHomePage();
                    } else {
                        // Show error message to user for login failure
                        showError("Invalid credentials. Please try again.");
                    }
                } else {
                    // Show error message to user for invalid input
                    showError("Invalid username or password. Please check the requirements.");
                    // Additionally, show signup text and link
                    showSignupText();
                }
            }
        });
        signupText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSignupText();
            }
        });
    }

    private boolean isValidUsername(String username) {
        // Username should be alpha or alphanumeric
        return username.matches("[a-zA-Z0-9]+");
    }

    private boolean isValidPassword(String password) {
        // Password should be between 6-16 characters, alphanumeric, and contain at least one special character
        return password.length() >= 6 && password.length() <= 16 && password.matches("^(?=.*[a-zA-Z0-9])(?=.*[@#$%^&+=]).+$");
    }

    private boolean login(String username, String password) {
        // ... check credentials against your user database or authentication service
        return true; // Replace with actual logic
    }

    private void goToHomePage() {
        // Implement the logic to start the main activity or navigate to the home page
        startActivity(new Intent(Login.this, WelcomePage.class));
    }

    private void showError(String errorMessage) {
        // Implement the logic to show an error message to the user
        // You can use Toast or any other UI element to display the error message
        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show();
    }


    private void showSignupText() {
        // Implement the logic to show the text message with a signup link
        signupText.setVisibility(View.VISIBLE);
        // Set an OnClickListener for the signup text to navigate to the signup activity
        signupText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Implement the logic to navigate to the signup activity
                startActivity(new Intent(Login.this, SignUp.class));
            }
        });
    }
}

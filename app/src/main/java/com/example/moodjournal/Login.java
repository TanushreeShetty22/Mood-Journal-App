package com.example.moodjournal;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class Login extends AppCompatActivity {

    private EditText emailField, passwordField;
    private Button loginButton;
    private TextView signupText;
    private FirebaseAuth mAuth;
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            goToWelcomePage();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailField = findViewById(R.id.email_field);
        passwordField = findViewById(R.id.password_field);
        loginButton = findViewById(R.id.login_button);
        signupText = findViewById(R.id.signup_text);
        // Initialize the FirebaseAuth object
        mAuth = FirebaseAuth.getInstance();

        loginButton.setOnClickListener(new View.OnClickListener() {




            @Override
            public void onClick(View v) {
                String email = emailField.getText().toString().trim();
                String password = passwordField.getText().toString();

                if (isValidEmail(email) && isValidPassword(password)) {
                    mAuth.signInWithEmailAndPassword(email, password)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Sign in success, update UI with the signed-in user's information

                                        FirebaseUser user = mAuth.getCurrentUser();

                                    } else {
                                        // If sign in fails, display a message to the user.

                                        Toast.makeText(Login.this, "Authentication failed.",
                                                Toast.LENGTH_SHORT).show();

                                    }
                                }
                            });
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

    private boolean isValidEmail(String email) {
        // Email should be in a valid email format
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private boolean isValidPassword(String password) {
        // Password should be between 6-16 characters, alphanumeric, and contain at least one special character
        return password.length() >= 6 && password.length() <= 16 && password.matches("^(?=.*[a-zA-Z0-9])(?=.*[@#$%^&+=]).+$");
    }

    private void goToWelcomePage() {
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

        }
        );}
}
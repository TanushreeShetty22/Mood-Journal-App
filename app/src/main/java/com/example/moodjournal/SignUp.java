package com.example.moodjournal;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
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

public class SignUp extends AppCompatActivity {

    private EditText emailField;
    private EditText passwordField;
    private Button signupButton;
    private TextView loginText;

    FirebaseAuth mAuth;
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            goToWelcomePage();
        }
    }
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);


        emailField = findViewById(R.id.email_field);
        passwordField = findViewById(R.id.password_field);
        signupButton = findViewById(R.id.signup_button);
        loginText = findViewById(R.id.login_text);
        mAuth=FirebaseAuth.getInstance();
        loginText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = emailField.getText().toString().trim();
                String password = passwordField.getText().toString().trim();

                if (isValidEmail(email) && isValidPassword(password)) {
                    // Show error message to user
                    Toast.makeText(SignUp.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                    return;
                }
                // Replace with your sign-up logic and handle errors
                if (signup(email, password)) {
                    // Start main activity or other appropriate action
                   goToWelcomePage();
                }else {
                    // Show error message to user for invalid input
                    showError("Invalid username or password. Please check the requirements.");
                    // Additionally, show signup text and link
                }

                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(SignUp.this, "Account Created.",
                                            Toast.LENGTH_SHORT).show(); // Sign in success, update UI with the signed-in user's information
                                            Intent intent = new Intent(getApplicationContext(), Login.class);
                                            startActivity(intent);
                                } else {
                                    // If sign in fails, display a message to the user.

                                    Toast.makeText(SignUp.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();

                                }
                            }
                        });
            }
        });
    }

    // Implement your sign-up logic here
    private boolean signup(String username, String email) {
        // ... save user data and handle sign-up process
        return true; // Replace with actual logic
    }
    private void login(){
        startActivity(new Intent(this,Login.class));
        finish();
    }

    private void goToWelcomePage() {
        // Implement the logic to start the main activity or navigate to the home page
        startActivity(new Intent(SignUp.this, WelcomePage.class));
        // Make sure to finish the current activity if you don't want to come back to it when pressing the back button
        finish();
    }
    private boolean isValidEmail(String username) {
        // Username should be alpha or alphanumeric
        return username.matches("[a-zA-Z0-9]+");
    }

    private boolean isValidPassword(String password) {
        // Password should be between 6-16 characters, alphanumeric, and contain at least one special character
        return password.length() >= 6 && password.length() <= 16 && password.matches("^(?=.*[a-zA-Z0-9])(?=.*[@#$%^&+=]).+$");
    }
    private void showError(String errorMessage) {
        // Implement the logic to show an error message to the user
        // You can use Toast or any other UI element to display the error message
        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show();
    }


}
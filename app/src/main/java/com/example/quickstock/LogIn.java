package com.example.quickstock;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class LogIn extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Ensure this matches your activity_log_in.xml file name
        setContentView(R.layout.activity_log_in);

        // 1. BACK BUTTON: Navigates back to the 'Account' selection page
        ImageButton btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LogIn.this, Account.class);
                startActivity(intent);
                finish();
            }
        });

        // 2. CREATE ACCOUNT LINK: Opens your Sign In (Registration) UI
        TextView createAccountText = findViewById(R.id.btnCreateAccount);
        createAccountText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LogIn.this, SignIn.class);
                startActivity(intent);
            }
        });

        // 3. ORANGE SIGN IN BUTTON: Integrated logic to pass Name and Location
        final EditText etStoreName = findViewById(R.id.etStoreName);
        final EditText etPassword = findViewById(R.id.etPassword);
        Button btnSignIn = findViewById(R.id.btnSignIn);

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String storeName = etStoreName.getText().toString().trim();
                String password = etPassword.getText().toString().trim();

                if (storeName.isEmpty() || password.isEmpty()) {
                    Toast.makeText(LogIn.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                } else {
                    // Create the bridge to the Dashboard
                    Intent intent = new Intent(LogIn.this, Dashboard.class);

                    // Pass the Store Name entered by the user
                    intent.putExtra("STORE_NAME_KEY", storeName);

                    // Pass a placeholder Location (this will eventually come from your database)
                    intent.putExtra("LOCATION_KEY", "Dasmariñas, Cavite");

                    startActivity(intent);
                    finish(); // Prevents user from going back to login screen after success
                }
            }
        });
    }
}
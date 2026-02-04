package com.example.quickstock;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView; // Import TextView
import android.content.Intent;

public class LogIn extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        // Find the back button
        ImageButton btnBack = findViewById(R.id.btnBack);

        // Set an OnClickListener on the back button
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an Intent to navigate back to the Account activity
                Intent intent = new Intent(LogIn.this, Account.class);
                startActivity(intent);
                finish();  // Optional: To finish the current activity and remove it from the stack
            }
        });

        // Find the "Create an account" TextView by ID
        TextView createAccountText = findViewById(R.id.tvCreateAccount);

        createAccountText.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LogIn.this, SignIn.class); // Replace SignIn.class with the appropriate class name
                startActivity(intent);  // Start the Sign In activity
            }
        });
    }
}

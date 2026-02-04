package com.example.quickstock;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;  // Import LinearLayout for the button
import androidx.appcompat.app.AppCompatActivity;

public class Account extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);  // Set the account layout

        // Find the "Continue as Store" button by ID
        LinearLayout btnStore = findViewById(R.id.btnStore);

        // Set an OnClickListener for the "Continue as Store" button
        btnStore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the LogIn activity when the button is clicked
                Intent intent = new Intent(Account.this, LogIn.class);
                startActivity(intent);  // Start the LogIn activity
                finish();  // Close the Account activity so the user can't go back
            }
        });

        // Find the "Continue as Guest" button by ID
        LinearLayout btnGuest = findViewById(R.id.btnGuest);

        // Set an OnClickListener for the "Continue as Guest" button
        btnGuest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the Guest activity when the button is clicked
                Intent intent = new Intent(Account.this, Guest.class);  // Make sure to use the Guest activity
                startActivity(intent);  // Start the Guest activity
                finish();  // Close the Account activity so the user can't go back
            }
        });
    }
}

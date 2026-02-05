package com.example.quickstock;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.ImageButton;  // Import the ImageButton

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Guest extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);  // If you're using Edge to Edge UI
        setContentView(R.layout.activity_guest);

        // Set up window insets to handle system bars (optional)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Get the Spinners (Dropdowns)
        Spinner spinnerBarangay = findViewById(R.id.spinnerBarangay);
        Spinner spinnerStores = findViewById(R.id.spinnerStores);

        // Define data for Barangay Spinner (dropdown)
        String[] barangays = {"Place your Barangay", "Barangay 1", "Barangay 2", "Barangay 3", "Barangay 4"};

        // Define data for Stores Spinner (dropdown)
        String[] stores = {"Place your Store", "Store A", "Store B", "Store C", "Store D"};

        // Create ArrayAdapters for each Spinner
        ArrayAdapter<String> barangayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, barangays);
        barangayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerBarangay.setAdapter(barangayAdapter);

        ArrayAdapter<String> storesAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, stores);
        storesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerStores.setAdapter(storesAdapter);

        // Find the back button by ID
        ImageButton btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Logic to go back to a activity
                finish();  // This will close the current activity and return to the previous one
            }
        });


        // Set an OnClickListener for the back button
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an Intent to navigate back to the Account activity
                Intent intent = new Intent(Guest.this, Account.class);  // Account is the target activity
                startActivity(intent);  // Start the Account activity
                finish();  // Close the Guest activity so the user can't go back to it
            }
        });
    }
}

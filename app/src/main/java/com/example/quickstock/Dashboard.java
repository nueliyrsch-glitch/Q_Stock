package com.example.quickstock;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class Dashboard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set the content view to activity_dashboard.xml
        setContentView(R.layout.activity_dashboard);

        // 1. Link the TextViews from the XML to display store name and location
        TextView tvDashboardStoreName = findViewById(R.id.tvDashboardStoreName);
        TextView tvDashboardLocation = findViewById(R.id.tvDashboardLocation);

        // 2. Get the data sent from LogIn.java
        String name = getIntent().getStringExtra("STORE_NAME_KEY");
        String location = getIntent().getStringExtra("LOCATION_KEY");

        // 3. Update the UI text if the data exists
        if (name != null && !name.isEmpty()) {
            tvDashboardStoreName.setText(name);
        }

        if (location != null && !location.isEmpty()) {
            tvDashboardLocation.setText(location);
        }

        // 4. Back Button Logic - Navigate to LogIn activity
        ImageButton btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v -> {
            // When the back button is clicked, go back to the LogIn activity
            Intent intent = new Intent(Dashboard.this, LogIn.class);
            startActivity(intent);
            finish(); // Finish the current Dashboard activity
        });

        // 5. Inventory Button Logic - Navigate to Inventory activity
        LinearLayout btnInventory = findViewById(R.id.btnInventory); // Ensure the correct ID for the inventory button
        btnInventory.setOnClickListener(v -> {
            // When Inventory icon is clicked, navigate to the Inventory activity
            Intent intent = new Intent(Dashboard.this, Inventory.class);
            startActivity(intent);
        });
    }
}

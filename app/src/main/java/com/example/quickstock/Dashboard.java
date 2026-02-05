package com.example.quickstock;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout; // Ensure this is imported for the buttons
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class Dashboard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Ensure this matches your activity_dashboard.xml
        setContentView(R.layout.activity_dashboard);

        // 1. Link the TextViews for Store Name and Location
        TextView tvDashboardStoreName = findViewById(R.id.tvDashboardStoreName);
        TextView tvDashboardLocation = findViewById(R.id.tvDashboardLocation);

        // 2. Set dynamic Store Name and fixed Location
        String name = getIntent().getStringExtra("STORE_NAME_KEY");
        if (name != null && !name.isEmpty()) {
            tvDashboardStoreName.setText(name);
        }
        tvDashboardLocation.setText("Dasma");

        // 3. INVENTORY BUTTON LOGIC: Navigate to Inventory UI
        // This links to the LinearLayout with id: btnInventory
        // Inside Dashboard.java onCreate
        LinearLayout btnInventory = findViewById(R.id.btnInventory);
        btnInventory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Starts the Inventory Activity
                Intent intent = new Intent(Dashboard.this, Inventory.class);
                startActivity(intent);
            }
        });

        // 4. Back Button Logic
        ImageButton btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Dashboard.this, LogIn.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
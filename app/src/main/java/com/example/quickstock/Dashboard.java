package com.example.quickstock;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class Dashboard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Ensure this matches your activity_dashboard.xml
        setContentView(R.layout.activity_dashboard);

        // 1. Link the TextViews from the XML
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

        // 4. Back Button Logic
        ImageButton btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Dashboard.this, LogIn.class);
                startActivity(intent);
                finish(); // Removes Dashboard from the stack
            }
        });
    }
}
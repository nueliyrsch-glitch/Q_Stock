package com.example.quickstock;

import android.content.Intent; // Required for navigating between screens
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton; // Required to reference your back button
import androidx.appcompat.app.AppCompatActivity;

public class Dashboard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Ensure this matches your activity_dashboard.xml filename
        setContentView(R.layout.activity_dashboard);

        // 1. Find the Back Button by the ID you set in the XML
        ImageButton btnBack = findViewById(R.id.btnBack);

        // 2. Set the Click Listener to navigate back to LogIn
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // This intent tells Android to move from Dashboard to LogIn
                Intent intent = new Intent(Dashboard.this, LogIn.class);
                startActivity(intent);

                // finish() removes Dashboard from the "back stack" so the user
                // doesn't accidentally return here after logging out.
                finish();
            }
        });
    }
}
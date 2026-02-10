package com.example.quickstock;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;

public class settings extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings); // Ensure this points to the correct layout

        // Find the custom back button and set its OnClickListener
        ImageButton btnBackSettings = findViewById(R.id.btnBackSettings);  // Button from XML
        btnBackSettings.setOnClickListener(v -> {
            // Navigate back to Dashboard when the back button is clicked
            Intent intent = new Intent(settings.this, Dashboard.class);
            startActivity(intent);
            finish();  // Optional, finish this activity to remove it from the stack
        });
    }
}

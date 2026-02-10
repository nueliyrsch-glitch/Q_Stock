package com.example.quickstock;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;

public class supplier extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supplier);  // This is your Supplier layout

        // Find the back button and set an OnClickListener
        ImageView btnBack = findViewById(R.id.btnBack);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // When the back button is clicked, navigate to the Dashboard activity
                Intent intent = new Intent(supplier.this, Dashboard.class);
                startActivity(intent);
                finish();  // Close this activity and go back to the Dashboard
            }
        });
    }
}

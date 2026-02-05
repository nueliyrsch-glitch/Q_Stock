package com.example.quickstock;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Inventory extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory);

        // 1. Back button click listener (navigate to Dashboard activity)
        ImageView btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to Dashboard
                Intent intent = new Intent(Inventory.this, Dashboard.class);
                startActivity(intent);
                finish();
            }
        });

        // 2. Plus button click listener to navigate to Add New Product UI
        View fabAdd = findViewById(R.id.fabAdd);
        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Intent to go to the AddProductInv activity
                Intent intent = new Intent(Inventory.this, AddProductInv.class);
                startActivity(intent);
            }
        });

    }
}

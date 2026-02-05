package com.example.quickstock;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView; // For back button
import android.widget.TextView; // For product button click listener

import androidx.appcompat.app.AppCompatActivity;

public class Inventory extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory); // Link to your Inventory XML layout

        // 1. Back button click listener (navigate to Dashboard activity)
        ImageView btnBack = findViewById(R.id.btnBack); // Ensure this matches your XML ID for the back button
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to Dashboard
                Intent intent = new Intent(Inventory.this, Dashboard.class);
                startActivity(intent);
                finish(); // Close the Inventory activity after navigation
            }
        });

        // 2. Plus button (fabAdd) click listener to navigate to Add New Product UI
        View fabAdd = findViewById(R.id.fabAdd); // Ensure this matches your XML ID for the plus button
        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Intent to go to the AddProductInv activity (Add New Product screen)
                Intent intent = new Intent(Inventory.this, AddProductInv.class);
                startActivity(intent);
            }
        });

        // 3. Set OnClickListener for the product button ("52 >")
        TextView btnProductDetail = findViewById(R.id.btnProductDetail);
        btnProductDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // This will be triggered when the "52 >" button is clicked

                // Start ProductDetailActivity and pass data to it
                Intent intent = new Intent(Inventory.this, ProductDetail.class);
                intent.putExtra("productName", "Datu Puti Vinegar");
                intent.putExtra("productPrice", "₱ 15.00");
                intent.putExtra("productStock", "52");
                startActivity(intent); // Start ProductDetail activity
            }
        });
    }
}

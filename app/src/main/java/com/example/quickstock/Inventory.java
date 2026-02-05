package com.example.quickstock;

import android.content.Intent;
import android.os.Bundle;
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
        btnBack.setOnClickListener(v -> {
            // Create an Intent to navigate to the Dashboard activity
            Intent intent = new Intent(Inventory.this, Dashboard.class);  // Correctly initialized Intent
            startActivity(intent);  // Start the Dashboard activity
            finish();  // Close the Inventory activity after navigation
        });

        // 2. Product click listener (for Datu Puti Vinegar)
        TextView product1TextView = findViewById(R.id.product1); // ID from the inventory layout
        product1TextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an Intent to open ProductDetail activity
                Intent intent = new Intent(Inventory.this, ProductDetail.class);
                // Pass product data to the ProductDetail activity
                intent.putExtra("productName", "Datu Puti Vinegar");
                intent.putExtra("productPrice", "₱ 15.00");
                intent.putExtra("productStock", "52");

                // Start ProductDetail activity
                startActivity(intent);
            }
        });
    }
}

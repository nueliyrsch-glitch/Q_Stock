package com.example.quickstock;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Inventory extends AppCompatActivity {

    // Sample product data (for demo purposes)
    private static final String PRODUCT_NAME = "Datu Puti Vinegar";
    private static final String PRODUCT_PRICE = "₱ 15.00";
    private static final String PRODUCT_STOCK = "52";
    private static final int PRODUCT_ID = 1;  // Example product ID

    private TextView tvProductName, tvProductPrice, tvProductStock;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory);

        // Initialize the TextViews
        tvProductName = findViewById(R.id.product1);
        tvProductPrice = findViewById(R.id.tvProductPrice);
        tvProductStock = findViewById(R.id.tvProductStock);

        // Set initial product values
        tvProductName.setText(PRODUCT_NAME);
        tvProductPrice.setText(PRODUCT_PRICE);
        tvProductStock.setText(PRODUCT_STOCK);

        // 1. Back button click listener
        ImageView btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v -> finish());  // Goes back to the previous screen (Dashboard)

        // 2. Product click listener for product detail navigation
        TextView product1TextView = findViewById(R.id.product1);
        TextView btnDetail = findViewById(R.id.tvProductStock);  // This seems to be a "View Details" button

        // On click, navigate to ProductDetail activity and pass product details
        View.OnClickListener productClickListener = v -> {
            Intent intent = new Intent(Inventory.this, ProductDetail.class);
            // Pass product details to ProductDetail activity
            intent.putExtra("productName", PRODUCT_NAME);
            intent.putExtra("productPrice", PRODUCT_PRICE);
            intent.putExtra("productStock", PRODUCT_STOCK);
            intent.putExtra("productId", PRODUCT_ID); // Passing product ID as well
            startActivityForResult(intent, 1);  // Request code 1
        };

        product1TextView.setOnClickListener(productClickListener);
        btnDetail.setOnClickListener(productClickListener);
    }

    // Handling the result from ProductDetail (updated price)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK) {
            // Get the updated price from ProductDetail
            String updatedPrice = data.getStringExtra("updatedPrice");

            if (updatedPrice != null) {
                // Update the UI with the new price in the Inventory screen
                tvProductPrice.setText(updatedPrice);
                Toast.makeText(this, "Price updated successfully!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Failed to update price.", Toast.LENGTH_SHORT).show();
            }
        }
    }
}

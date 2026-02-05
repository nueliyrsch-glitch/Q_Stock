package com.example.quickstock;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class ProductDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        // Retrieve the product details from the Intent
        Intent intent = getIntent();
        String productName = intent.getStringExtra("productName");
        String productPrice = intent.getStringExtra("productPrice");
        String productStock = intent.getStringExtra("productStock");

        // Get references to the TextViews and EditText where data should be displayed
        TextView productNameTextView = findViewById(R.id.txtProductName);
        TextView productBrandTextView = findViewById(R.id.txtProductBrand);
        TextView productStockTextView = findViewById(R.id.txtProductStock);
        EditText productPriceEditText = findViewById(R.id.edtProductPrice);

        // Set the values from the intent to the TextViews and EditText
        productNameTextView.setText(productName);
        productBrandTextView.setText("Brand: RichTaste Creations"); // Example brand name, adjust as needed
        productStockTextView.setText("In Stock: " + productStock);
        productPriceEditText.setText(productPrice);

        // Back button logic to navigate back to Inventory activity
        ImageView btnBack = findViewById(R.id.btnBack); // Ensure this ID matches your layout
        btnBack.setOnClickListener(v -> {
            // Create an Intent to go back to Inventory activity
            Intent backIntent = new Intent(ProductDetail.this, Inventory.class);
            startActivity(backIntent); // Start Inventory activity
            finish(); // Close the ProductDetail activity
        });
    }
}

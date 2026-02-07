package com.example.quickstock;


import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;


public class ProductDetail extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail); // Link to your ProductDetail XML layout


        // Retrieve product details from the Intent
        Intent intent = getIntent();
        String productName = intent.getStringExtra("productName");
        String productPrice = intent.getStringExtra("productPrice");
        String productStock = intent.getStringExtra("productStock");


        // Find TextViews to display product details
        TextView productNameTextView = findViewById(R.id.txtProductName);
        TextView productPriceTextView = findViewById(R.id.edtProductPrice);
        TextView productStockTextView = findViewById(R.id.txtProductStock);


        // Set the product details to the TextViews
        productNameTextView.setText(productName);
        productPriceTextView.setText(productPrice);
        productStockTextView.setText(productStock);
    }
}


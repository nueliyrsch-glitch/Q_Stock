package com.example.quickstock;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView; // To display the product details

import androidx.appcompat.app.AppCompatActivity;

public class ProductDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail); // Link to your ProductDetail XML layout

        // Retrieve the product details from the Intent
        Intent intent = getIntent();
        String productName = intent.getStringExtra("productName");
        String productPrice = intent.getStringExtra("productPrice");
        String productStock = intent.getStringExtra("productStock");

        // Get references to the TextViews and EditText where data should be displayed
        TextView productNameTextView = findViewById(R.id.txtProductName); // Added ID
        TextView productBrandTextView = findViewById(R.id.txtProductBrand); // Added ID
        TextView productStockTextView = findViewById(R.id.txtProductStock); // Added ID
        EditText productPriceEditText = findViewById(R.id.edtProductPrice); // Added ID

        // Set the retrieved product data to the respective views
        productNameTextView.setText(productName);
        productBrandTextView.setText("Brand: RichTaste Creations"); // Static data for now
        productStockTextView.setText("In Stock: " + productStock);
        productPriceEditText.setText(productPrice);
    }
}

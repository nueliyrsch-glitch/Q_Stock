package com.example.quickstock;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddProductInv extends AppCompatActivity {

    private EditText etProductName, etSupplier, etPrice;
    private Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product_inv); // Make sure this is the correct layout file name

        // Initialize views
        etProductName = findViewById(R.id.etProductName);
        etSupplier = findViewById(R.id.etSupplier);
        etPrice = findViewById(R.id.etPrice);
        btnSave = findViewById(R.id.btnSave);

        // Back Button click listener
        findViewById(R.id.btnBack).setOnClickListener(v -> {
            // Close AddProductInv and return to the previous activity (Inventory)
            finish(); // This will close this activity and return to Inventory
        });

        // Save Product button click listener
        btnSave.setOnClickListener(v -> saveProduct());
    }

    private void saveProduct() {
        // Get data from EditText fields
        String productName = etProductName.getText().toString().trim();
        String supplier = etSupplier.getText().toString().trim();
        String priceString = etPrice.getText().toString().trim();

        // Validate input
        if (productName.isEmpty() || supplier.isEmpty() || priceString.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        // Convert price to a decimal value
        double price = 0;
        try {
            price = Double.parseDouble(priceString);
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Invalid price format", Toast.LENGTH_SHORT).show();
            return;
        }

        // Logic to save product (e.g., database insertion)
        // Example: Add the product to a database or send data to a server

        // Show success message
        Toast.makeText(this, "Product saved successfully", Toast.LENGTH_SHORT).show();

        // Clear the form after saving
        etProductName.setText("");
        etSupplier.setText("");
        etPrice.setText("");
    }
}

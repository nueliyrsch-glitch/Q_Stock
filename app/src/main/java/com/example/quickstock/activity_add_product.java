package com.example.quickstock;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.textfield.TextInputEditText;

public class activity_add_product extends AppCompatActivity {

    private TextInputEditText etName, etDescription, etPrice, etStock, etCategory;
    private Button btnAdd, btnCancel;
    private ProgressBar progressBar;
    private ApiHandler apiHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        apiHandler = new ApiHandler();


        etName = findViewById(R.id.etProductName);
        etDescription = findViewById(R.id.etProductDescription);
        etPrice = findViewById(R.id.etProductPrice);
        etStock = findViewById(R.id.etProductStock);
        etCategory = findViewById(R.id.etProductCategory);
        btnAdd = findViewById(R.id.btnAddProduct);
        btnCancel = findViewById(R.id.btnCancel);
        progressBar = findViewById(R.id.progressBarAdd);

        btnCancel.setOnClickListener(v -> finish());

        btnAdd.setOnClickListener(v -> addProduct());
    }

    private void addProduct() {
        String name = etName.getText().toString().trim();
        String description = etDescription.getText().toString().trim();
        String priceStr = etPrice.getText().toString().trim();
        String stockStr = etStock.getText().toString().trim();
        String category = etCategory.getText().toString().trim();

        if (name.isEmpty() || priceStr.isEmpty() || stockStr.isEmpty()) {
            Toast.makeText(this, "Please fill in all required fields", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            double price = Double.parseDouble(priceStr);
            int stock = Integer.parseInt(stockStr);

            Product product = new Product();
            product.setName(name);
            product.setDescription(description);
            product.setPrice(price);
            product.setStock(stock);
            product.setCategory(category);

            progressBar.setVisibility(View.VISIBLE);
            btnAdd.setEnabled(false);

            apiHandler.addProduct(product, new ApiHandler.ApiCallback() {
                @Override
                public void onSuccess(String response) {
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(activity_add_product.this, "Product added successfully!", Toast.LENGTH_SHORT).show();
                    finish();
                }

                @Override
                public void onError(String error) {
                    progressBar.setVisibility(View.GONE);
                    btnAdd.setEnabled(true);
                    Toast.makeText(activity_add_product.this, "Failed to add product: " + error, Toast.LENGTH_SHORT).show();
                }
            });

        } catch (NumberFormatException e) {
            Toast.makeText(this, "Invalid price or stock", Toast.LENGTH_SHORT).show();
        }
    }
}

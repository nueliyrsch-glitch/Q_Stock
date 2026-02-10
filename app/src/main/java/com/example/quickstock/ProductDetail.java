package com.example.quickstock;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.Map;

public class ProductDetail extends AppCompatActivity {

    private EditText edtProductPrice, edtProductStock;
    private Button btnUpdate, btnDelete;
    private String productId = "1"; // This should come from the previous activity
    private String productPrice, productStock;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        // Initialize Views
        edtProductPrice = findViewById(R.id.edtProductPrice);
        edtProductStock = findViewById(R.id.edtProductStock1);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);

        // Get product details passed from the previous activity
        Intent intent = getIntent();
        productId = intent.getStringExtra("productId");
        productPrice = intent.getStringExtra("productPrice");
        productStock = intent.getStringExtra("productStock");

        // Set the existing price and stock
        edtProductPrice.setText(productPrice);
        edtProductStock.setText(productStock);

        // Update Button Click Listener
        btnUpdate.setOnClickListener(v -> {
            String newPrice = edtProductPrice.getText().toString().trim();
            String newStock = edtProductStock.getText().toString().trim();

            if (newPrice.isEmpty() && newStock.isEmpty()) {
                Toast.makeText(ProductDetail.this, "Please enter a new price or stock", Toast.LENGTH_SHORT).show();
            } else {
                updateProductDetails(productId, newPrice, newStock); // Update both price and stock
            }
        });

        // Delete Button Click Listener
        btnDelete.setOnClickListener(v -> {
            deleteProduct(productId);
        });
    }

    // Update both Product Price and Stock
    private void updateProductDetails(String productId, String newPrice, String newStock) {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://10.0.2.2/Quickstock_API/update_product_details.php"; // Your PHP file URL

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                response -> {
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        String status = jsonObject.getString("status");

                        if ("success".equals(status)) {
                            Toast.makeText(ProductDetail.this, "Product details updated successfully", Toast.LENGTH_SHORT).show();
                            if (!newPrice.isEmpty()) edtProductPrice.setText(newPrice);
                            if (!newStock.isEmpty()) edtProductStock.setText(newStock);
                        } else {
                            String errorMessage = jsonObject.getString("message");
                            Toast.makeText(ProductDetail.this, errorMessage, Toast.LENGTH_SHORT).show();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        Toast.makeText(ProductDetail.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                },
                error -> {
                    error.printStackTrace();
                    Toast.makeText(ProductDetail.this, "Network error. Please try again.", Toast.LENGTH_SHORT).show();
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("productlist_id", productId);  // Correct parameter name for PHP
                params.put("new_price", newPrice); // Send price if it's provided
                params.put("new_stock", newStock); // Send stock if it's provided
                return params;
            }
        };

        queue.add(stringRequest);
    }

    // Delete Product
    private void deleteProduct(String productId) {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://10.0.2.2/Quickstock_API/delete_product.php"; // Your PHP file URL

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                response -> {
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        String status = jsonObject.getString("status");

                        if ("success".equals(status)) {
                            Toast.makeText(ProductDetail.this, "Product deleted successfully", Toast.LENGTH_SHORT).show();
                            finish();  // Close the activity after successful deletion
                        } else {
                            String errorMessage = jsonObject.getString("message");
                            Toast.makeText(ProductDetail.this, errorMessage, Toast.LENGTH_SHORT).show();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        Toast.makeText(ProductDetail.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                },
                error -> {
                    error.printStackTrace();
                    Toast.makeText(ProductDetail.this, "Network error. Please try again.", Toast.LENGTH_SHORT).show();
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("productlist_id", productId);  // Correct parameter name for PHP
                return params;
            }
        };

        queue.add(stringRequest);
    }
}

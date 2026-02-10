package com.example.quickstock;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.textfield.TextInputEditText;

public class dialog_update_product extends AppCompatActivity {

    private TextInputEditText etPrice, etStock;
    private Button btnConfirm, btnCancel;
    private ApiHandler apiHandler;
    private Product product;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_update_product);

        apiHandler = new ApiHandler();
        product = (Product) getIntent().getSerializableExtra("product");

        // Initialize Views
        etPrice = findViewById(R.id.etUpdatePrice);
        etStock = findViewById(R.id.etUpdateStock);
        btnConfirm = findViewById(R.id.btnConfirmUpdate);
        btnCancel = findViewById(R.id.btnCancelUpdate);

        if (product != null) {
            etPrice.setText(String.valueOf(product.getPrice()));
            etStock.setText(String.valueOf(product.getStock()));
        }

        btnCancel.setOnClickListener(v -> finish());

        btnConfirm.setOnClickListener(v -> updateProduct());
    }

    private void updateProduct() {
        String priceStr = etPrice.getText().toString().trim();
        String stockStr = etStock.getText().toString().trim();

        if (priceStr.isEmpty() || stockStr.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            double price = Double.parseDouble(priceStr);
            int stock = Integer.parseInt(stockStr);

            btnConfirm.setEnabled(false);

            apiHandler.updateProduct(product.getId(), price, stock, new ApiHandler.ApiCallback() {
                @Override
                public void onSuccess(String response) {
                    Toast.makeText(dialog_update_product.this, "Product updated!", Toast.LENGTH_SHORT).show();
                    finish(); // Close the dialog/activity
                }

                @Override
                public void onError(String error) {
                    btnConfirm.setEnabled(true);
                    Toast.makeText(dialog_update_product.this, "Update failed: " + error, Toast.LENGTH_SHORT).show();
                }
            });

        } catch (NumberFormatException e) {
            Toast.makeText(this, "Invalid price or stock format", Toast.LENGTH_SHORT).show();
        }
    }
}

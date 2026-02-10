package com.example.quickstock;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class activity_transaction extends AppCompatActivity {

    private Spinner spinnerProducts;
    private EditText etQuantity;
    private TextView tvUnitPrice, tvTotalAmount;
    private Button btnProcessSale, btnBack;
    private RecyclerView recyclerViewTransactions;
    private ProgressBar progressBar;

    private ApiHandler apiHandler;
    private List<Product> productList;
    private List<Transaction> transactionList;
    private TransactionAdapter transactionAdapter;
    private Product selectedProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction);


        spinnerProducts = findViewById(R.id.spinnerProducts);
        etQuantity = findViewById(R.id.etQuantity);
        tvUnitPrice = findViewById(R.id.tvUnitPrice);
        tvTotalAmount = findViewById(R.id.tvTotalAmount);
        btnProcessSale = findViewById(R.id.btnProcessSale);
        btnBack = findViewById(R.id.btnBack);
        recyclerViewTransactions = findViewById(R.id.recyclerViewTransactions);
        progressBar = findViewById(R.id.progressBarTransaction);

        apiHandler = new ApiHandler();
        productList = new ArrayList<>();
        transactionList = new ArrayList<>();


        recyclerViewTransactions.setLayoutManager(new LinearLayoutManager(this));
        transactionAdapter = new TransactionAdapter(this, transactionList);
        recyclerViewTransactions.setAdapter(transactionAdapter);

        loadProducts();
        loadTransactions();


        etQuantity.addTextChangedListener(new android.text.TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                calculateTotal();
            }

            @Override
            public void afterTextChanged(android.text.Editable s) {}
        });

        btnProcessSale.setOnClickListener(v -> processSale());
        btnBack.setOnClickListener(v -> finish());
    }

    private void loadProducts() {
        apiHandler.getProducts(new ApiHandler.ApiCallback() {
            @Override
            public void onSuccess(String response) {
                productList = ApiHandler.parseProducts(response);
                setupProductSpinner();
            }

            @Override
            public void onError(String error) {
                Toast.makeText(activity_transaction.this, "Error loading products: " + error, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setupProductSpinner() {
        List<String> productNames = new ArrayList<>();
        for (Product product : productList) {
            productNames.add(product.getName() + " (Stock: " + product.getStock() + ")");
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, productNames);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerProducts.setAdapter(adapter);

        spinnerProducts.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedProduct = productList.get(position);
                tvUnitPrice.setText(String.format(Locale.US, "₱%.2f", selectedProduct.getPrice()));
                calculateTotal();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
    }

    private void calculateTotal() {
        if (selectedProduct == null) return;

        String qtyStr = etQuantity.getText().toString().trim();
        if (qtyStr.isEmpty()) {
            tvTotalAmount.setText("₱0.00");
            return;
        }

        try {
            int quantity = Integer.parseInt(qtyStr);
            double total = selectedProduct.getPrice() * quantity;
            tvTotalAmount.setText(String.format(Locale.US, "₱%.2f", total));
        } catch (NumberFormatException e) {
            tvTotalAmount.setText("₱0.00");
        }
    }

    private void processSale() {
        if (selectedProduct == null) {
            Toast.makeText(this, "Please select a product", Toast.LENGTH_SHORT).show();
            return;
        }

        String qtyStr = etQuantity.getText().toString().trim();
        if (qtyStr.isEmpty()) {
            etQuantity.setError("Quantity is required");
            return;
        }

        try {
            int quantity = Integer.parseInt(qtyStr);

            if (quantity <= 0) {
                etQuantity.setError("Quantity must be greater than 0");
                return;
            }

            if (quantity > selectedProduct.getStock()) {
                Toast.makeText(this, "Insufficient stock! Available: " + selectedProduct.getStock(), Toast.LENGTH_SHORT).show();
                return;
            }

            double total = selectedProduct.getPrice() * quantity;

            Transaction transaction = new Transaction(
                    selectedProduct.getId(),
                    selectedProduct.getName(),
                    quantity,
                    selectedProduct.getPrice(),
                    total,
                    "sale"
            );

            progressBar.setVisibility(View.VISIBLE);
            btnProcessSale.setEnabled(false);

            apiHandler.addTransaction(transaction, new ApiHandler.ApiCallback() {
                @Override
                public void onSuccess(String response) {
                    progressBar.setVisibility(View.GONE);
                    btnProcessSale.setEnabled(true);
                    Toast.makeText(activity_transaction.this, "Sale completed successfully!", Toast.LENGTH_SHORT).show();

                    // Clear fields
                    etQuantity.setText("");
                    tvTotalAmount.setText("₱0.00");

                    // Reload data
                    loadProducts();
                    loadTransactions();
                }

                @Override
                public void onError(String error) {
                    progressBar.setVisibility(View.GONE);
                    btnProcessSale.setEnabled(true);
                    Toast.makeText(activity_transaction.this, "Transaction failed: " + error, Toast.LENGTH_SHORT).show();
                }
            });

        } catch (NumberFormatException e) {
            Toast.makeText(this, "Invalid quantity", Toast.LENGTH_SHORT).show();
        }
    }

    private void loadTransactions() {
        apiHandler.getTransactions(new ApiHandler.ApiCallback() {
            @Override
            public void onSuccess(String response) {
                transactionList = ApiHandler.parseTransactions(response);
                transactionAdapter.updateTransactions(transactionList);
            }

            @Override
            public void onError(String error) {
                Toast.makeText(activity_transaction.this, "Error loading transactions: " + error, Toast.LENGTH_SHORT).show();
            }
        });
    }
}

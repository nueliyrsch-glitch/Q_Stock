package com.example.quickstock;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ProductAdapter.OnProductUpdateListener {

    private RecyclerView recyclerView;
    private ProductAdapter adapter;
    private ApiHandler apiHandler;
    private ProgressBar progressBar;
    private TextView tvNoProducts;
    private FloatingActionButton fabAddProduct;
    private Button btnTransactions;
    private List<Product> productList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        recyclerView = findViewById(R.id.recyclerViewProducts);
        progressBar = findViewById(R.id.progressBar);
        tvNoProducts = findViewById(R.id.tvNoProducts);
        fabAddProduct = findViewById(R.id.fabAddProduct);
        btnTransactions = findViewById(R.id.btnTransactions);


        apiHandler = new ApiHandler();
        productList = new ArrayList<>();


        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ProductAdapter(this, productList, this);
        recyclerView.setAdapter(adapter);


        loadProducts();


        fabAddProduct.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, activity_add_product.class);
            startActivity(intent);
        });


        btnTransactions.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, activity_transaction.class);
            startActivity(intent);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadProducts();
    }

    private void loadProducts() {
        progressBar.setVisibility(View.VISIBLE);
        tvNoProducts.setVisibility(View.GONE);

        apiHandler.getProducts(new ApiHandler.ApiCallback() {
            @Override
            public void onSuccess(String response) {
                progressBar.setVisibility(View.GONE);

                productList = ApiHandler.parseProducts(response);

                if (productList.isEmpty()) {
                    tvNoProducts.setVisibility(View.VISIBLE);
                    recyclerView.setVisibility(View.GONE);
                } else {
                    tvNoProducts.setVisibility(View.GONE);
                    recyclerView.setVisibility(View.VISIBLE);
                    adapter.updateProducts(productList);
                }
            }

            @Override
            public void onError(String error) {
                progressBar.setVisibility(View.GONE);
                tvNoProducts.setVisibility(View.VISIBLE);
                tvNoProducts.setText("Error loading products: " + error);
                Toast.makeText(MainActivity.this, "Error: " + error, Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onProductUpdated() {

        loadProducts();
    }
}

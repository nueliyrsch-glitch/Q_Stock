package com.example.quickstock;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;


public class Inventory extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory);


        // 1. Back button click listener
        ImageView btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v -> {
            finish(); // Goes back to the previous screen (Dashboard)
        });


        TextView product1TextView = findViewById(R.id.product1);


        TextView btnDetail = findViewById(R.id.btnProductDetail);




        View.OnClickListener productClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Inventory.this, ProductDetail.class);
                intent.putExtra("productName", "Datu Puti Vinegar");
                intent.putExtra("productPrice", "₱ 15.00");
                intent.putExtra("productStock", "52");
                startActivity(intent);
            }
        };




        product1TextView.setOnClickListener(productClickListener);
        btnDetail.setOnClickListener(productClickListener);
    }
}


package com.example.quickstock;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView; // Change this from ImageButton

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Inventory extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_inventory);

        // 1. FIX: Apply window insets to the ROOT layout (RelativeLayout) instead of the back button
        // Open activity_inventory.xml and add android:id="@+id/mainInventory" to the first RelativeLayout
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.btnBack), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // 2. FIX: Match the ImageView type from your XML
        ImageView btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Inventory.this, Dashboard.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
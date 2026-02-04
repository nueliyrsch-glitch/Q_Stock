package com.example.quickstock;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;  // Import Handler for delayed transitions
import androidx.appcompat.app.AppCompatActivity;

public class Screen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen);  // Set your screen layout

        // Delay for 3 seconds (3000 milliseconds)
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Start the LogIn activity after the delay
                Intent intent = new Intent(Screen.this, Account.class);
                startActivity(intent);
                finish();  // Close Screen activity to prevent going back to it
            }
        }, 3000);  // 3000 milliseconds = 3 seconds
    }
}

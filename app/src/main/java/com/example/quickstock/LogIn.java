package com.example.quickstock;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LogIn extends AppCompatActivity {

    String URL = "http://10.0.2.2/Quickstock_API/login_user.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        ImageButton btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v -> finish());

        TextView createAccountText = findViewById(R.id.btnCreateAccount);
        createAccountText.setOnClickListener(v ->
                startActivity(new Intent(LogIn.this, SignIn.class)));

        EditText etStoreName = findViewById(R.id.etStoreName);
        EditText etPassword = findViewById(R.id.etPassword);
        TextView tvPasswordError = findViewById(R.id.tvPasswordError);
        tvPasswordError.setVisibility(TextView.GONE);

        Button btnSignIn = findViewById(R.id.btnSignIn);
        btnSignIn.setOnClickListener(v -> {
            String storeName = etStoreName.getText().toString().trim().toLowerCase();
            String password = etPassword.getText().toString().trim();

            tvPasswordError.setVisibility(TextView.GONE);

            if (storeName.isEmpty() || password.isEmpty()) {
                Toast.makeText(LogIn.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            } else {
                loginUser(storeName, password, tvPasswordError, etPassword);
            }
        });
    }

    private void loginUser(String storeName, String password, TextView tvPasswordError, EditText etPassword) {
        RequestQueue queue = Volley.newRequestQueue(this);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL,
                response -> {
                    try {
                        JSONObject jsonObject = new JSONObject(response);

                        boolean success = jsonObject.optBoolean("success", false);

                        if (success) {
                            // Login successful → go to Dashboard
                            String location = jsonObject.optString("location", "Dasmariñas, Cavite");

                            Intent intent = new Intent(LogIn.this, Dashboard.class);
                            intent.putExtra("STORE_NAME_KEY", storeName);
                            intent.putExtra("LOCATION_KEY", location);
                            startActivity(intent);
                            finish();

                        } else {
                            // Login failed → show PHP error
                            String errorMessage = jsonObject.optString("error", "Incorrect store name or password");
                            tvPasswordError.setText(errorMessage);
                            tvPasswordError.setVisibility(TextView.VISIBLE);
                            etPassword.requestFocus();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        tvPasswordError.setText("An error occurred. Please try again.");
                        tvPasswordError.setVisibility(TextView.VISIBLE);
                        etPassword.requestFocus();
                    }
                },
                error -> {
                    error.printStackTrace();
                    tvPasswordError.setText("Network error. Please check your connection.");
                    tvPasswordError.setVisibility(TextView.VISIBLE);
                    etPassword.requestFocus();
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                // Send POST params to PHP
                params.put("storeName", storeName); // already trimmed & lowercase
                params.put("password", password);
                return params;
            }
        };

        queue.add(stringRequest);
    }
}

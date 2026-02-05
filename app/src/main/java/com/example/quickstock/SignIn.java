package com.example.quickstock;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import java.util.HashMap;
import java.util.Map;

public class SignIn extends AppCompatActivity {

    EditText etStoreName, etPassword, etPhone, etBarangay;
    Button btnRegister;

    // PHP REGISTER ENDPOINT
    String URL = "http://10.0.2.2/Quickstock_API/register_user.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        // BACK BUTTON
        ImageButton btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v -> finish());

        // INPUTS
        etStoreName = findViewById(R.id.etStoreName);
        etPassword  = findViewById(R.id.etPassword);
        etPhone     = findViewById(R.id.etPhone);
        etBarangay  = findViewById(R.id.etBarangay);
        btnRegister = findViewById(R.id.btnSignUp);

        // REGISTER BUTTON
        btnRegister.setOnClickListener(v -> registerUser());
    }

    private void registerUser() {

        String storeName = etStoreName.getText().toString().trim();
        String password  = etPassword.getText().toString().trim();
        String phone     = etPhone.getText().toString().trim();
        String barangay  = etBarangay.getText().toString().trim();

        if (storeName.isEmpty() || password.isEmpty() ||
                phone.isEmpty() || barangay.isEmpty()) {

            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        StringRequest request = new StringRequest(
                Request.Method.POST,
                URL,
                response -> {
                    Toast.makeText(SignIn.this, response, Toast.LENGTH_LONG).show();
                    finish(); // balik sa login after register
                },
                error -> Toast.makeText(SignIn.this,
                        error.toString(),
                        Toast.LENGTH_SHORT).show()
        ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("store_name", storeName);
                params.put("password", password);
                params.put("phone_num", phone);
                params.put("barangay", barangay);
                return params;
            }
        };

        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(request);
    }
}

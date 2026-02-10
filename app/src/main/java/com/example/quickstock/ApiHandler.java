package com.example.quickstock;

import android.os.Handler;
import android.os.Looper;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ApiHandler {

    private static final String BASE_URL = "http://10.0.2.2/try/";


    private final ExecutorService executor = Executors.newSingleThreadExecutor();
    private final Handler mainHandler = new Handler(Looper.getMainLooper());

    public interface ApiCallback {
        void onSuccess(String response);
        void onError(String error);
    }

    public void getProducts(ApiCallback callback) {
        executor.execute(() -> {
            try {

                URL url = new URL(BASE_URL + "products.php");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                conn.setConnectTimeout(10000);
                conn.setReadTimeout(10000);

                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();
                conn.disconnect();

                mainHandler.post(() -> callback.onSuccess(response.toString()));
            } catch (Exception e) {
                mainHandler.post(() -> callback.onError(e.getMessage()));
            }
        });
    }

    public void addProduct(Product product, ApiCallback callback) {
        executor.execute(() -> {
            try {

                URL url = new URL(BASE_URL + "products.php");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type", "application/json; utf-8");
                conn.setDoOutput(true);

                JSONObject jsonParam = new JSONObject();
                jsonParam.put("name", product.getName());
                jsonParam.put("description", product.getDescription());
                jsonParam.put("price", product.getPrice());
                jsonParam.put("stock", product.getStock());
                jsonParam.put("category", product.getCategory());

                try (OutputStream os = conn.getOutputStream()) {
                    byte[] input = jsonParam.toString().getBytes(StandardCharsets.UTF_8);
                    os.write(input, 0, input.length);
                }

                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();
                conn.disconnect();

                mainHandler.post(() -> callback.onSuccess(response.toString()));
            } catch (Exception e) {
                mainHandler.post(() -> callback.onError(e.getMessage()));
            }
        });
    }

    public void updateProduct(int id, double price, int stock, ApiCallback callback) {
        executor.execute(() -> {
            try {

                URL url = new URL(BASE_URL + "products.php");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("PUT");
                conn.setRequestProperty("Content-Type", "application/json; utf-8");
                conn.setDoOutput(true);

                JSONObject jsonParam = new JSONObject();
                jsonParam.put("id", id);
                jsonParam.put("price", price);
                jsonParam.put("stock", stock);

                try (OutputStream os = conn.getOutputStream()) {
                    byte[] input = jsonParam.toString().getBytes(StandardCharsets.UTF_8);
                    os.write(input, 0, input.length);
                }

                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();
                conn.disconnect();

                mainHandler.post(() -> callback.onSuccess(response.toString()));
            } catch (Exception e) {
                mainHandler.post(() -> callback.onError(e.getMessage()));
            }
        });
    }

    public void addTransaction(Transaction transaction, ApiCallback callback) {
        executor.execute(() -> {
            try {

                URL url = new URL(BASE_URL + "transactions.php");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type", "application/json; utf-8");
                conn.setDoOutput(true);

                JSONObject jsonParam = new JSONObject();
                jsonParam.put("product_id", transaction.getProductId());
                jsonParam.put("product_name", transaction.getProductName());
                jsonParam.put("quantity", transaction.getQuantity());
                jsonParam.put("unit_price", transaction.getUnitPrice());
                jsonParam.put("total_amount", transaction.getTotalAmount());
                jsonParam.put("transaction_type", transaction.getType());

                try (OutputStream os = conn.getOutputStream()) {
                    byte[] input = jsonParam.toString().getBytes(StandardCharsets.UTF_8);
                    os.write(input, 0, input.length);
                }

                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();
                conn.disconnect();

                mainHandler.post(() -> callback.onSuccess(response.toString()));
            } catch (Exception e) {
                mainHandler.post(() -> callback.onError(e.getMessage()));
            }
        });
    }

    public void getTransactions(ApiCallback callback) {
        executor.execute(() -> {
            try {
                // ✅ FIXED: Changed from "/transactions" to "transactions.php"
                URL url = new URL(BASE_URL + "transactions.php");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                conn.setConnectTimeout(10000);
                conn.setReadTimeout(10000);

                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();
                conn.disconnect();

                mainHandler.post(() -> callback.onSuccess(response.toString()));
            } catch (Exception e) {
                mainHandler.post(() -> callback.onError(e.getMessage()));
            }
        });
    }

    public static List<Product> parseProducts(String jsonResponse) {
        List<Product> products = new ArrayList<>();
        try {

            JSONObject response = new JSONObject(jsonResponse);
            if (response.getBoolean("success")) {
                JSONArray array = response.getJSONArray("data");
                for (int i = 0; i < array.length(); i++) {
                    JSONObject obj = array.getJSONObject(i);
                    Product p = new Product();
                    p.setId(obj.optInt("id"));
                    p.setName(obj.optString("name"));
                    p.setDescription(obj.optString("description"));
                    p.setPrice(obj.optDouble("price"));
                    p.setStock(obj.optInt("stock"));
                    p.setCategory(obj.optString("category"));
                    products.add(p);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return products;
    }

    public static List<Transaction> parseTransactions(String jsonResponse) {
        List<Transaction> transactions = new ArrayList<>();
        try {

            JSONObject response = new JSONObject(jsonResponse);
            if (response.getBoolean("success")) {
                JSONArray array = response.getJSONArray("data");
                for (int i = 0; i < array.length(); i++) {
                    JSONObject obj = array.getJSONObject(i);
                    Transaction t = new Transaction();
                    t.setId(obj.optInt("id"));
                    t.setProductId(obj.optInt("product_id"));
                    t.setProductName(obj.optString("product_name"));
                    t.setQuantity(obj.optInt("quantity"));
                    t.setUnitPrice(obj.optDouble("unit_price"));
                    t.setTotalAmount(obj.optDouble("total_amount"));
                    t.setType(obj.optString("transaction_type"));

                    transactions.add(t);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return transactions;
    }
}
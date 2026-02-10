package com.example.quickstock;

import java.io.Serializable;

public class Transaction implements Serializable {
    private int id;
    private int productId;
    private String productName;
    private int quantity;
    private double unitPrice;
    private double totalAmount;
    private String type; // e.g., "sale"

    public Transaction() {}

    public Transaction(int productId, String productName, int quantity, double unitPrice, double totalAmount, String type) {
        this.productId = productId;
        this.productName = productName;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.totalAmount = totalAmount;
        this.type = type;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getProductId() { return productId; }
    public void setProductId(int productId) { this.productId = productId; }

    public String getProductName() { return productName; }
    public void setProductName(String productName) { this.productName = productName; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public double getUnitPrice() { return unitPrice; }
    public void setUnitPrice(double unitPrice) { this.unitPrice = unitPrice; }

    public double getTotalAmount() { return totalAmount; }
    public void setTotalAmount(double totalAmount) { this.totalAmount = totalAmount; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
}

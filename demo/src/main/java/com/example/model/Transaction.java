package com.example.model;
public class Transaction {
    private Long id;
    private String name;
    private String email;
    private Double amount;

    // Constructor
    public Transaction(Long id, String name, String email, Double amount) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.amount = amount;
    }

    // Getters
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public Double getAmount() {
        return amount;
    }
}

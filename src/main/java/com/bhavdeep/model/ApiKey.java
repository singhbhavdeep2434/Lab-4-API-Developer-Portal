package com.bhavdeep.model;


import java.sql.Timestamp;

public class ApiKey {
    private int id;
    private int userId;
    private String apiKey;
    private String status;
    private Timestamp createdAt;

    public ApiKey(int id, int userId, String apiKey, String status, Timestamp createdAt) {
        this.id = id;
        this.userId = userId;
        this.apiKey = apiKey;
        this.status = status;
        this.createdAt = createdAt;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public String getApiKey() {
        return apiKey;
    }

    public String getStatus() {
        return status;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }
}

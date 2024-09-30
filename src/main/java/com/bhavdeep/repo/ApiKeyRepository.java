package com.bhavdeep.repo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.bhavdeep.model.ApiKey;

public class ApiKeyRepository {
    // Database connection parameters
    private static final String URL = "jdbc:mysql://localhost:3306/api_portal";
    private static final String USER = "root"; 
    private static final String PASSWORD = "password";

    // Method to get API keys for a user
    public List<ApiKey> getApiKeysForUser(int userId) {
        List<ApiKey> apiKeys = new ArrayList<>();
        String sql = "SELECT * FROM api_keys WHERE user_id = ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String apiKey = rs.getString("api_key");
                String status = rs.getString("status");
                java.sql.Timestamp createdAt = rs.getTimestamp("created_at");
                apiKeys.add(new ApiKey(id, userId, apiKey, status, createdAt));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return apiKeys;
    }

    public boolean generateApiKey(int userId) {
        String sql = "INSERT INTO api_keys (user_id, api_key, status, created_at) VALUES (?, ?, ?, NOW())";
        
        // Generate a random API key (UUID)
        String apiKey = UUID.randomUUID().toString();
        
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
             
            pstmt.setInt(1, userId);
            pstmt.setString(2, apiKey);
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Method to deactivate an API key
    public boolean deactivateApiKey(int apiKeyId) {
        String sql = "UPDATE api_keys SET status = ? WHERE id = ?";
        
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
             
            pstmt.setString(1, "inactive"); // Set status to inactive
            pstmt.setInt(2, apiKeyId);
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}

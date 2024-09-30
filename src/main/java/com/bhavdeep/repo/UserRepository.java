package com.bhavdeep.repo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;

//import org.mindrot.jbcrypt.BCrypt;

public class UserRepository {
    // Database connection parameters
    private static final String URL = "jdbc:mysql://localhost:3306/api_portal";
    private static final String USER = "root"; // replace with your MySQL username
    private static final String PASSWORD = "password"; // replace with your MySQL password

    // Method to register user
    
//    public boolean registerUser(String username, String password, String email) {
//        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
//        String sql = "INSERT INTO users (username, password, email, created_at) VALUES (?, ?, ?, NOW())";
//        
//    }
    
    
    public boolean registerUser(String username, String password, String email) {
//    	String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
        String sql = "INSERT INTO users (username, password, email, created_at) VALUES (?, ?, ?, NOW())";
        
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, username);
            pstmt.setString(2, password); // Ideally, hash the password before storing
            pstmt.setString(3, email);
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Method to authenticate user
    public boolean authenticateUser(String username, String password) {
        String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
        
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();
            return rs.next(); // Returns true if a matching user is found
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.bhavdeep.model.ApiKey" %> 

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>API Keys</title>
    <link rel="stylesheet" href="styles.css"> 
</head>
<body>
<h1>Welcome to the Dashboard</h1>
<a href="api-keys">Manage API Keys</a>
    <div class="container">
        <h1>Your API Keys</h1>
        <ul id="apiKeyList">
            
            
            <% 
                
                @SuppressWarnings("unchecked") 
                List<ApiKey> apiKeys = (List<ApiKey>) request.getAttribute("apiKeys");
                if (apiKeys != null && !apiKeys.isEmpty()) {
                    for (ApiKey key : apiKeys) {
            %>
                <li>
                    <strong>API Key:</strong> <%= key.getApiKey() %><br>
                    <strong>Status:</strong> <%= key.getStatus() %><br>
                    <strong>Created At:</strong> <%= key.getCreatedAt() %><br>
                    <button onclick="deactivateApiKey(<%= key.getId() %>)">Deactivate</button>
                </li>
            <% 
                    }
                } else { 
            %>
                <li>No API keys available.</li>
            <% 
                } 
            %>
        </ul>
        <button onclick="generateNewApiKey()">Generate New API Key</button>
    </div>

    <script>
        function deactivateApiKey(apiKeyId) {
            if (confirm("Are you sure you want to deactivate this API key?")) {
                // Make an AJAX call to your server to deactivate the API key
                fetch(`/apiKey/deactivate/${apiKeyId}`, {
                    method: 'POST'
                })
                .then(response => {
                    if (response.ok) {
                        alert("API Key deactivated successfully.");
                        location.reload(); // Refresh the page to update the list
                    } else {
                        alert("Failed to deactivate API Key.");
                    }
                })
                .catch(error => {
                    console.error('Error:', error);
                });
            }
        }

        function generateNewApiKey() {
            // Make an AJAX call to your server to generate a new API key
            fetch('/apiKey/generate', {
                method: 'POST'
            })
            .then(response => {
                if (response.ok) {
                    alert("New API Key generated successfully.");
                    location.reload(); // Refresh the page to update the list
                } else {
                    alert("Failed to generate new API Key.");
                }
            })
            .catch(error => {
                console.error('Error:', error);
            });
        }
    </script>
</body>
</html>
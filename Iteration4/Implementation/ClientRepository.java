import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClientRepository {

    private Connection connect() throws SQLException {
        String url = "jdbc:postgresql://localhost:5432/lesson_management_db";
        String user = "postgres";
        String password = "12345";
        return DriverManager.getConnection(url, user, password);
    }

    // Create: Add a new client to the database
    public void addClient(Client client) {
        String sql = "INSERT INTO client (name, age, registered, hasGuardian) VALUES (?, ?, ?, ?)";
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, client.getName());
            pstmt.setInt(2, client.getAge());
            pstmt.setBoolean(3, client.isRegistered());
            pstmt.setBoolean(4, client.hasLegalGuardian());
            
            pstmt.executeUpdate();
            System.out.println("Client added successfully.");
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }

public Client getClientById(int clientId) {
    String sql = "SELECT * FROM client WHERE clientid = ?";
    
    try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
        pstmt.setInt(1, clientId);
        
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            Client client = new Client(
                rs.getString("name"),
                rs.getInt("age")
            );
            client.setRegistered(rs.getBoolean("registered"));
            client.setHasGuardian(rs.getBoolean("hasguardian"));
            System.out.println("Client retrieved successfully.");
            return client;
        } else {
            System.out.println("No client found with the given ID.");
            return null;
        }
    } catch (SQLException e) {
        System.out.println("Database Error: " + e.getMessage());
        return null;
    }
}


    // Update: Update client information
    public void updateClient(int clientId, Client updatedClient) {
        String sql = "UPDATE client SET name = ?, age = ?, registered = ?, hasGuardian = ? WHERE clientid = ?";
        
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, updatedClient.getName());
            pstmt.setInt(2, updatedClient.getAge());
            pstmt.setBoolean(3, updatedClient.isRegistered());
            pstmt.setBoolean(4, updatedClient.hasLegalGuardian());
            pstmt.setInt(5, clientId);
    
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Client updated successfully.");
            } else {
                System.out.println("No client found with the given ID.");
            }
        } catch (SQLException e) {
            System.out.println("Database Error: " + e.getMessage());
        }
    }

    // Delete: Delete a client based on client ID
    public void deleteClientById(int clientId) {
        String sql = "DELETE FROM client WHERE clientid = ?";
        
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, clientId);
    
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Client deleted successfully.");
            } else {
                System.out.println("No client found with the given ID.");
            }
        } catch (SQLException e) {
            System.out.println("Database Error: " + e.getMessage());
        }
    }

    public void deleteClient(Client client) {
        String sql = "DELETE FROM client WHERE name = ? AND age = ? AND registered = ? AND hasGuardian = ?";
    
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            // Setting parameters based on Client object properties
            pstmt.setString(1, client.getName());
            pstmt.setInt(2, client.getAge());
            pstmt.setBoolean(3, client.isRegistered());
            pstmt.setBoolean(4, client.hasLegalGuardian());
    
            int rowsAffected = pstmt.executeUpdate();
    
            if (rowsAffected > 0) {
                System.out.println("Client deleted successfully.");
            } else {
                System.out.println("No client found matching the given criteria.");
            }
        } catch (SQLException e) {
            System.out.println("Database Error: " + e.getMessage());
        }
    }
    
}

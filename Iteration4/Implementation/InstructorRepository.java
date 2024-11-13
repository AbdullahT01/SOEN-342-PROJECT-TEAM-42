import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

public class InstructorRepository {

    private Connection connect() throws SQLException {
        String url = "jdbc:postgresql://localhost:5432/lesson_management_db";
        String user = "postgres";
        String password = "12345";
        return DriverManager.getConnection(url, user, password);
    }

    public void addInstructor(Instructor instructor) {
        String sql = "INSERT INTO instructor (name, phone, specialization, availability) VALUES (?, ?, ?, ?)";
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, instructor.getName());
            pstmt.setString(2, instructor.getPhone());
            pstmt.setString(3, instructor.getSpecialization());
            pstmt.setString(4, instructor.getAvailabilityAsString());

            pstmt.executeUpdate();
            System.out.println("Instructor added successfully.");
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }

    public void deleteInstructor(Instructor instructor) {
        String sql = "DELETE FROM instructor WHERE name = ? AND phone = ? AND specialization = ? AND availability = ?";
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, instructor.getName());
            pstmt.setString(2, instructor.getPhone());
            pstmt.setString(3, instructor.getSpecialization());
            pstmt.setString(4, instructor.getAvailabilityAsString()); // Convert the list to a comma-separated string
    
            int rowsAffected = pstmt.executeUpdate();
            
            if (rowsAffected > 0) {
                System.out.println("Instructor deleted successfully.");
            } else {
                System.out.println("No instructor found matching the given criteria.");
            }
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }

    public void getInstructor(Instructor instructor) {
        String sql = "SELECT * FROM instructor WHERE name = ? AND phone = ? AND specialization = ? AND availability = ?";
        
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            // Setting parameters
            pstmt.setString(1, instructor.getName());
            pstmt.setString(2, instructor.getPhone());
            pstmt.setString(3, instructor.getSpecialization());
            pstmt.setString(4, instructor.getAvailabilityAsString());
    
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                System.out.println("Instructor found:");
                System.out.println("ID: " + rs.getInt("InstructorID"));
                System.out.println("Name: " + rs.getString("Name"));
                System.out.println("Phone: " + rs.getString("Phone"));
                System.out.println("Specialization: " + rs.getString("Specialization"));
                System.out.println("Availability: " + rs.getString("Availability"));
            } else {
                System.out.println("No instructor found matching the given criteria.");
            }
            
        } catch (SQLException e) {
            System.out.println("Database Error: " + e.getMessage());
        }
    }


    public void getInstructorById(int id) {
        String sql = "SELECT * FROM instructor WHERE instructorid = ?";
        
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            // Setting parameters
            pstmt.setInt(1, id);
    
            // Execute query and process result
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                System.out.println("Instructor found:");
                System.out.println("ID: " + rs.getInt("InstructorID"));
                System.out.println("Name: " + rs.getString("Name"));
                System.out.println("Phone: " + rs.getString("Phone"));
                System.out.println("Specialization: " + rs.getString("Specialization"));
                System.out.println("Availability: " + rs.getString("Availability"));
            } else {
                System.out.println("No instructor found matching the given criteria.");
            }
            
        } catch (SQLException e) {
            System.out.println("Database Error: " + e.getMessage());
        }
    }
    
    
    
    public void updateInstructor(int id, Instructor updatedInstructor) {
        String sql = "UPDATE instructor SET name = ?, phone = ?, specialization = ?, availability = ? WHERE instructorid = ?";
        
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, updatedInstructor.getName());
            pstmt.setString(2, updatedInstructor.getPhone());
            pstmt.setString(3, updatedInstructor.getSpecialization());
            pstmt.setString(4, updatedInstructor.getAvailabilityAsString());
            pstmt.setInt(5, id);
            
            int rowsAffected = pstmt.executeUpdate();
            
            if (rowsAffected > 0) {
                System.out.println("Instructor updated successfully.");
            } else {
                System.out.println("No instructor found with the given ID.");
            }
        } catch (SQLException e) {
            System.out.println("Database Error: " + e.getMessage());
        }
    }
    

    
}

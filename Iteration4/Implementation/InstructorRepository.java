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

    public int addInstructor(Instructor instructor) {
        String sql = "INSERT INTO instructor (name, phone, specialization, availability) VALUES (?, ?, ?, ?)";
        try (Connection conn = connect(); 
             PreparedStatement pstmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            
            pstmt.setString(1, instructor.getName());
            pstmt.setString(2, instructor.getPhone());
            pstmt.setString(3, instructor.getSpecialization());
            pstmt.setString(4, instructor.getAvailabilityAsString());
    
            int affectedRows = pstmt.executeUpdate();
    
            // Check if the insertion was successful and retrieve the generated key
            if (affectedRows > 0) {
                try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int newId = generatedKeys.getInt(1);
                        System.out.println("Instructor added successfully with ID: " + newId);
                        return newId; // Return the generated ID
                    }
                }
            } else {
                System.out.println("Adding instructor failed, no rows affected.");
            }
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
        return -1; // Return -1 if there was an error or no ID was generated
    }
    


    // Method to retrieve instructor ID based on instructor attributes
    public Integer getInstructorId(Instructor instructor) {
        String sql = "SELECT instructorid FROM instructor WHERE name = ? AND phone = ? AND specialization = ? AND availability = ?";
        
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, instructor.getName());
            pstmt.setString(2, instructor.getPhone());
            pstmt.setString(3, instructor.getSpecialization());
            pstmt.setString(4, instructor.getAvailabilityAsString()); // Convert availability to string format

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                int instructorId = rs.getInt("instructorid");
                System.out.println("Instructor ID retrieved successfully: " + instructorId);
                return instructorId;
            } else {
                System.out.println("No instructor found matching the given attributes.");
            }
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }

        return null; // Return null if the instructor ID is not found
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


    public Instructor getInstructorById(int id) {
        String sql = "SELECT * FROM instructor WHERE instructorid = ?";
        
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            // Setting parameters
            pstmt.setInt(1, id);
    
            // Execute query and process result
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                // Assuming the Instructor class has a constructor that takes these parameters
                Instructor instructor = new Instructor(
                    rs.getString("Name"),
                    rs.getString("Phone"),
                    rs.getString("Specialization"),
                    Arrays.asList(rs.getString("Availability").split(","))
                );
                System.out.println("Instructor retrieved successfully with ID: " + id);
                return instructor;
            } else {
                System.out.println("No instructor found matching the given ID: " + id);
            }
            
        } catch (SQLException e) {
            System.out.println("Database Error: " + e.getMessage());
        }
    
        return null; // Return null if no instructor is found or an error occurs
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

    public void deleteInstructorById(int instructorId) {
        String sql = "DELETE FROM instructor WHERE instructorid = ?";
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, instructorId);
            
            int rowsAffected = pstmt.executeUpdate();
            
            if (rowsAffected > 0) {
                System.out.println("Instructor with ID " + instructorId + " deleted successfully.");
            } else {
                System.out.println("No instructor found with the given ID: " + instructorId);
            }
        } catch (SQLException e) {
            System.out.println("Database Error: " + e.getMessage());
        }
    }
    
    
}

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InstructorRepository {

    private Connection connect() throws SQLException {
        String url = "jdbc:postgresql://localhost:5432/lesson_management_db";
        String user = "postgres";
        String password = "12345";
        return DriverManager.getConnection(url, user, password);
    }

    public void addInstructor(Instructor instructor) {
        String sql = "INSERT INTO instructor (name, phone, specialization) VALUES (?, ?, ?)";
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, instructor.getName());
            pstmt.setString(2, instructor.getPhone());
            pstmt.setString(3, instructor.getSpecialization());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }

    // Other CRUD methods like findById, update, delete can go here
}

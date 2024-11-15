import java.sql.*;

public class OfferingRepository {

    private Connection connect() throws SQLException {
        String url = "jdbc:postgresql://localhost:5432/lesson_management_db";
        String user = "postgres";
        String password = "12345";
        return DriverManager.getConnection(url, user, password);
    }

    private final LocationRepository locationRepo = new LocationRepository();
    private final ScheduleRepository scheduleRepo = new ScheduleRepository();
    private final InstructorRepository instructorRepo = new InstructorRepository();

    // Add a new offering
    public int addOffering(Offering offering) {
        String sql = "INSERT INTO offering (type, mode, available, locationid, scheduleid, instructorid) VALUES (?, ?, ?, ?, ?, ?) RETURNING offeringid";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // Retrieve related IDs from respective repositories
            Integer locationId = locationRepo.getLocationId(offering.getLocation());
            Integer scheduleId = scheduleRepo.getScheduleId(offering.getSchedule());
            Integer instructorId = instructorRepo.getInstructorId(offering.getInstructor());

            if (locationId == null || scheduleId == null || instructorId == null) {
                System.out.println("Failed to retrieve location, schedule, or instructor ID.");
                return -1;
            }

            pstmt.setString(1, offering.getType());
            pstmt.setString(2, offering.getMode());
            pstmt.setBoolean(3, offering.isAvailable());
            pstmt.setInt(4, locationId);
            pstmt.setInt(5, scheduleId);
            pstmt.setInt(6, instructorId);

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                int offeringId = rs.getInt("offeringid");
                System.out.println("Offering added successfully with ID: " + offeringId);
                return offeringId;
            }
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
        return -1;
    }

    // Retrieve an offering by ID
    public Offering getOfferingById(int offeringId) {
        String sql = "SELECT * FROM offering WHERE offeringid = ?";
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, offeringId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                // Retrieve associated entities by querying each table
                Location location = locationRepo.getLocation(rs.getInt("locationid"));
                Schedule schedule = scheduleRepo.getScheduleById(rs.getInt("scheduleid"));
                Instructor instructor = instructorRepo.getInstructorById(rs.getInt("instructorid"));
    
                if (location == null || schedule == null || instructor == null) {
                    System.out.println("Failed to retrieve associated entities for the offering.");
                    return null;
                }
    
                // Create an Offering object with full details of associated entities
                Offering offering = new Offering(
                        rs.getInt("offeringid"),
                        rs.getString("type"),
                        rs.getString("mode"),
                        location,
                        schedule,
                        instructor,
                        rs.getBoolean("available")
                );
                System.out.println("Offering retrieved successfully with ID: " + offeringId);
                return offering;
            }
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
        return null;
    }
    

    // Update an offering by ID
    public void updateOffering(int offeringId, Offering updatedOffering) {
        String sql = "UPDATE offering SET type = ?, mode = ?, available = ?, locationid = ?, scheduleid = ?, instructorid = ? WHERE offeringid = ?";

        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            // Retrieve related IDs from respective repositories
            Integer locationId = locationRepo.getLocationId(updatedOffering.getLocation());
            Integer scheduleId = scheduleRepo.getScheduleId(updatedOffering.getSchedule());
            Integer instructorId = instructorRepo.getInstructorId(updatedOffering.getInstructor());

            if (locationId == null || scheduleId == null || instructorId == null) {
                System.out.println("Failed to retrieve location, schedule, or instructor ID.");
                return;
            }

            pstmt.setString(1, updatedOffering.getType());
            pstmt.setString(2, updatedOffering.getMode());
            pstmt.setBoolean(3, updatedOffering.isAvailable());
            pstmt.setInt(4, locationId);
            pstmt.setInt(5, scheduleId);
            pstmt.setInt(6, instructorId);
            pstmt.setInt(7, offeringId);

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Offering updated successfully.");
            } else {
                System.out.println("No offering found with the given ID.");
            }
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }

    // Delete an offering by ID
    public void deleteOfferingById(int offeringId) {
        String sql = "DELETE FROM offering WHERE offeringid = ?";
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, offeringId);
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Offering deleted successfully.");
            } else {
                System.out.println("No offering found with the given ID.");
            }
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }
}

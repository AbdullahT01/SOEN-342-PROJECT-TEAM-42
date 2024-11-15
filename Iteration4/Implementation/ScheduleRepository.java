import java.sql.*;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class ScheduleRepository {

    private Connection connect() throws SQLException {
        String url = "jdbc:postgresql://localhost:5432/lesson_management_db";
        String user = "postgres";
        String password = "12345";
        return DriverManager.getConnection(url, user, password);
    }

    public int addSchedule(Schedule schedule) {
        String sql = "INSERT INTO schedule (day, startTime, endTime) VALUES (?, ?, ?) RETURNING scheduleid";
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, schedule.getDay());
    
            // Adjust the DateTimeFormatter pattern to match "HH:mm:ss" format
            LocalTime startTime = LocalTime.parse(schedule.getStartTime(), DateTimeFormatter.ofPattern("HH:mm:ss"));
            LocalTime endTime = LocalTime.parse(schedule.getEndTime(), DateTimeFormatter.ofPattern("HH:mm:ss"));
    
            pstmt.setTime(2, Time.valueOf(startTime));
            pstmt.setTime(3, Time.valueOf(endTime));
    
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("scheduleid");
                System.out.println("Schedule added successfully with ID: " + id);
                return id;
            }
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
        return -1; // Return -1 if insertion fails
    }
    
    


    public Integer getScheduleId(Schedule schedule) {
        String sql = "SELECT scheduleid FROM schedule WHERE day = ? AND startTime = ? AND endTime = ?";
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, schedule.getDay());
            pstmt.setTime(2, Time.valueOf(schedule.getStartTime())); // Directly set as Time without parsing
            pstmt.setTime(3, Time.valueOf(schedule.getEndTime())); // Directly set as Time without parsing
    
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                int scheduleId = rs.getInt("scheduleid");
                System.out.println("Schedule ID retrieved successfully: " + scheduleId);
                return scheduleId;
            } else {
                System.out.println("No schedule found matching the given details.");
            }
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
        return null;
    }

    
    public Schedule getScheduleById(int scheduleId) {
        String sql = "SELECT * FROM schedule WHERE scheduleid = ?";
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, scheduleId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Schedule(
                        rs.getString("day"),
                        rs.getTime("startTime").toString(),
                        rs.getTime("endTime").toString()
                );
            }
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
        return null;
    }

    public void updateSchedule(int scheduleId, Schedule updatedSchedule) {
        String sql = "UPDATE schedule SET day = ?, startTime = ?, endTime = ? WHERE scheduleid = ?";
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, updatedSchedule.getDay());

            // Convert start and end times to TIME type
            LocalTime startTime = LocalTime.parse(updatedSchedule.getStartTime(), DateTimeFormatter.ofPattern("HH:mm"));
            LocalTime endTime = LocalTime.parse(updatedSchedule.getEndTime(), DateTimeFormatter.ofPattern("HH:mm"));

            pstmt.setTime(2, Time.valueOf(startTime));
            pstmt.setTime(3, Time.valueOf(endTime));
            pstmt.setInt(4, scheduleId);

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) System.out.println("Schedule updated successfully.");
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }

    public void deleteScheduleById(int scheduleId) {
        String sql = "DELETE FROM schedule WHERE scheduleid = ?";
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, scheduleId);
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) System.out.println("Schedule deleted successfully.");
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }
}

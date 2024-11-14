import java.sql.*;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class ScheduleRepository {

    private Connection connect() throws SQLException {
        String url = "jdbc:postgresql://localhost:5432/lesson_management_db";
        String user = "postgres";
        String password = "root";
        return DriverManager.getConnection(url, user, password);
    }

    public int addSchedule(Schedule schedule) {
        String sql = "INSERT INTO schedule (day, startTime, endTime) VALUES (?, ?, ?) RETURNING scheduleid";
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, schedule.getDay());

            LocalTime startTime = LocalTime.parse(schedule.getStartTime(), DateTimeFormatter.ofPattern("HH:mm"));
            LocalTime endTime = LocalTime.parse(schedule.getEndTime(), DateTimeFormatter.ofPattern("HH:mm"));

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

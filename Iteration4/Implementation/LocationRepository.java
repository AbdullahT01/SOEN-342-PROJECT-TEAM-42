import java.sql.*;

public class LocationRepository {

    private Connection connect() throws SQLException{
        String url = "jdbc:postgresql://localhost:5432/lesson_management_db"; 
        String user = "postgres";
        String password = "12345";
        return DriverManager.getConnection(url, user, password);
    }

    public int addLocation(Location location) {
        String sql = "INSERT INTO location (name, city, facilityType) VALUES (?, ?, ?)";
    
        try (Connection conn = connect(); 
             PreparedStatement pstmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            
            pstmt.setString(1, location.getName());
            pstmt.setString(2, location.getCity());
            pstmt.setString(3, location.getFacilityType());
    
            int affectedRows = pstmt.executeUpdate();
    
            if (affectedRows > 0) {
                try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int newID = generatedKeys.getInt(1);
                        System.out.println("Location was added to the DB successfully with ID: " + newID);
                        return newID;
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    
        return -1; // Return -1 if there was an error or no ID was generated
    }
    

    public void deleteLocationByID(int id){
        String sql = "DELETE FROM location WHERE locationid = ?";

        try(Connection conn = connect(); PreparedStatement ptsmt = conn.prepareStatement(sql)) {
            ptsmt.setInt(1, id);

            int rowsAffected = ptsmt.executeUpdate();
            if(rowsAffected > 0){
                System.out.println("succesfully delete location");
            }else{
                System.out.println("could not delete location");
            }
        } catch (SQLException e) {
            System.out.println("There was an error deleting location in db" + e.getMessage());
        }
    }

    public void updateLocation (int locationID, Location location){
        String sql = "UPDATE location SET name = ?, city = ?, facilityType = ? WHERE locationid = ?";

        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, location.getName());
            pstmt.setString(2, location.getCity());
            pstmt.setString(3, location.getFacilityType());
            pstmt.setInt(4, locationID);

            int rowsAffected = pstmt.executeUpdate();
            if(rowsAffected > 0){
                System.out.println("location was updated");
            }else{
                System.out.println("location was NOT updated");
            }
        } catch (SQLException e) {
            System.out.println("There was an error updating the client in db" + e.getMessage());
        }
    }

    public Location getLocation(int locationID){
        String sql = "SELECT * FROM location WHERE locationid = ?";
        try(Connection conn = connect(); PreparedStatement dbConn = conn.prepareStatement(sql)){
            dbConn.setInt(1, locationID);

            ResultSet rs = dbConn.executeQuery(); 

            if(rs.next()){
                Location loc = new Location(rs.getString("name"), rs.getString("city"), rs.getString("facilityType"));
                System.out.println("Location retrieved successfully."); 
                System.out.println(loc.toString());
                return loc;
            }else{
                System.out.println("No location was found with the given id");
            }
        }catch(SQLException e){
            System.out.println("Could not connect to db " + e.getMessage());
        }
        
        return null;
    }

    public Integer getLocationId(Location location) {
        String sql = "SELECT locationid FROM location WHERE name = ? AND city = ? AND facilityType = ?";

        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, location.getName());
            pstmt.setString(2, location.getCity());
            pstmt.setString(3, location.getFacilityType());

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                int locationId = rs.getInt("locationid");
                System.out.println("Location ID retrieved successfully: " + locationId);
                return locationId;
            } else {
                System.out.println("No location found matching the given attributes.");
            }
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }

        return null; // Return null if the location ID is not found
    }
}

import java.sql.*;

public class BookingRepository {

    private Connection connect() throws SQLException {
        String url = "jdbc:postgresql://localhost:5432/lesson_management_db";
        String user = "postgres";
        String password = "12345";
        return DriverManager.getConnection(url, user, password);
    }

    private final ClientRepository clientRepo = new ClientRepository(); // Assuming you have ClientRepository

    // Add a new booking
    public int addBooking(Booking booking) {
        String sql = "INSERT INTO booking (clientid, offeringid, status) VALUES (?, ?, ?)";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            // Retrieve client ID from the database using ClientRepository
            Integer clientId = clientRepo.getClientId(booking.getClient());
            if (clientId == null) {
                System.out.println("Client not found in the database.");
                return -1;
            }

            // Assuming Offering has a method to retrieve the ID
            int offeringId = booking.getOffering().getId();

            pstmt.setInt(1, clientId);
            pstmt.setInt(2, offeringId);
            pstmt.setString(3, booking.getStatus());

            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int newID = generatedKeys.getInt(1);
                        System.out.println("Booking was added to the DB successfully with ID: " + newID);
                        return newID;
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }

        return -1; // Return -1 if there was an error or no ID was generated
    }

    // Retrieve a booking by ID
    public Booking getBooking(int bookingID) {
        String sql = "SELECT * FROM booking WHERE bookingid = ?";
    
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, bookingID);
    
            ResultSet rs = pstmt.executeQuery();
    
            if (rs.next()) {
                int clientId = rs.getInt("clientid");
                int offeringId = rs.getInt("offeringid");
                String status = rs.getString("status");
    
                // Retrieve Client information from the database
                Client client = getClientById(clientId);
                // Retrieve Offering information from the database
                Offering offering = getOfferingById(offeringId);
    
                if (client != null && offering != null) {
                    Booking booking = new Booking(client, offering);
                    booking.setStatus(status); // Assuming Booking has a setStatus method
                    System.out.println("Booking retrieved successfully: " + booking);
                    return booking;
                } else {
                    System.out.println("Failed to retrieve Client or Offering for the Booking.");
                }
            } else {
                System.out.println("No booking was found with the given ID.");
            }
        } catch (SQLException e) {
            System.out.println("Could not connect to db: " + e.getMessage());
        }
    
        return null;
    }
    
    // Helper method to retrieve Client by ID
    private Client getClientById(int clientId) {
        String sql = "SELECT * FROM client WHERE clientid = ?";

        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, clientId);

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                String name = rs.getString("name");
                int age = rs.getInt("age");
                boolean hasGuardian = rs.getBoolean("hasguardian");
                boolean registered = rs.getBoolean("registered");

                Client client = new Client(name, age);
                client.setHasGuardian(hasGuardian);
                client.setRegistered(registered);
                System.out.println("Client retrieved successfully: " + client);
                return client;
            }
        } catch (SQLException e) {
            System.out.println("Database Error: " + e.getMessage());
        }
        return null;
    }

    // Helper method to retrieve Offering by ID (assuming a similar Offering structure)
    private Offering getOfferingById(int offeringId) {
        // This method would contain logic similar to getClientById, but for the Offering entity
        // Placeholder code below, implement based on Offering structure in your database
        // return new Offering(...);
        return null; // Replace with actual implementation
    }

    // Update booking status
    public void updateBookingStatus(int bookingID, String status) {
        String sql = "UPDATE booking SET status = ? WHERE bookingid = ?";

        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, status);
            pstmt.setInt(2, bookingID);

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Booking status was updated.");
            } else {
                System.out.println("Booking status was NOT updated.");
            }
        } catch (SQLException e) {
            System.out.println("There was an error updating the booking in db: " + e.getMessage());
        }
    }

    // Delete a booking by ID
    public void deleteBookingByID(int bookingID) {
        String sql = "DELETE FROM booking WHERE bookingid = ?";

        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, bookingID);

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Booking was successfully deleted.");
            } else {
                System.out.println("Could not delete booking.");
            }
        } catch (SQLException e) {
            System.out.println("There was an error deleting booking in db: " + e.getMessage());
        }
    }
}

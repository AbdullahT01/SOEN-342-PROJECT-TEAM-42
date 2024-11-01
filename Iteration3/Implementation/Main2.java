import java.util.Arrays;

public class Main2 {
    public static void main(String[] args) {
        // Set up location and schedule for offerings
        Location location = new Location("EV Building", "Montreal", "Gym");
        Schedule schedule1 = new Schedule("Sunday", "12:00", "13:00");
        Schedule schedule2 = new Schedule("Sunday", "13:00", "14:00");

        // Create offerings for group and private lessons
        Offering judoGroupClass = new Offering(1, "Judo", "Group", location, schedule1);
        Offering judoPrivateClass = new Offering(2, "Judo", "Private", location, schedule2);

        // Create an instructor and assign to the group class
        Instructor instructor = new Instructor("Grace", "514-123-4567", "Judo", Arrays.asList("Montreal", "Laval"));
        judoGroupClass.assignInstructor(instructor);

        // Create an administrator and add offerings
        Administrator admin = new Administrator("Admin");
        admin.createOffering(judoGroupClass);
        admin.createOffering(judoPrivateClass);

        // Public can view offerings
        System.out.println("Public view of available offerings:");
        System.out.println("Offering 1: " + judoGroupClass.getLocation().getCity() + " - " + judoGroupClass.isAvailable());
        System.out.println("Offering 2: " + judoPrivateClass.getLocation().getCity() + " - " + judoPrivateClass.isAvailable());

        // Register an adult client
        Client adultClient = new Client("James Russo", 40);
        adultClient.register();

        // Register an underage client
        Client underageClient = new Client("Lisa Russo", 14);
        underageClient.register();

        // Set up a legal guardian for the underage client
        LegalGuardian guardian = new LegalGuardian("James Russo");

        // Adult client books a lesson (direct booking)
        Booking booking1 = null;
        try {
            booking1 = adultClient.makeBooking(judoGroupClass);
            System.out.println("Booking for James Russo: " + booking1.getStatus());
            System.out.println("Offering availability after booking: " + judoGroupClass.isAvailable());
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }

        // Attempt direct booking by underage client (should fail)
        System.out.println("\nAttempting direct booking by underage client:");
        try {
            underageClient.makeBooking(judoPrivateClass); // Should throw an exception
            System.out.println("Booking for Lisa Russo was successful (unexpected).");
        } catch (RuntimeException e) {
            System.out.println("Booking error for underage client: " + e.getMessage()); // Expected outcome
        }

        // Guardian makes a booking for the underage client
        System.out.println("\nGuardian attempting to book for underage client:");
        Booking booking2 = null;
        try {
            booking2 = guardian.manageBookingForUnderageClient(underageClient, judoPrivateClass);
            System.out.println("Booking for Lisa Russo (underage) by guardian: " + booking2.getStatus());
            System.out.println("Offering availability after booking: " + judoPrivateClass.isAvailable());
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }

        // View client bookings directly
        System.out.println("\nAdult client booking details for judoGroupClass: " + booking1);
        System.out.println("Underage client booking details for judoPrivateClass: " + booking2);

        // Cancel a booking and verify availability
        if (booking1 != null) {
            adultClient.cancelBooking(booking1);
            System.out.println("Offering availability after cancellation: " + judoGroupClass.isAvailable());
        } else {
            System.out.println("No booking found for the specified offering.");
        }
    }
}

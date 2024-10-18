import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        // Create location and schedule
        Location location = new Location("EV Building", "Montreal", "Gym");
        Schedule schedule = new Schedule("Sunday", "12:00", "14:00");

        // Create an offering
        Offering judoClass = new Offering(1, "Judo", "Group", location, schedule);

        // Create an instructor
        Instructor instructor = new Instructor("Grace", "514-123-4567", "Judo",
                Arrays.asList("Montreal", "Laval"));

        // Administrator creates the offering
        Administrator admin = new Administrator("Admin");
        admin.createOffering(judoClass);

        // Instructor selects the offering
        try {
            judoClass.assignInstructor(instructor); // Successful assignment
            System.out.println("Offering assigned to instructor.");
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
        }

        // Step 6: Try to assign the same offering again (Second Assignment - Failure)
        try {
            judoClass.assignInstructor(instructor); // This will throw an exception
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage()); // Expected: "Offering is already assigned."
        }
    }
}




import java.util.Arrays;

public class Main3 {
    public static void main(String[] args) {
        // Step 1: Create an Instructor object
        Instructor instructor = new Instructor(
            "Grace",
            "514-123-4567",
            "Judo",
            Arrays.asList("Montreal", "Laval")
        );

        // Step 2: Create an instance of InstructorRepository
        InstructorRepository instructorRepo = new InstructorRepository();

        // Step 3: Add the instructor to the database
        instructorRepo.addInstructor(instructor);

        // If no errors, the instructor should be added to the database
        System.out.println("Instructor added successfully to the database.");
    }
}

import java.util.Arrays;

public class Main3 {
    public static void main(String[] args) {
        // Step 1: Create an Instructor object
        Instructor instructor = new Instructor(
            "Tester",
            "514-123-4567",
            "Karate",
            Arrays.asList("Montreal", "Laval", "Terrebonne")
        );

        // Step 2: Create an instance of InstructorRepository
        InstructorRepository instructorRepo = new InstructorRepository();

        // Step 3: Add the instructor to the database
        //instructorRepo.addInstructor(instructor);

        instructorRepo.getInstructor(instructor);

        instructorRepo.getInstructorById(2);
        instructorRepo.updateInstructor(2, instructor);
        instructorRepo.getInstructorById(2);


        

        // If no errors, the instructor should be added to the database
        System.out.println("Instructor added successfully to the database.");
    }
}

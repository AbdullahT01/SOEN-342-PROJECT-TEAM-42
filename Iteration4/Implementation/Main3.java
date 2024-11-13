import java.util.Arrays;

public class Main3 {
    public static void main(String[] args) {
        // Testing Instructor Repository Operations
        Instructor instructor = new Instructor(
            "Tester",
            "514-123-4567",
            "Karate",
            Arrays.asList("Montreal", "Laval", "Terrebonne")
        );

        InstructorRepository instructorRepo = new InstructorRepository();
        
        // Test adding an instructor
        instructorRepo.addInstructor(instructor);

        // Retrieve the instructor by details
        instructorRepo.getInstructor(instructor);

        // Retrieve instructor by ID
        instructorRepo.getInstructorById(2);

        // Update the instructor with a new instance
        Instructor updatedInstructor = new Instructor(
            "Updated Tester",
            "514-765-4321",
            "Judo",
            Arrays.asList("Montreal", "Brossard")
        );
        instructorRepo.updateInstructor(2, updatedInstructor);
        
        // Retrieve the updated instructor by ID
        instructorRepo.getInstructorById(2);

        System.out.println("Instructor operations complete.\n");
        System.out.println("////////////////////////////////////////////////////////////////////////////////////////////////");

        // Testing Client Repository Operations
        Client client = new Client("John Doe", 17);
        client.setRegistered(true);
        client.setHasGuardian(true);

        ClientRepository clientRepo = new ClientRepository();

        // Add the client to the database
        clientRepo.addClient(client);

        int clientID = 1; 
        Client retrievedClient = clientRepo.getClientById(clientID);
        if (retrievedClient != null) {
            System.out.println("Client Name: " + retrievedClient.getName());
            System.out.println("Client Age: " + retrievedClient.getAge());
            System.out.println("Client Registered: " + retrievedClient.isRegistered());
            System.out.println("Client Has Guardian: " + retrievedClient.hasLegalGuardian());
        }

        // Update client information by client ID (assuming ID 1 for this example)
        Client updatedClient = new Client("Updated Client", 18);
        updatedClient.setRegistered(true);
        updatedClient.setHasGuardian(false);

        clientRepo.updateClient(1, updatedClient);

        // Retrieve the updated client by ID
        clientRepo.getClientById(1);

        // Delete client by ID (assuming ID 1 for this example)
       // clientRepo.deleteClientById(1);

        // Delete client by details
        //clientRepo.deleteClient(client);

        System.out.println("Client operations complete.");
    }
}

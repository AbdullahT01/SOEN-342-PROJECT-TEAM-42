import java.util.Arrays;

public class Main3 {
    public static void main(String[] args) {
        /* 
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
        System.out.println("////////////////////////////////////////////////////////////////////////////////////////////////");


// Testing Schedule Repository Operations
        ScheduleRepository scheduleRepo = new ScheduleRepository();

// Adding a new schedule
        Schedule newSchedule = new Schedule("Monday", "09:00", "11:00");
        int scheduleId = scheduleRepo.addSchedule(newSchedule);
        System.out.println("Schedule added successfully.");
        System.out.println("Added Schedule ID: " + scheduleId);

// Retrieve schedule by ID
        Schedule retrievedSchedule = scheduleRepo.getScheduleById(scheduleId);
        if (retrievedSchedule != null) {
            System.out.println("Schedule retrieved successfully.");
            System.out.println("Day: " + retrievedSchedule.getDay());
            System.out.println("Start Time: " + retrievedSchedule.getStartTime());
            System.out.println("End Time: " + retrievedSchedule.getEndTime());
        }

// Update schedule information by schedule ID
        Schedule updatedSchedule = new Schedule("Wednesday", "10:00", "12:00");
        scheduleRepo.updateSchedule(scheduleId, updatedSchedule);
        System.out.println("Schedule updated successfully.");

// Retrieve the updated schedule by ID
        retrievedSchedule = scheduleRepo.getScheduleById(scheduleId);
        if (retrievedSchedule != null) {
            System.out.println("Schedule retrieved successfully.");
            System.out.println("Day: " + retrievedSchedule.getDay());
            System.out.println("Start Time: " + retrievedSchedule.getStartTime());
            System.out.println("End Time: " + retrievedSchedule.getEndTime());
        }

        System.out.println("Schedule operations complete.");
        System.out.println("////////////////////////////////////////////////////////////////////////////////////////////////");


        Location testLocation = new Location("testName", "Montreal", "gym");
        Location testLocation2 = new Location("testName", "Laval", "gym");

        LocationRepository locRepo = new LocationRepository(); 

       
        int locId = locRepo.addLocation(testLocation);

        

        locRepo.updateLocation(locId, testLocation2);
        locRepo.getLocation(locId); 

        */
        // Create test data for Instructor
   
     
        ClientRepository clientRepo = new ClientRepository();
        LocationRepository locationRepo = new LocationRepository();
        ScheduleRepository scheduleRepo = new ScheduleRepository();
        InstructorRepository instructorRepo = new InstructorRepository();
        OfferingRepository offeringRepo = new OfferingRepository();
        BookingRepository bookingRepo = new BookingRepository();

        // Step 1: Add a Location
        Location location = new Location("Gym", "Montreal", "Indoor");
        int locationId = locationRepo.addLocation(location);
        Location retrievedLocation = locationRepo.getLocation(locationId);

        // Step 2: Add a Schedule
        Schedule schedule = new Schedule("Monday", "10:00:00", "12:00:00");
        int scheduleId = scheduleRepo.addSchedule(schedule);
        Schedule retrievedSchedule = scheduleRepo.getScheduleById(scheduleId);

        // Step 3: Add an Instructor
        Instructor instructor = new Instructor("Alice Smith", "555-7890", "Yoga", Arrays.asList("Monday", "Wednesday"));
        int instructorId = instructorRepo.addInstructor(instructor);
        Instructor retrievedInstructor = instructorRepo.getInstructorById(instructorId);

        // Step 4: Add an Offering
        Offering offering = new Offering("Yoga Class", "In-person", retrievedLocation, retrievedSchedule, retrievedInstructor);
        int offeringId = offeringRepo.addOffering(offering);
        Offering retrievedOffering = offeringRepo.getOfferingById(offeringId);

        // Step 5: Add a Client
        Client client = new Client("John Doe", 30);
        int clientId = clientRepo.addClient(client);
        Client retrievedClient = clientRepo.getClientById(clientId);

        // Step 6: Make a Booking
        Booking booking = new Booking(retrievedClient, retrievedOffering);
        int bookingId = bookingRepo.addBooking(booking);
        
        // Retrieve and print the booking details
        Booking retrievedBooking = bookingRepo.getBooking(bookingId);
        System.out.println("Retrieved Booking: " + retrievedBooking);
    
        
        
    }
}

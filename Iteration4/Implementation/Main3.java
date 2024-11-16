import java.util.Arrays;

public class Main3 {
    public static void main(String[] args) {

        // Initialize Repositories
        ClientRepository clientRepo = new ClientRepository();
        LocationRepository locationRepo = new LocationRepository();
        ScheduleRepository scheduleRepo = new ScheduleRepository();
        InstructorRepository instructorRepo = new InstructorRepository();
        OfferingRepository offeringRepo = new OfferingRepository();
        BookingRepository bookingRepo = new BookingRepository();

        // Step 1: Add and Retrieve a Location
        Location location = new Location("Gym", "Montreal", "Indoor");
        int locationId = locationRepo.addLocation(location);
        Location retrievedLocation = locationRepo.getLocation(locationId);
        System.out.println("Location added and retrieved successfully: " + retrievedLocation);
        System.out.println();
        // Step 2: Add and Retrieve a Schedule
        Schedule schedule = new Schedule("Monday", "10:00:00", "12:00:00");
        int scheduleId = scheduleRepo.addSchedule(schedule);
        Schedule retrievedSchedule = scheduleRepo.getScheduleById(scheduleId);
        System.out.println("Schedule added and retrieved successfully: " + retrievedSchedule);
        System.out.println();

        // Step 3: Add and Retrieve an Instructor
        Instructor instructor = new Instructor("Alice Smith", "555-7890", "Yoga", Arrays.asList("Monday", "Wednesday"));
        int instructorId = instructorRepo.addInstructor(instructor);
        Instructor retrievedInstructor = instructorRepo.getInstructorById(instructorId);
        System.out.println("Instructor added and retrieved successfully: " + retrievedInstructor);
        System.out.println();

        // Step 4: Add and Retrieve an Offering
        Offering offering = new Offering("Yoga Class", "In-person", retrievedLocation, retrievedSchedule, retrievedInstructor);
        int offeringId = offeringRepo.addOffering(offering);
        Offering retrievedOffering = offeringRepo.getOfferingById(offeringId);
        System.out.println("Offering added and retrieved successfully: " + retrievedOffering);
        System.out.println();

        // Step 5: Add and Retrieve a Client
        Client client = new Client("John Doe", 30);
        int clientId = clientRepo.addClient(client);
        Client retrievedClient = clientRepo.getClientById(clientId);
        System.out.println("Client added and retrieved successfully: " + retrievedClient);
        System.out.println();

        // Step 6: Make and Retrieve a Booking
        Booking booking = new Booking(retrievedClient, retrievedOffering);
        int bookingId = bookingRepo.addBooking(booking);
        Booking retrievedBooking = bookingRepo.getBooking(bookingId);
        System.out.println("Booking added and retrieved successfully: " + retrievedBooking);
        System.out.println();

        // Final output of retrieved booking details
        System.out.println("Final Retrieved Booking: " + retrievedBooking);
    }
}

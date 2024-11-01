import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Client {
    private String name;
    private int age;
    private List<Booking> bookings;
    private boolean registered;
    private boolean hasGuardian; 

    public Client(String name, int age) {
        this.name = name;
        this.age = age;
        this.bookings = new ArrayList<>();
        this.registered = false;
        this.hasGuardian = age < 18; 
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }


    public void register() {
        // Implementation for client registration
        if (!registered) {
            registered = true;
            System.out.println("Client " + name + " has been registered.");
        } else {
            System.out.println("Client " + name + " is already registered.");
        }
    }

    public List<Offering> viewOfferings() {
        
        return new ArrayList<>();
    }

    public Booking makeBooking(Offering offering, boolean isGuardianBooking) {
        // Check if the client is underage and prevent booking unless it's done by a guardian
        if (this.age < 18 && !isGuardianBooking) {
            throw new RuntimeException("Only the guardian can make bookings for underage clients.");
        }
        
        // Proceed with the booking if allowed
        if (offering.isAvailable()) {
            Booking booking = new Booking(this, offering);
            bookings.add(booking);
            offering.setUnavailable();
            return booking;
        } else {
            throw new RuntimeException("Booking not allowed: Offering unavailable.");
        }
    }

    public Booking makeBooking(Offering offering) {
        // Check if the client is underage and prevent booking unless it's done by a guardian
        if (this.age < 18 ) {
            throw new RuntimeException("Only the guardian can make bookings for underage clients.");
        }
        
        // Proceed with the booking if allowed
        if (offering.isAvailable()) {
            Booking booking = new Booking(this, offering);
            bookings.add(booking);
            offering.setUnavailable();
            return booking;
        } else {
            throw new RuntimeException("Booking not allowed: Offering unavailable.");
        }
    }

    public void cancelBooking(Booking booking) {
        bookings.remove(booking);
        booking.cancelBooking();
    }

    public Booking viewBookingDetails(Booking booking) {
        return bookings.contains(booking) ? booking : null;
    }

    private boolean hasLegalGuardian() {
        
        return age < 18 && hasGuardian;
    }

   

}

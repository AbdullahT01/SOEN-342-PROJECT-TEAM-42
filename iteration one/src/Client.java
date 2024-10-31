public class Client {
    private String name;
    private int age;

    public Client(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public Booking makeBooking(Offering offering) {
        if (offering.isAvailable()) {
            Booking booking = new Booking(this, offering);
            System.out.println("Booking created for client: " + name);
            offering.markAsBooked(); // Mark offering as booked
            return booking;
        } else {
            System.out.println("Offering is not available.");
            return null;
        }
    }
}

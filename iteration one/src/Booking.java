public class Booking {
    private static int bookingCounter = 1;
    private int bookingID;
    private Client client;
    private Offering offering;
    private String status;

    public Booking(Client client, Offering offering) {
        this.bookingID = bookingCounter++;
        this.client = client;
        this.offering = offering;
        this.status = "Confirmed";
    }

    public int getBookingID() {
        return bookingID;
    }

    public Client getClient() {
        return client;
    }

    public Offering getOffering() {
        return offering;
    }

    public String getStatus() {
        return status;
    }

    public void cancel() {
        this.status = "Cancelled";
        offering.markAsAvailable(); // Mark offering as available
        System.out.println("Booking cancelled for client: " + client.getName());
    }
}

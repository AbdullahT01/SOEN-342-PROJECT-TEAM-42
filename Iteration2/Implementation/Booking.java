import java.util.Objects;

public class Booking {
    private int id;
    private Offering offering;
    private Client client;
    private String status;

    public Booking(Client client, Offering offering) {
        this.client = client;
        this.offering = offering;
        this.status = "Confirmed";
    }

    public void confirmBooking() {
        this.status = "Confirmed";
    }

    public void cancelBooking() {
        this.status = "Canceled";
        this.offering.setUnavailable(); // Make the offering available again
    }

    public String getStatus() {
        return status;
    }


     @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Booking booking = (Booking) obj;
        return Objects.equals(client, booking.client) && Objects.equals(offering, booking.offering);
    }

    @Override
    public String toString() {
        return "Booking{" +
               "client=" + client.getName() +
               ", offering=" + offering.getType() +
               ", location=" + offering.getLocation().getCity() +
               ", status=" + status +
               '}';
    }
    
}
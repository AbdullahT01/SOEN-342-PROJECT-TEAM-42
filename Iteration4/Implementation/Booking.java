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

    // Getters
    public int getId() {
        return id;
    }

    public Offering getOffering() {
        return offering;
    }

    public Client getClient() {
        return client;
    }

    public String getStatus() {
        return status;
    }

    // Methods to manage booking status
    public void confirmBooking() {
        this.status = "Confirmed";
    }

    public void cancelBooking() {
        this.status = "Canceled";
        this.offering.setUnavailable(); // Make the offering available again
    }

    public void setStatus(String status){
        this.status = status; 
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

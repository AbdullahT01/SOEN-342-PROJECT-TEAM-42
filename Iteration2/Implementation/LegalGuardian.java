public class LegalGuardian {
    private String name;

    public LegalGuardian(String name) {
        this.name = name;
    }

    public Booking manageBookingForUnderageClient(Client underageClient, Offering offering) {
        if (underageClient.getAge() < 18) {
            // Bypass the age restriction by setting isGuardianBooking to true
            return underageClient.makeBooking(offering, true);
        } else {
            throw new RuntimeException("Client is not underage.");
        }
    }
}

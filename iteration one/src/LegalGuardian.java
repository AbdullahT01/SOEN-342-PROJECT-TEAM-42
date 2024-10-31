public class LegalGuardian extends Client {
    public LegalGuardian(String name, int age) {
        super(name, age);
    }

    public Booking manageBookingForUnderageClient(Client client, Offering offering) {
        if (client.getAge() < 18) {
            return client.makeBooking(offering);
        } else {
            System.out.println("Client is not underage; legal guardian approval not needed.");
            return null;
        }
    }
}

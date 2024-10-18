public class Administrator {
    private String name;

    public Administrator(String name) {
        this.name = name;
    }

    public boolean createOffering(Offering offering) {
        // Add offering to system (database logic can be added here)
        System.out.println("Offering created and made available: " + offering);
        return true;
    }

    public void deleteAccount(Object user) {
        // Logic to delete an account
        System.out.println("Account deleted: " + user);
    }
}

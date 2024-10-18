import java.util.List;

public class Instructor {
    private String name;
    private String phone;
    private String specialization;
    private List<String> availability;

    public Instructor(String name, String phone, String specialization, List<String> availability) {
        this.name = name;
        this.phone = phone;
        this.specialization = specialization;
        this.availability = availability;
    }

    public String getName() {
        return name;
    }

    public boolean selectOffering(Offering offering) {
        if (offering.isAvailable()) {
            offering.assignInstructor(this);
            return true;
        }
        System.out.println("Offering is not available for selection.");
        return false;
    }
}

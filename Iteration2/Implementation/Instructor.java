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

    public boolean selectOffering(Offering offering) {
        if (availability.contains(offering.getLocation().getCity())) {
            offering.assignInstructor(this);
            return true;
        }
        return false;
    }
}
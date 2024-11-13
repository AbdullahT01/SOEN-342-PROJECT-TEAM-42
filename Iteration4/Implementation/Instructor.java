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

    // Getter and Setter for name
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Getter and Setter for phone
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    // Getter and Setter for specialization
    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    // Getter and Setter for availability
    public List<String> getAvailability() {
        return availability;
    }

    public void setAvailability(List<String> availability) {
        this.availability = availability;
    }

    // Method to select offering
    public boolean selectOffering(Offering offering) {
        if (availability.contains(offering.getLocation().getCity())) {
            offering.assignInstructor(this);
            return true;
        }
        return false;
    }
}

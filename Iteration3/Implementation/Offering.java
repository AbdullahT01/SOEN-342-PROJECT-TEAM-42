public class Offering {
    private int id;
    private String type;
    private String mode;
    private Location location;
    private Schedule schedule;
    private Instructor instructor;
    private boolean available = true;

    public Offering(int id, String type, String mode, Location location, Schedule schedule) {
        this.id = id;
        this.type = type;
        this.mode = mode;
        this.location = location;
        this.schedule = schedule;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setUnavailable() {
        this.available = false;
    }

    public void assignInstructor(Instructor instructor) {
        this.instructor = instructor;
    }
    public Location getLocation() {
        return location;
    }

    public String getType() {
        return type;
    }
}
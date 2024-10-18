public class Offering {
    private int id;
    private String type;
    private String mode;
    private Location location;
    private Schedule schedule;
    private Instructor instructor;

    public Offering(int id, String type, String mode, Location location, Schedule schedule) {
        this.id = id;
        this.type = type;
        this.mode = mode;
        this.location = location;
        this.schedule = schedule;
        this.instructor = null;
    }

    public boolean isAvailable() {
        return instructor == null;
    }

    public void assignInstructor(Instructor instructor) {
        if (isAvailable()) {
            this.instructor = instructor;
            System.out.println("Instructor " + instructor.getName() + " assigned to offering: " + id);
        } else {
            throw new IllegalStateException("Offering is already assigned.");
        }
    }
}

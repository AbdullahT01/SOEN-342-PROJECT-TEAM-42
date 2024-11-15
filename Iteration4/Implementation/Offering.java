public class Offering {
    private int id;
    private String type;
    private String mode;
    private Location location;
    private Schedule schedule;
    private Instructor instructor;
    private boolean available = true;

    public Offering(int id, String type, String mode, Location location, Schedule schedule, Instructor instructor, boolean available) {
        this.id = id;
        this.type = type;
        this.mode = mode;
        this.location = location;
        this.schedule = schedule;
        this.instructor = instructor;
        this.available = available;
    }
    

    // Constructor without ID and available status (for adding new offering)
    public Offering(String type, String mode, Location location, Schedule schedule, Instructor instructor) {
        this.type = type;
        this.mode = mode;
        this.location = location;
        this.schedule = schedule;
        this.instructor = instructor;
    }

    public int getId() {
        return id;
    }

    public String getMode() {
        return mode;
    }



    public Schedule getSchedule() {
        return schedule;
    }

    public Instructor getInstructor() {
        return instructor;
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

    @Override
    public String toString() {
    return "Offering{" +
           "id=" + id +
           ", type='" + type + '\'' +
           ", mode='" + mode + '\'' +
           ", location=" + location +
           ", schedule=" + schedule +
           ", instructor=" + instructor +
           ", available=" + available +
           '}';
}

}
public class Location {
    private String name;
    private String city;
    private String facilityType;

    public Location(String name, String city, String facilityType) {
        this.name = name;
        this.city = city;
        this.facilityType = facilityType;
    }

    public boolean checkAvailability(String time) {
        // Check if location is available at the specified time
        return true;
    }

    public String getCity() {
        return city;
    }
}
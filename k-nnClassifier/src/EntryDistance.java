public class EntryDistance {
    private String dataClass;
    private double distance;

    public EntryDistance(String dataClass, double distance) {
        this.dataClass = dataClass;
        this.distance = distance;
    }

    public double getDistance() {
        return distance;
    }

    public String getDataClass() {
        return new String(dataClass);
    }
}

public class DataEntry {
    private Vector vector;
    private String dataClass;

    public DataEntry(Vector vector, String dataClass) {
        this.vector = vector;
        this.dataClass = dataClass;
    }
    public int getDimension(){
        return vector.getParameters().length;
    }

    public Vector getVector() {
        return vector;
    }

    public Object getDataClass() {
        return dataClass;
    }
}

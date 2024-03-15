public class DataEntry {
    private double[] parameters;
    private String dataClass;

    public DataEntry(double[] parameters, String dataClass) {
        this.parameters = parameters;
        this.dataClass = dataClass;
    }
    public double findDistance(DataEntry other){
        if(parameters.length != other.parameters.length){
            throw new RuntimeException();
        }
        double sum = 0;
        for (int i = 0; i < parameters.length; i++) {
            sum += Math.pow(parameters[i] - other.getParameter(i),2);
        }
        return Math.sqrt(sum);
    }

    private double getParameter(int index) {
        return parameters[index];
    }

    public String getDataClass() {
        return new String(dataClass);
    }
}

public class Vector {
    private double[] parameters;

    public Vector(double[] parameters) {
        this.parameters = parameters;
    }

    public double[] getParameters() {
        return parameters;
    }
    public Vector plus(Vector other){
        /*if(parameters.length != other.parameters.length) throw new DifferentDimensionsException();*/
        double[] resultParameters = new double[parameters.length];
        for (int i = 0; i < parameters.length; i++) {
            resultParameters[i] = parameters[i] + other.parameters[i];
        }
        return new Vector(resultParameters);
    }
    public Vector mult(double value){
        double[] resultParameters = new double[parameters.length];
        for (int i = 0; i < parameters.length; i++) {
            resultParameters[i] = parameters[i] * value;
        }
        return new Vector(resultParameters);
    }

    public double scalarProd(Vector other) {
        double prod = 0;
        for (int i = 0; i < other.parameters.length; i++) {
            prod += parameters[i] * other.parameters[i];
        }
        return prod;
    }
}

public class Perceptron {
    private Vector weight;
    private double bias;
    private final double learningRate;

    public Perceptron(Vector weight, double bias, double learingRate) {
        this.weight = weight;
        this.bias = bias;
        this.learningRate = learingRate;
    }
    private void modify(Vector input, int d, double y){
        weight = weight.plus(input.mult(learningRate * (d - y)));
        bias = bias - learningRate * (d - y);
    }
    public double activate(Vector input){
        var net = calculateNet(input);
        return net;
    }

    private double calculateNet(Vector input) {
        return weight.scalarProd(input) - bias;
    }

    public double[] getWeights() {
        return weight.getParameters();
    }

    public double getBias() {
        return bias;
    }

    public void train(Vector input, int expectedOutput) {
        modify(input, expectedOutput, activate(input));
    }
}

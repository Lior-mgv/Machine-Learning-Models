public class Perceptron {
    private Vector weight;
    private double bias;
    private final double learingRate;

    public Perceptron(Vector weight, double bias, double learingRate) {
        this.weight = weight;
        this.bias = bias;
        this.learingRate = learingRate;
    }


    public void train(DataRepository trainingSet, int numOfEpochs) {
        for (int i = 0; i < numOfEpochs; i++) {
            for(var entry : trainingSet.list()){
                var realOutput = activate(entry.getVector());
                var dataClass = entry.getDataClass();
                var expectedOutput = dataClass.equals("Iris-versicolor") ? 0 : 1;
                modify(entry.getVector(), expectedOutput, realOutput);
            }
        }
    }
    private void modify(Vector input, int expectedOutput, int realOutput){
        weight = weight.plus(input.mult((learingRate * (expectedOutput - realOutput))));
        bias = bias - learingRate * (expectedOutput - realOutput);
    }
    private int activate(Vector input){
        return weight.scalarProd(input) - bias >= 0 ? 1 : 0;
    }
    public String classify(Vector input){
        return activate(input) == 0 ? "Iris-versicolor" : "Iris-virginica";
    }

    public double[] getWeights() {
        return weight.getParameters();
    }

    public double getBias() {
        return bias;
    }
    public double calculateAccuracy(DataRepository testSet){
        double correctlyClassified = 0;
        for(var entry : testSet.list()){
            if(classify(entry.getVector()).equals(entry.getDataClass())){
                correctlyClassified++;
            }
        }
        return correctlyClassified/testSet.list().size();
    }
}

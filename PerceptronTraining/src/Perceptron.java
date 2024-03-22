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
                var output = activate(entry.getVector());

            }
        }
    }
    public int activate(Vector input){

    }
}

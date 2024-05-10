import java.util.Arrays;
import java.util.Random;

public class Main {
    private static String train_set;
    private static String test_set;
    public static void main(String[] args) {
        try {
            train_set = args[0];
            test_set = args[1];
        } catch (Exception e) {
            System.err.println("Error occurred while parsing the arguments.");
            System.exit(1);
        }
        DataRepository testData = new DataRepository(DataReader.readData(test_set));
        DataRepository trainingData = new DataRepository(DataReader.readData(train_set));

        Random rnd = new Random();
        double[] weightParameters = new double[trainingData.list().getFirst().getDimension()];
        for (int i = 0; i < weightParameters.length; i++) {
            weightParameters[i] = rnd.nextDouble();
        }
        double bias = rnd.nextDouble();
        Perceptron perceptron = new Perceptron(new Vector(weightParameters), bias, 0.01);


        System.out.println("Initial weights: " + Arrays.toString(weightParameters));
        System.out.println("Initial bias " + bias);
        perceptron.train(trainingData, 500);
        System.out.println("Weights after training " +  Arrays.toString(perceptron.getWeights()));
        System.out.println("Bias after training " + perceptron.getBias());

        System.out.println("Accuracy of classification: " + perceptron.calculateAccuracy(testData) * 100 + "%.");


    }
}
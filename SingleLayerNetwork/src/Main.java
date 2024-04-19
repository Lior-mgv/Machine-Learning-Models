import java.util.*;

public class Main {
    private static String train_set;
    private static String test_set;

    private static int numOfClasses;

    public static void main(String[] args) {
        try {
            numOfClasses = Integer.parseInt(args[0]);
            train_set = args[1];
            test_set = args[2];
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

        List<String> classesList = new ArrayList<>();
        testData.list().forEach(entry -> {
            if (!classesList.contains(entry.getDataClass())) {
                classesList.add(entry.getDataClass());
            }
        });
        String[] classes = classesList.toArray(new String[0]);

        Perceptron[] perceptrons = new Perceptron[numOfClasses];
        for (int i = 0; i < numOfClasses; i++) {
            perceptrons[i] = new Perceptron(new Vector(weightParameters), bias, 0.5);
        }

        PerceptronLayer perceptronLayer = new PerceptronLayer(perceptrons, classes);
        perceptronLayer.train(trainingData, 50);

        Scanner scanner = new Scanner(System.in);
        int input = -1;
        do {
            System.out.println("1.Input and classify a text\n2.Classify test set and show accuracy\n3.Exit");
            try {
                input = scanner.nextInt();
                scanner.nextLine();
                switch (input) {
                    case 1 -> {
                        System.out.println("Enter the text: ");
                        String line = scanner.nextLine();
                        DataEntry dataEntry = DataParser.parseEntry(line);
                        System.out.println("The text was classified as " + perceptronLayer.classify(dataEntry));
                    }
                    case 2 -> {
                        System.out.println("Accuracy of classification: " + perceptronLayer.getAccuracy(testData) * 100 + "%.");
                    }
                }
            } catch (InputMismatchException e) {
                scanner.nextLine();
                System.out.println("Please enter a valid number\n");
            }
        } while(input != 3);
    }
}
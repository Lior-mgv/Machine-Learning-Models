import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    private static int k;
    private static String train_set;
    private static String test_set;
    public static void main(String[] args) {
        try {
            k = Integer.parseInt(args[0]);
            train_set = args[1];
            test_set = args[2];
        } catch (Exception e) {
            System.err.println("Error occurred while parsing the arguments.");
            System.exit(1);
        }
        DataRepository testData = new DataRepository(DataReader.readData(test_set));
        DataRepository trainingData = new DataRepository(DataReader.readData(train_set));

        Scanner scanner = new Scanner(System.in);
        int input = -1;
        do {
            System.out.println("1.Input and classify a vector\n2.Classify training set and show accuracy\n3.Exit");
            try {
                input = scanner.nextInt();
                scanner.nextLine();
                switch (input){
                    case 1 -> {
                        while (true) {
                            try {
                                System.out.println("Enter parameters separated by comas: ");
                                String line = scanner.nextLine();
                                double[] parameters = Arrays.stream(line.split(","))
                                        .mapToDouble(Double::parseDouble).toArray();
                                DataEntry dataEntry = new DataEntry(parameters, "");
                                System.out.println("The vector was classified as " + Classifier.classify(dataEntry, testData, k));
                                break;
                            } catch (NumberFormatException e) {
                                System.out.println("Error occurred while parsing the parameters");
                            }
                            catch (RuntimeException e) {
                                System.out.println("Input vector has different dimensions from test vectors");
                            }
                        }

                    }
                    case 2 -> {
                        System.out.println("Application of k-NN classifier was "
                                + Classifier.calculateAccuracy(trainingData, testData, k)*100 + "% accurate.\n");
                    }
                    case 3 -> {
                        return;
                    }
                }
            } catch (InputMismatchException e) {
                scanner.nextLine();
                System.out.println("Please enter a valid number\n");
            }
        }while (input != 3);
    }
}
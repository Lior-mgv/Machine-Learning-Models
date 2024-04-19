import java.util.Arrays;
import java.util.List;

public class PerceptronLayer {
    private Perceptron[] perceptrons;
    private String[] classes;

    public PerceptronLayer(Perceptron[] perceptrons, String[] classes) {
        this.perceptrons = perceptrons;
        this.classes = classes;
    }

    public void train(DataRepository trainSet, int numOfEpochs){
        for (int i = 0; i < numOfEpochs; i++) {
            List<DataEntry> dataList = trainSet.list();
            for (DataEntry dataEntry : dataList) {
                for (int j = 0; j < perceptrons.length; j++) {
                    int expectedOutput = Arrays.asList(classes).indexOf(dataEntry.getDataClass()) == j ? 1 : 0;
                    perceptrons[j].train(dataEntry.getVector(), expectedOutput);
                }
            }
        }
    }

    public String classify(DataEntry entry){
        double[] outputs = new double[perceptrons.length];
        for (int i = 0; i < perceptrons.length; i++) {
            outputs[i] = perceptrons[i].activate(entry.getVector());
        }
        double max = Arrays.stream(outputs).max().getAsDouble();
        for (int i = 0; i < outputs.length; i++) {
            if(outputs[i] == max) return classes[i];
        }
        return null;
    }

    public double getAccuracy(DataRepository testSet){
        int correctlyClassified = 0;
        List<DataEntry> dataList = testSet.list();
        for (DataEntry dataEntry : dataList) {
            if(classify(dataEntry).equals(dataEntry.getDataClass())) correctlyClassified++;
        }
        return (double) correctlyClassified / dataList.size();
    }
}

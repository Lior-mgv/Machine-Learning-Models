import java.util.*;

public class Classifier {

    public static String classify(DataEntry dataEntry, DataRepository trainData, int k) {
        List<EntryDistance> distances = new ArrayList<>();
        for(var testEntry : trainData.list()){
            distances.add(new EntryDistance(testEntry.getDataClass(), dataEntry.findDistance(testEntry)));
        }
        distances.sort(Comparator.comparingDouble(EntryDistance::getDistance));
        if(k > distances.size()) k = distances.size();
        distances = distances.subList(0,k);
        HashMap<String, Integer> frequencyMap = new HashMap<>();
        for(var distance : distances){
            var dataClass = distance.getDataClass();
            if(!frequencyMap.containsKey(dataClass)){
                frequencyMap.put(dataClass, 1);
            }
            else{
                frequencyMap.put(dataClass, frequencyMap.get(dataClass)+1);
            }
        }
        return frequencyMap.entrySet().stream().max(Comparator.comparingInt(Map.Entry::getValue)).get().getKey();
    }
    public static double calculateAccuracy(DataRepository trainData, DataRepository testData, int k){
        int correctlyClassified = 0;
        for(var dataEntry : testData.list()){
            if(classify(dataEntry, trainData, k).equals(dataEntry.getDataClass())){
                correctlyClassified++;
            }
        }
        return (double)correctlyClassified/testData.list().size();
    }
}

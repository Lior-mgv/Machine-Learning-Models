public class DataParser {
    public static DataEntry parseEntry(String data){
        var splitted = data.split(",", 2);
        double[] letterOccurrences = new double[26];
        for (var character : splitted[1].toLowerCase().toCharArray()){
            if(character >= 97 && character <= 122){
                letterOccurrences[character - 97]++;
            }
        }
        Vector vector = new Vector(letterOccurrences);
        vector = vector.divide(vector.getMagnitude());
        return new DataEntry(vector, splitted[0]);
    }
}

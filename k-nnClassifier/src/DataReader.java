import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DataReader {
    public static List<DataEntry> readData(String filename){
        List<DataEntry> entries = new ArrayList<>();
        try {
            List<String> lines = Files.readAllLines(Paths.get(filename));
            lines.forEach(l ->{
                var splitted = l.split(",");
                var parameters = Arrays.stream(Arrays.copyOf(splitted, splitted.length-1))
                        .mapToDouble(Double::parseDouble).toArray();
                entries.add(new DataEntry(parameters, splitted[splitted.length-1]));
            });
        } catch (IOException e) {
            System.err.println("Error occurred while reading the file");;
        }
        return entries;
    }
}

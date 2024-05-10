import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class DataReader {
    public static List<DataEntry> readData(String filename){
        List<DataEntry> entries = new ArrayList<>();
        try {
            List<String> lines = Files.readAllLines(Paths.get(filename));
            lines.forEach(l ->{
                entries.add(DataParser.parseEntry(l));
            });
        } catch (IOException e) {
            System.err.println("Error occurred while reading the file");
        }
        return entries;
    }
}

import java.util.ArrayList;
import java.util.List;

public class DataRepository {
    private List<DataEntry> entries;

    public DataRepository(List<DataEntry> entries) {
        this.entries = entries;
    }

    public List<DataEntry> list() {
        return new ArrayList<>(entries);
    }
}

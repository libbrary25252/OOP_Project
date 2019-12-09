package project.oop.g26.csv;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public final class G26CSVUtils {

    public static List<String[]> fastRead(File csv) {
        try (G26CSVReader reader = new G26CSVReader(csv)) {
            return reader.stream().collect(Collectors.toList());
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    public static boolean fastWrite(File csv, List<String[]> list) {
        try (G26CSVWriter writer = new G26CSVWriter(csv)) {
            writer.writes(list);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}

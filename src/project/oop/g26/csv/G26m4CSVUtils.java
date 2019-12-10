package project.oop.g26.csv;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public final class G26m4CSVUtils {

    public static List<String[]> fastRead(File csv) {
        try (G26m4CSVReader reader = new G26m4CSVReader(csv)) {
            return reader.stream().collect(Collectors.toList());
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    public static boolean fastWrite(File csv, List<String[]> list) {
        try (G26m4CSVWriter writer = new G26m4CSVWriter(csv)) {
            writer.writes(list);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}

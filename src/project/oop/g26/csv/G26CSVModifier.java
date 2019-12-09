package project.oop.g26.csv;

import java.io.Closeable;
import java.io.File;
import java.io.Flushable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public final class G26CSVModifier implements Flushable, Closeable {

    private G26CSVWriter csvWriter;
    private List<String[]> caches;
    private File csv;

    public G26CSVModifier(File csv) throws IOException {
        this.csv = csv;
        csvWriter = new G26CSVWriter(csv);
        G26CSVReader csvReader = new G26CSVReader(csv);
        caches = csvReader.readAll();
    }

    public void remove(long... ids) {
        caches.removeIf(s -> Arrays.stream(ids).anyMatch(id -> s[0].equals(id + "")));
    }

    public void append(String[]... lines) {
        Collections.addAll(caches, lines);
    }

    public void writeAll() {
        if (caches == null) throw new IllegalStateException("Caches is null");
        csvWriter.writes(caches);
    }

    public List<String[]> getCachesClone() {
        return new ArrayList<>(caches);
    }

    @Override
    public void close() throws IOException {
        csvWriter.close();
    }

    @Override
    public void flush() throws IOException {
        caches = new G26CSVReader(csv).readAll();
    }
}

package project.oop.g26.csv;

import java.io.Closeable;
import java.io.File;
import java.io.Flushable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

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

    @SafeVarargs
    public final <T> void remove(int pos, T... ids) {
        caches.removeIf(s -> Arrays.stream(ids).anyMatch(id -> s[pos].equals(id + "")));
    }

    public void remove(long... ids) {
        this.remove(0, ids);
    }

    @SafeVarargs
    public final <T> void append(T... line) {
        caches.add(toStringArray(line));
    }

    @SafeVarargs
    public final <T> void append(int pos, T... line) {
        caches.add(pos, toStringArray(line));
    }

    @SafeVarargs
    private <T> String[] toStringArray(T... line) {
        return Arrays.stream(line).map(Objects::toString).toArray(String[]::new);
    }

    @SafeVarargs
    public final <T> void set(int pos, T... line) {
        caches.set(pos, toStringArray(line));
    }

    public void writeAll() throws IOException {
        if (caches == null) throw new IllegalStateException("Caches is null");
        csvWriter.writes(caches);
        csvWriter.flush();
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
        writeAll();
        caches = new G26CSVReader(csv).readAll();
    }
}

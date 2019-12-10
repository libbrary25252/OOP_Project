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

    private List<String[]> caches;
    private String[] header;
    private File csv;

    public G26CSVModifier(File csv) throws IOException {
        this.csv = csv;
        refresh();
    }

    @SafeVarargs
    public final <T> void remove(int pos, T... ids) {
        caches.removeIf(s -> Arrays.stream(ids).anyMatch(id -> s[pos].equals(id + "")));
    }

    public void changeHeader(String... header) {
        this.header = header;
    }

    public void removeLine(int... lines) {
        for (int line : lines) {
            this.caches.remove(line);
        }
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
        try (G26CSVWriter csvWriter = new G26CSVWriter(csv)) {
            csvWriter.write(header);
            csvWriter.writes(caches);
            csvWriter.flush();
        }
    }

    public List<String[]> getCachesClone() {
        return new ArrayList<>(caches);
    }

    public String[] getHeader() {
        return header;
    }

    @Override
    public void close() throws IOException {
    }

    public void refresh() throws IOException {
        try (G26CSVReader csvReader = new G26CSVReader(csv)) {
            caches = csvReader.readAll();
            header = csvReader.readHeader();
        }
    }

    @Override
    public void flush() throws IOException {
        writeAll();
        refresh();
    }
}

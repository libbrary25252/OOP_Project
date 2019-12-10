package project.oop.g26.csv;

import java.io.Closeable;
import java.io.File;
import java.io.Flushable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public final class G26m4CSVModifier implements Flushable, Closeable {

    private List<String[]> caches;
    private String[] header;
    private File csv;

    public G26m4CSVModifier(File csv) throws IOException {
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
        this.caches.removeIf(s -> Arrays.stream(lines).anyMatch(l -> this.caches.indexOf(s) == l));
    }

    public void remove(long... ids) {
        this.remove(0, ids);
    }

    public void modify(int row, int column, Object data) {
        String[] arr = this.caches.remove(row);
        arr[column] = data.toString();
        this.caches.add(row, arr);
    }

    public int getPos(int pos, Object id) {
        List<String[]> list = getCachesClone();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i)[pos].equalsIgnoreCase(id.toString())) return i;
        }
        return -1;
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
    public final <T> void modify(int pos, T... line) {
        caches.set(pos, toStringArray(line));
    }

    public void writeAll() throws IOException {
        if (caches == null) throw new IllegalStateException("Caches is null");
        try (G26m4CSVWriter csvWriter = new G26m4CSVWriter(csv)) {
            csvWriter.write(header);
            csvWriter.writes(caches);
            csvWriter.flush();
        }
    }

    public void writeAll(List<String[]> list) throws IOException {
        try (G26m4CSVWriter csvWriter = new G26m4CSVWriter(csv)) {
            csvWriter.write(header);
            csvWriter.writes(list);
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
        try (G26m4CSVReader csvReader = new G26m4CSVReader(csv)) {
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

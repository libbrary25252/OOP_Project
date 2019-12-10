package project.oop.g26.csv;

import java.io.*;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public final class G26CSVWriter implements Closeable, Flushable {

    private PrintWriter writer;
    private File csv;

    public G26CSVWriter(File csv, boolean append) throws IOException {
        if (!csv.getName().endsWith(".csv")) throw new IllegalStateException(csv.getName() + " is not csv file");
        this.csv = csv;
        writer = new PrintWriter(new FileWriter(csv, append));
    }

    public G26CSVWriter(File csv) throws IOException {
        this(csv, false);
    }

    public void writeHeader(boolean append, String... header) throws IOException {
        flush();
        List<String> str = Files.readAllLines(csv.toPath());
        if (str.size() == 0) {
            writer.println(String.join(",", header));
            flush();
            return;
        }
        String head = String.join(",", header);
        if (append) str.add(0, head);
        else str.set(0, head);
        Files.write(csv.toPath(), str);
    }

    @SafeVarargs
    public final <T> void write(T... params) {
        writer.println(Arrays.stream(params).map(Object::toString).collect(Collectors.joining(",")));
    }

    public void writes(List<String[]> strings) {
        strings.forEach(this::write);
    }

    @Override
    public void close() {
        writer.close();
    }

    @Override
    public void flush() throws IOException {
        writer.flush();
    }

}

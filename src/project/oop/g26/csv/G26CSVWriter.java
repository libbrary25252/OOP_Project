package project.oop.g26.csv;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public final class G26CSVWriter implements Closeable, Flushable {

    private PrintWriter writer;

    public G26CSVWriter(File csv, boolean append) throws IOException {
        if (!csv.getName().endsWith(".csv")) throw new IllegalStateException(csv.getName() + " is not csv file");
        writer = new PrintWriter(new FileWriter(csv, append));
    }

    public G26CSVWriter(File csv) throws IOException {
        this(csv, false);
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

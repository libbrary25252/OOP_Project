package project.oop.g26.csv;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class G26CSVReader implements Closeable, Iterable<String[]>, Flushable {

    private BufferedReader reader;
    private Map<Integer, String[]> cache;
    private File csv;

    public G26CSVReader(File csv) throws IOException {
        if (!csv.getName().endsWith(".csv")) throw new IllegalStateException(csv.getName() + " is not csv file");
        this.csv = csv;
        this.reader = new BufferedReader(new FileReader(csv));
    }

    public Stream<String[]> stream() {
        return reader.lines().map(s -> s.split(","));
    }

    public List<String[]> readAll() throws IOException {
        return new LinkedList<>(readAllWithLines().values());
    }

    public Map<Integer, String[]> readAllWithLines() throws IOException {
        if (cache != null) return cache;
        Map<Integer, String[]> map = new TreeMap<>();
        String line;
        int i = 0;
        while ((line = reader.readLine()) != null) {
            map.put(i++, line.split(","));
        }
        backToTop();
        cache = map;
        return map;
    }

    public String[] read(long id) throws IOException {
        return this.read(id, 0);
    }

    public String[] read(Object id, int pos) throws IOException {
        return readAll().stream().filter(s -> s[pos].equals(id.toString())).findAny().orElse(null);
    }

    public List<String[]> filter(long userId) throws IOException {
        return filter(userId, 1);
    }

    public List<String[]> filter(Object id, int pos) throws IOException {
        return readAll().stream().filter(s -> s[pos].equals(id.toString())).collect(Collectors.toList());
    }

    public int getPos(long id) throws IOException {
        String[] arr = read(id);
        List<String[]> list = readAll();
        for (int i = 0; i < list.size(); i++) {
            if (Arrays.equals(list.get(i), arr)) return i;
        }
        return -1;
    }


    @Override
    public void close() throws IOException {
        reader.close();
    }

    public void backToTop() throws IOException {
        reader = new BufferedReader(new FileReader(csv));
    }

    @Override
    public Iterator<String[]> iterator() {
        try {
            return readAll().iterator();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stream().iterator();
    }

    @Override
    public void flush() throws IOException {
        cache = null;
    }
}

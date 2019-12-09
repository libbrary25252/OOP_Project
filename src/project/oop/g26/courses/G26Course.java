package project.oop.g26.courses;

import project.oop.g26.G26LoginUser;
import project.oop.g26.csv.G26CSVUtils;
import project.oop.g26.csv.G26CSVWriter;
import project.oop.g26.misc.G26Utils;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

public class G26Course {
    private final String name;
    private final String[] information;
    private final String[] columns;
    private Map<Long, String[]> record = new HashMap<>();
    private final File csv;
    private final Function<G26LoginUser, String[]> createRecordFunc;
    private final String fileName;

    private G26Course(String name, String[] information, String[] columns, Function<G26LoginUser, String[]> createRecordFunc, String fileName) throws IOException {
        this.name = name;
        this.information = information;
        this.columns = columns;
        this.fileName = fileName;
        this.createRecordFunc = createRecordFunc;
        this.csv = new File(fileName + ".csv");
        if (csv.createNewFile()) G26Utils.debug("Successfully create csv file for " + name);
        try (G26CSVWriter writer = new G26CSVWriter(csv)) {
            writer.write(columns);
        }
    }

    public void addRecord(G26LoginUser user) {
        String[] record = createRecordFunc.apply(user);
        if (record.length != columns.length) {
            System.out.println("Validate failed. make sure your string array is same as columns name");
            return;
        }
        this.record.putIfAbsent(UUID.randomUUID().getMostSignificantBits(), record);
    }

    public void removeRecord(long uid) {
        this.record.remove(uid);
    }

    public void outPutRecords() {
        G26CSVUtils.fastWrite(csv, record.entrySet().stream().map(s -> (s.getKey().toString() + "," + String.join(",", s.getValue())).split(",")).collect(Collectors.toList()));
    }

    public void loadRecords() {
        this.record.clear();
        for (String[] record : G26CSVUtils.fastRead(csv)) {
            long uid = Long.parseLong(record[0]);
            this.record.putIfAbsent(uid, Arrays.copyOfRange(record, 1, record.length));
        }
    }

    public String getName() {
        return name;
    }

    public String[] getInformation() {
        return information;
    }

    public Map<Long, String[]> getRecord() {
        return record;
    }


    public static class Builder {
        private String name;
        private String[] info;
        private String[] columns;
        private Function<G26LoginUser, String[]> func;
        private String fileName;

        private Builder(String name) {
            this.name = name;
        }

        public static Builder name(String name) {
            return new Builder(name);
        }

        public Builder info(String... info) {
            this.info = info;
            return this;
        }

        public Builder columns(String... columns) {
            this.columns = columns;
            return this;
        }

        public Builder fileName(String fileName) {
            this.fileName = fileName;
            return this;
        }

        public Builder create(Function<G26LoginUser, String[]> func) {
            this.func = func;
            return this;
        }

        public G26Course build() {
            try {
                if (!G26Utils.notNull(name, info, columns, func, fileName)) {
                    throw new IllegalStateException("some infomation are lost.");
                }
                return new G26Course(name, info, columns, func, fileName);
            } catch (IOException e) {
                e.printStackTrace();
                G26Utils.debug("Error: " + e.getMessage());
                return null;
            }
        }
    }


}

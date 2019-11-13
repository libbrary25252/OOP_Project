package project.oop.g26.courses;

import project.oop.g26.Utils;
import project.oop.g26.roles.GUser;

import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;

public class Course {
    private final String name;
    private final String[] information;
    private final String[] columns;
    private Map<Long, String[]> record = new HashMap<>();
    private final File csv;
    private final Function<GUser, String[]> createRecordFunc;

    private Course(String name, String[] information, String[] columns, Function<GUser, String[]> createRecordFunc) throws IOException {
        this.name = name;
        this.information = information;
        this.columns = columns;
        this.createRecordFunc = createRecordFunc;
        this.csv = new File(name + ".csv");
        if (csv.createNewFile()) Utils.debug("Successfully create csv file for " + name);
    }

    public void addRecord(GUser user) {
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
        try (PrintWriter writer = new PrintWriter(new FileOutputStream(csv))) {
            writer.println(String.join(",", columns));
            record.forEach((l, str) -> writer.println(l + "," + String.join(",", str)));
        } catch (IOException e) {
            e.printStackTrace();
            Utils.debug("Error: " + e.getMessage());
        }
    }

    public void loadRecords() {
        this.record.clear();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(csv)))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] record = line.split(",");
                long uid = Long.parseLong(record[0]);
                this.record.putIfAbsent(uid, Arrays.copyOfRange(record, 1, record.length));
            }
        } catch (IOException e) {
            e.printStackTrace();
            Utils.debug("Error: " + e.getMessage());
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
        private Function<GUser, String[]> func;

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

        public Builder create(Function<GUser, String[]> func) {
            this.func = func;
            return this;
        }

        public Course build() {
            try {
                if (!Utils.notNull(name, info, columns, func)) {
                    throw new IllegalStateException("some infomation are lost.");
                }
                return new Course(name, info, columns, func);
            } catch (IOException e) {
                e.printStackTrace();
                Utils.debug("Error: " + e.getMessage());
                return null;
            }
        }
    }


}

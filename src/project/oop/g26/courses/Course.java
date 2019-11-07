package project.oop.g26.courses;

import project.oop.g26.Utils;
import project.oop.g26.roles.GUser;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;

public class Course {
    private final String name;
    private final String[] information;
    private final List<String[]> record = new LinkedList<>();
    private final File csv;
    private final Function<GUser, String[]> createRecordFunc;

    private Course(String name, String[] information, Function<GUser, String[]> createRecordFunc) throws IOException {
        this.name = name;
        this.information = information;
        this.createRecordFunc = createRecordFunc;
        this.csv = new File(name + ".csv");
        if (csv.createNewFile()) Utils.debug("Successfully create csv file for " + name);
    }

    public void addRecord(GUser user) {
        this.record.add(createRecordFunc.apply(user));
    }

    public void outPutRecords() {
        try (PrintWriter writer = new PrintWriter(new FileOutputStream(csv))) {
            record.forEach(str -> writer.println(String.join(",", str)));
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
                record.add(line.split(","));
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

    public List<String[]> getRecord() {
        return record;
    }

    public static class Builder {
        private String name;
        private String[] info;
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

        public Builder create(Function<GUser, String[]> func) {
            this.func = func;
            return this;
        }

        public Course build() {
            try {
                return new Course(name, info, func);
            } catch (IOException e) {
                e.printStackTrace();
                Utils.debug("Error: " + e.getMessage());
                return null;
            }
        }
    }


}

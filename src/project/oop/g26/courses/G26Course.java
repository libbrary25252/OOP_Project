package project.oop.g26.courses;

import project.oop.g26.G26LoginUser;
import project.oop.g26.G26MainStream;
import project.oop.g26.csv.G26m4CSVReader;
import project.oop.g26.csv.G26m4CSVWriter;
import project.oop.g26.misc.G26Utils;
import project.oop.g26.misc.G26m4Permission;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public final class G26Course {
    private final String name;
    private final String[] information;
    private final String[] columns;
    private final File csv;
    private final Function<G26LoginUser, Object[]> createRecordFunc;
    private final JTable table;
    private final Consumer<Component> showAboutUs;

    private G26Course(String name, String[] information, String[] columns, Function<G26LoginUser, Object[]> createRecordFunc, Consumer<Component> showAboutUs, String fileName) throws IOException {
        this.name = name;
        this.information = information;
        this.columns = columns;
        this.createRecordFunc = createRecordFunc;
        this.showAboutUs = showAboutUs;
        File folder = new File("Courses");
        folder.mkdir();
        this.csv = new File(folder, fileName + ".csv");
        if (csv.createNewFile()) {
            G26Utils.debug("Successfully create csv file for " + name);
            try (G26m4CSVWriter writer = new G26m4CSVWriter(csv)) {
                writer.write(columns);
            }
        }
        this.table = new JTable(new String[0][0], columns);
    }

    public JTable getJTable() {
        return this.table;
    }

    public File getCsv() {
        return csv;
    }


    public void showAboutUs(Component parent) {
        showAboutUs.accept(parent);
    }

    public void updateTable() {
        table.setModel(new DefaultTableModel(this.loadRecords().toArray(String[][]::new), columns) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        });
    }

    public void addRecord(G26LoginUser user) {
        Object[] record = createRecordFunc.apply(user);
        if (record == null || record.length != columns.length) {
            JOptionPane.showMessageDialog(table, "Information Not Enough", "Add Appointment Failed", JOptionPane.WARNING_MESSAGE);
            return;
        }
        try (G26m4CSVWriter writer = new G26m4CSVWriter(csv, true)) {
            writer.write(record);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.updateTable();
    }

    public List<String[]> loadRecords() {
        G26MainStream stream = G26MainStream.getStream();
        try (G26m4CSVReader reader = new G26m4CSVReader(csv)) {
            return stream.hasPermission(G26m4Permission.SHOW_ALL_APPOINTMENT) ? reader.readAll() : reader.filter(stream.getLoginUser().getU_ID());
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public String getName() {
        return name;
    }

    public String[] getInformation() {
        return information;
    }


    public static class Builder {
        private String name;
        private String[] info;
        private String[] columns;
        private Function<G26LoginUser, Object[]> func;
        private String fileName;
        private Consumer<Component> showAboutUs;

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

        public Builder showAboutUs(Consumer<Component> showAboutUs) {
            this.showAboutUs = showAboutUs;
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

        public Builder create(Function<G26LoginUser, Object[]> func) {
            this.func = func;
            return this;
        }

        public G26Course build() {
            try {
                if (!G26Utils.notNull(name, info, columns, func, fileName)) {
                    throw new IllegalStateException("some infomation are lost.");
                }
                return new G26Course(name, info, columns, func, showAboutUs, fileName);
            } catch (IOException e) {
                e.printStackTrace();
                G26Utils.debug("Error: " + e.getMessage());
                return null;
            }
        }
    }


}

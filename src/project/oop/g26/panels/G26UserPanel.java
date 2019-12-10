package project.oop.g26.panels;

import project.oop.g26.G26LoginUser;
import project.oop.g26.G26MainStream;
import project.oop.g26.csv.G26m4CSVReader;
import project.oop.g26.misc.G26m4Permission;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.List;

public final class G26UserPanel extends G26IPanel {

    @Override
    protected void initGUI() {
        JPanel panel = new JPanel();
        File UR = G26LoginUser.getUserList();
        G26MainStream Ustream = G26MainStream.getStream();

        try (G26m4CSVReader reader = new G26m4CSVReader(UR)) {

            List<String[]> list = reader.readAll();
            JTable table = new JTable(new DefaultTableModel(list.toArray(String[][]::new), reader.readHeader()) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            });

            panel.add(new JScrollPane(table), CENTER_ALIGNMENT);


            add(panel);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
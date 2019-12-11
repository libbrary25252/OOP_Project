package project.oop.g26.panels;

import project.oop.g26.csv.G26m4CSVModifier;
import project.oop.g26.roles.G26m4ERole;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.File;
import java.io.IOException;

public final class G26RolePanel extends G26IPanel {
    @Override
    protected void initGUI() {
        JPanel panel = new JPanel();
        File rl = G26m4ERole.getRoleList();
        JTable table = new JTable();
        try (G26m4CSVModifier modifier = new G26m4CSVModifier(rl)) {
            table.setModel(new DefaultTableModel(modifier.getCachesClone().toArray(String[][]::new), modifier.getHeader()) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        panel.add(new JScrollPane(table));
        panel.setBounds(0, 0, 800, 500);
        JButton addButton = new JButton("Add Role");
        JButton delButton = new JButton("Delete Roles");
        JButton modifyButton = new JButton("Modify Button");
        add(panel);

    }
}

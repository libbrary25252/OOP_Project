package project.oop.g26.panels;

import javax.swing.*;
import java.awt.*;

public final class G26UserPanel extends G26IPanel {
    @Override
    protected void initGUI() {
        setBackground(Color.pink);
        JLabel label = new JLabel("hello world");
        add(label);
        /*
        JPanel panel = new JPanel();
        File UR = G26LoginUser.getUserList();
        G26MainStream stream = G26MainStream.getStream();
        JTable table = new JTable();
        try (G26m4CSVReader reader = new G26m4CSVReader(UR)) {
            List<String[]> list = reader.readAll();
            table.setModel(new DefaultTableModel(list.toArray(String[][]::new), reader.readHeader()) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

        panel.add(new JScrollPane(table), CENTER_ALIGNMENT);
        add(panel);

        if (stream.hasPermission(G26m4Permission.MODIFY_USER_ACCOUNT)) {
            JButton MacButton = new JButton("Modify User Account");
            add(MacButton);
            MacButton.addActionListener(e -> {
                String[] function = {" Name", "Role", " Year of Birth"};
                JComboBox<String> cB = new JComboBox<>(function);
            });
            JButton AddButton = new JButton("Add User Account");
            add(AddButton);
            AddButton.addActionListener(e -> {

            });
            JButton DelButton = new JButton("Delete User Account");
            add(DelButton);
            deletionLinkTable(DelButton, table, UR);
        }

         */
    }
}
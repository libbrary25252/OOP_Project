package project.oop.g26.panels;

import project.oop.g26.G26LoginUser;
import project.oop.g26.G26MainStream;
import project.oop.g26.csv.G26m4CSVReader;
import project.oop.g26.misc.G26m4Permission;
import project.oop.g26.roles.G26IRole;
import project.oop.g26.roles.G26m4ERole;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public final class G26UserPanel extends G26IPanel {


    @Override
    protected void initGUI() {

        JPanel panel = new JPanel();
        File UR = G26LoginUser.getUserList();
        G26MainStream stream = G26MainStream.getStream();
        JTable table = new JTable();
        List<String[]> list;
        String[] header;
        JButton MacButton = new JButton("Modify User Account");
        JButton AddButton = new JButton("Add User Account");
        JButton DelButton = new JButton("Delete User Account");

        if (stream.hasPermission(G26m4Permission.MODIFY_USER_ACCOUNT)) {
        try (G26m4CSVReader reader = new G26m4CSVReader(UR)) {
            list = reader.readAll();
            header = reader.readHeader();
        } catch (IOException e) {
            e.printStackTrace();
            list = new ArrayList<>();
            header = new String[0];
        }

        final List<Long> idList = list.stream().map(s -> Long.parseLong(s[0])).collect(Collectors.toList());

        table.setModel(new DefaultTableModel(list.toArray(String[][]::new), header) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        });

            MacButton.addActionListener(e -> {
                if (table.getSelectedRowCount() > 1) {
                    JOptionPane.showMessageDialog(this, "Please choose only one row to edit.", "Modify Failed", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                int row = table.getSelectedRow();
                String[] function = {"Name", "Role", "Year of Birth"};
                JComboBox<String> cB = new JComboBox<>(function);
                JOptionPane.showMessageDialog(this, cB, "Select which you want to edit", JOptionPane.INFORMATION_MESSAGE);
                String selected = (String) cB.getSelectedItem();
                if (selected == null) {
                    JOptionPane.showMessageDialog(this, "Unknown selection", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                int column;
                Function<String, Object> func;
                switch (selected.toLowerCase()) {
                    case "name":
                        column = 2;
                        func = string -> string;
                        break;
                    case "role":
                        column = 3;
                        func = string -> {
                            G26IRole role = G26m4ERole.getCustomRole(string.toUpperCase());
                            if (role == null) {
                                JOptionPane.showMessageDialog(this, "Invalid role " + string, "Invalid Role", JOptionPane.ERROR_MESSAGE);
                            }
                            return role == null ? null : role.getName().toUpperCase();
                        };
                        break;
                    case "year of birth":
                        column = 4;
                        func = string -> {
                            if (validateYearOfBirth(string)) return null;
                            return string;
                        };
                        break;
                    default:
                        JOptionPane.showMessageDialog(this, "Unknown selection", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                }
                String input = JOptionPane.showInputDialog(this, "The data you want to change", "Change data of " + selected, JOptionPane.INFORMATION_MESSAGE);
                Object obj = func.apply(input);
                if (obj == null) return;
                String str = G26LoginUser.modifyUser(idList.get(row), column, obj);
                if (str != null) {
                    JOptionPane.showMessageDialog(this, str, "Warning", JOptionPane.WARNING_MESSAGE);
                }
            });

            AddButton.addActionListener(e -> {
                String name = JOptionPane.showInputDialog(this, "Input the name");
                String pw = JOptionPane.showInputDialog(this, "Input the password");
                String role = JOptionPane.showInputDialog(this, "Input the role");
                String yearOfb = JOptionPane.showInputDialog(this, "Input Year Of Birth");
                if (validateYearOfBirth(yearOfb)) return;
                String msg = G26LoginUser.addUser(name, pw, role, yearOfb);
                if (msg != null) {
                    JOptionPane.showMessageDialog(this, msg, "Warning", JOptionPane.WARNING_MESSAGE);
                }
            });

            deletionLinkTable(DelButton, table, UR);

            JScrollPane p = new JScrollPane(table);
            p.setBounds(100, 30, 500, 500);
            AddButton.setBounds(300, 200, 100, 50);
            MacButton.setBounds(300, 300, 100, 50);
            DelButton.setBounds(300, 400, 100, 50);
            panel.add(p);
            addComponents(p, AddButton, MacButton, DelButton);

        }
    }


    private boolean validateYearOfBirth(String yearOfb) {
        String[] date = yearOfb.split("-");
        if (date.length != 3) {
            JOptionPane.showMessageDialog(this, "Date format should be DD-MM-YYYY", "Error", JOptionPane.ERROR_MESSAGE);
            return true;
        } else {
            for (String s : date) {
                try {
                    Integer.parseInt(s);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, ex.getMessage(), "Invalid number", JOptionPane.ERROR_MESSAGE);
                    return true;
                }
            }
        }
        return false;
    }
}


package project.oop.g26.panels;

import project.oop.g26.G26LoginUser;
import project.oop.g26.G26MainStream;
import project.oop.g26.csv.G26m4CSVReader;
import project.oop.g26.misc.G26m4Permission;
import project.oop.g26.roles.G26IRole;
import project.oop.g26.roles.G26m4ERole;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public final class G26UserPanel extends G26IPanel {


    private List<Long> idList;

    @Override
    protected void initGUI() {
        setLocation(0, 0);
        File UR = G26LoginUser.getUserList();
        G26MainStream stream = G26MainStream.getStream();
        JTable table = new JTable();
        JButton MacButton = new JButton("Modify");
        JButton AddButton = new JButton("Add");
        JButton DelButton = new JButton("Delete");

        idList = updateTable(table, UR).stream().map(s -> Long.parseLong(s[0])).collect(Collectors.toList());
        JPanel panel = new JPanel();
        panel.add(new JScrollPane(table));
        add(panel);
        panel.setBounds(20, 20, 500, 800);
        panel.setBorder(null);
        AddButton.setBounds(600, 10, 100, 30);
        MacButton.setBounds(600, 50, 100, 30);
        DelButton.setBounds(600, 90, 100, 30);
        JButton back = new JButton("back");
        addPanelChanger(back, "MainPanel");
        back.setBounds(600, 130, 100, 30);
        add(back);


        if (stream.hasPermission(G26m4Permission.MODIFY_USER_ACCOUNT)) {
            add(AddButton);
            add(DelButton);
            add(MacButton);
        }

        MacButton.addActionListener(e -> {
            if (table.getSelectedRowCount() > 1) {
                JOptionPane.showMessageDialog(this, "Please choose only one row to edit.", "Modify Failed", JOptionPane.WARNING_MESSAGE);
                return;
            }
            int row = table.getSelectedRow();
            System.out.println(row);
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
            if (input == null) input = "";
            Object obj = func.apply(input);
            if (obj == null) return;
            String str = G26LoginUser.modifyUser(idList.get(row), column, obj);
            if (str != null) {
                JOptionPane.showMessageDialog(this, str, "Warning", JOptionPane.WARNING_MESSAGE);
            } else {
                idList = updateTable(table, UR).stream().map(s -> Long.parseLong(s[0])).collect(Collectors.toList());
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
            } else {
                idList = updateTable(table, UR).stream().map(s -> Long.parseLong(s[0])).collect(Collectors.toList());
            }
        });

        deletionLinkTable(DelButton, table, UR);


    }

    private List<String[]> updateTable(JTable table, File UR) {
        try (G26m4CSVReader reader = new G26m4CSVReader(UR)) {
            List<String[]> list = reader.readAll();
            String[] header = reader.readHeader();
            table.setModel(new DefaultTableModel(list.toArray(String[][]::new), header) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            });
            table.repaint();
            table.updateUI();
            return list;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }


    private boolean validateYearOfBirth(String yearOfb) {
        if (yearOfb == null) {
            JOptionPane.showMessageDialog(this, "Birth is empty", "Error", JOptionPane.ERROR_MESSAGE);
            return true;
        }
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


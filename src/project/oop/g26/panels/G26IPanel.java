package project.oop.g26.panels;

import project.oop.g26.csv.G26m4CSVModifier;
import project.oop.g26.manager.G26PanelManger;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public abstract class G26IPanel extends JPanel {

    private final Map<JButton, String> jumpInto = new HashMap<>();
    private boolean initialized = false;

    public G26IPanel() {

    }

    public final void addPanelChanger(JButton button, String panelName) {
        jumpInto.putIfAbsent(button, panelName);
    }

    protected abstract void initGUI();

    public void launchUI(G26PanelManger manger) {
        if (!initialized) {
            this.initGUI();
            setFont(Font.getFont("Microsoft Jhenghei"));
            setVisible(true);
            setLayout(null);
            this.setOnClick(manger);
            this.initialized = true;
        }
    }

    public void resetUI() {
        this.initialized = false;
        removeAll();
    }

    private void setOnClick(G26PanelManger manger) {
        jumpInto.forEach((k, v) -> k.addActionListener(e -> manger.showPanel(v)));
    }

    public final void addComponents(Component... components) {
        for (Component component : components) {
            add(component);
        }
    }

    protected void deletionLinkTable(JButton delButton, JTable table, File linkFile) {
        delButton.addActionListener(e -> {
            int[] rows = table.getSelectedRows();
            if (rows.length == 0) {
                JOptionPane.showMessageDialog(table, "You haven't selected any rows to delete.", "Delete Failed", JOptionPane.WARNING_MESSAGE);
            } else {
                int i = JOptionPane.showConfirmDialog(table, "Are you sure want to delete the selected " + rows.length + " lines ?", "Are you sure ? ", JOptionPane.OK_CANCEL_OPTION);
                if (i == JOptionPane.OK_OPTION) {
                    try (G26m4CSVModifier modifier = new G26m4CSVModifier(linkFile)) {
                        modifier.removeLine(rows);
                        modifier.writeAll();
                        table.setModel(new DefaultTableModel(modifier.getCachesClone().toArray(String[][]::new), modifier.getHeader()) {
                            @Override
                            public boolean isCellEditable(int row, int column) {
                                return false;
                            }
                        });
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
    }
}

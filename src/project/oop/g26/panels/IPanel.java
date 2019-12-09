package project.oop.g26.panels;

import project.oop.g26.manager.PanelManger;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public abstract class IPanel extends JPanel {

    private static Map<JButton, String> jumpInto = new HashMap<>();

    public IPanel() {
        this.initGUI();
        setFont(Font.getFont("Microsoft Jhenghei"));
        setVisible(true);
        setLayout(null);
    }

    public static void addPanelChanger(JButton button, String panelName) {
        jumpInto.putIfAbsent(button, panelName);
    }

    protected abstract void initGUI();

    public void setOnClick(PanelManger manger) {
        jumpInto.forEach((k, v) -> k.addActionListener(e -> manger.setPanel(v)));
    }

    public void addComponents(Component... components) {
        for (Component component : components) {
            add(component);
        }
    }
}

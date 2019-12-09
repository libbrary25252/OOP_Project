package project.oop.g26.manager;

import project.oop.g26.panels.IPanel;

import javax.swing.*;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class PanelManger {

    private final JFrame frame;

    public PanelManger(JFrame frame) {
        this.frame = frame;
    }

    private Map<String, JPanel> panelMap = new ConcurrentHashMap<>();

    public void addPanel(String name, IPanel panel) {
        panel.setOnClick(this);
        this.panelMap.put(name, panel);
    }

    public void setPanel(String name) {
        if (!panelMap.containsKey(name)) {
            System.out.println("Panel " + name + " does not exist!");
            return;
        }
        frame.setContentPane(panelMap.get(name));
    }
}

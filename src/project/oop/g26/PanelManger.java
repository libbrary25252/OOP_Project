package project.oop.g26;

import javax.swing.*;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class PanelManger {
    private Map<String, JPanel> panelMap = new ConcurrentHashMap<>();

    public void addPanel(String name, JPanel panel) {
        this.panelMap.put(name, panel);
    }

    public void setPanel(JFrame frame, String name) {
        if (panelMap.containsKey(name)) {
            System.out.println("Panel " + name + " does not exist!");
            return;
        }
        frame.setContentPane(panelMap.get(name));
    }
}

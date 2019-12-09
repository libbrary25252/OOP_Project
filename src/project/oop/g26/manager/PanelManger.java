package project.oop.g26.manager;

import project.oop.g26.panels.IPanel;

import javax.swing.*;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
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

    public void showPanel(String name) {
        this.showPanel(name, 200);
    }

    public void showPanel(String name, long delay) {
        if (!panelMap.containsKey(name)) {
            System.out.println("Panel " + name + " does not exist!");
            return;
        }
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                frame.setContentPane(panelMap.get(name));
            }
        }, delay);
    }
}

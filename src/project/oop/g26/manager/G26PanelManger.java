package project.oop.g26.manager;

import project.oop.g26.panels.G26IPanel;

import javax.swing.*;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;

public class G26PanelManger {

    private final JFrame frame;
    private G26IPanel current;

    public G26PanelManger(JFrame frame) {
        this.frame = frame;
    }

    private Map<String, G26IPanel> panelMap = new ConcurrentHashMap<>();

    public void addPanel(String name, G26IPanel panel) {
        this.panelMap.put(name, panel);
    }

    public void showPanel(String name) {
        this.showPanel(name, 50);
    }

    public void showPanel(String name, long delay) {
        if (!panelMap.containsKey(name)) {
            System.out.println("Panel " + name + " does not exist!");
            return;
        }
        G26IPanel panel = panelMap.get(name);
        panel.launchUI(this);
        if (current != null) current.resetUI();
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("Jumping into " + name);
                frame.setContentPane(panel);
                frame.setVisible(true);
                current = panel;
            }
        }, delay);
    }

    public void endSession() {
        panelMap.values().forEach(G26IPanel::resetUI);
    }
}

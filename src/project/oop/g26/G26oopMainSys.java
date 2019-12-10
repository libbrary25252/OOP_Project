package project.oop.g26;

import project.oop.g26.courses.G26Course;
import project.oop.g26.manager.G26CourseManager;
import project.oop.g26.manager.G26PanelManger;
import project.oop.g26.misc.G26Utils;
import project.oop.g26.panels.G26CoursePanel;
import project.oop.g26.panels.G26LoginPanel;
import project.oop.g26.panels.G26MainPanel;
import project.oop.g26.panels.G26ProfilePanel;

import javax.swing.*;
import java.awt.*;

public class G26oopMainSys {
    public static void main(String[] args) {
        G26Utils.registerParse(Float.class, Float::parseFloat);
        G26Utils.registerParse(Integer.class, Integer::parseInt);


        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }

        G26LoginUser.generateDefaultFiles();

        JFrame frame = new JFrame("System Panel");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Dimension d = new Dimension(700, 500);
        frame.setSize(d);
        frame.setMaximumSize(d);
        frame.setMinimumSize(d);
        frame.setVisible(true);
        frame.setLayout(null);
        frame.setExtendedState(JFrame.MAXIMIZED_VERT);

        G26PanelManger panelManager = new G26PanelManger(frame);
        G26CourseManager courseManager = new G26CourseManager();

        panelManager.addPanel("MainPanel", new G26MainPanel());
        panelManager.addPanel("Login", new G26LoginPanel());
        panelManager.addPanel("Profile", new G26ProfilePanel());
        panelManager.addPanel("MyCourse", new G26CoursePanel());


        G26Course java = G26Course.Builder.name("Java")
                .columns("AR_ID", "U_ID", "Reversed Time", "Appointment Type", "Recorded", "Remarks")
                .info("java java")
                .fileName("G26M1Lam")
                .create(loginUser -> {
                    return new String[0];
                }).build();


        G26MainStream.setCourseManager(courseManager);
        G26MainStream.setPanelManger(panelManager);

        panelManager.showPanel("Login");
    }
}

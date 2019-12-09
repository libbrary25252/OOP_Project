package project.oop.g26;

import project.oop.g26.courses.Course;
import project.oop.g26.manager.CourseManager;
import project.oop.g26.manager.PanelManger;
import project.oop.g26.misc.Utils;
import project.oop.g26.panels.LoginPanel;
import project.oop.g26.panels.MainPanel;

import javax.swing.*;
import java.awt.*;

public class App {
    public static void main(String[] args) {
        Utils.registerParse(Float.class, Float::parseFloat);
        Utils.registerParse(Integer.class, Integer::parseInt);
        Utils.tryParse("95", Integer.class).map(i -> i + 5).ifPresent(System.out::println);


        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }


        JFrame frame = new JFrame("Panel");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Dimension d = new Dimension(700, 500);
        frame.setSize(d);
        frame.setMaximumSize(d);
        frame.setMinimumSize(d);
        frame.setVisible(true);
        frame.setLayout(null);
        frame.setExtendedState(JFrame.MAXIMIZED_VERT);

        PanelManger panelManager = new PanelManger(frame);
        CourseManager courseManager = new CourseManager();

        panelManager.addPanel("MainPanel", new MainPanel());
        panelManager.addPanel("Login", new LoginPanel());


        Course java = Course.Builder.name("Java")
                .columns("AR_ID", "U_ID", "Reversed Time", "Appointment Type", "Recorded", "Remarks")
                .info("java java")
                .fileName("G26M1Lam")
                .create(loginUser -> {
                    return new String[0];
                }).build();


        MainStream.setCourseManager(courseManager);
        MainStream.setPanelManger(panelManager);

        panelManager.showPanel("Login");
    }
}

package project.oop.g26;

import project.oop.g26.courses.G26Course;
import project.oop.g26.manager.G26CourseManager;
import project.oop.g26.manager.G26PanelManger;
import project.oop.g26.misc.G26Utils;
import project.oop.g26.panels.*;
import project.oop.g26.panels.courses.G26CoursePane;
import project.oop.g26.panels.courses.G26m4Java;
import project.oop.g26.roles.G26m4ERole;

import javax.swing.*;
import java.awt.*;
import java.util.function.Function;

public class G26oopMainSys {
    public static void main(String[] args) {

        G26Utils.registerParse(Float.class, Float::parseFloat);
        G26Utils.registerParse(Integer.class, Integer::parseInt);


        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            G26LoginUser.generateDefaultFiles();
            G26m4ERole.generateDefaultFiles();
        } catch (Exception e) {
            e.printStackTrace();
        }


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
        panelManager.addPanel("UserAccount",new G26UserPanel());


        final String[] header = {"AR_ID", "U_ID", "Reversed Time", "Recorded", "Remarks"};
        final Function<G26LoginUser, Object[]> createFunction = g26LoginUser -> {
            String[] timeSlots = {"11:30-13:00", "13:00-14:30", "14:30-16:00", "16:00-17:30"};
            JComboBox<String> comboBox = new JComboBox<>(timeSlots);
            String slot = JOptionPane.showInternalInputDialog(null, comboBox);
            if (slot == null) return null;
            String remark = JOptionPane.showInputDialog("Any Remarks? ", "-");
            if (remark == null) return null;
            return new Object[]{G26Utils.getRandomId(), g26LoginUser.getU_ID(), slot, System.currentTimeMillis(), remark};
        };

        G26Course java = G26Course.Builder.name("Java")
                .columns(header)
                .info("java java")
                .fileName("G26M1Lam")
                .showAboutMe(component -> {
                    JOptionPane.showMessageDialog(component, "this is about us");
                })
                .create(createFunction).build();

        G26Course C = G26Course.Builder.name("C")
                .columns(header)
                .info("a computer programming book written by Brian Kernighan and Dennis Ritchie, the latter of whom originally designed and implemented the language, as well as co-designed the Unix operating system with which development of the language was closely intertwined. The book was central to the development and popularization of the C programming language and is still widely read and used today. Because the book was co-authored by the original language designer, and because the first edition of the book served for many years as the de facto standard for the language, the book was regarded by many to be the authoritative reference on C.")
                .fileName("G26M4Liu")
                .showAboutMe(component -> {
                    JOptionPane.showMessageDialog(component, "this is about us");
                })
                .create(createFunction).build();

        G26CoursePane.addPart("Java", G26m4Java.class);

        courseManager.addCourse("Java", java);
        courseManager.addCourse("C", C);

        G26MainStream.setCourseManager(courseManager);
        G26MainStream.setPanelManger(panelManager);

        panelManager.showPanel("Login");
    }
}

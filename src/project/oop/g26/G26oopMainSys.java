package project.oop.g26;

import project.oop.g26.courses.G26Course;
import project.oop.g26.manager.G26CourseManager;
import project.oop.g26.manager.G26PanelManger;
import project.oop.g26.misc.G26Utils;
import project.oop.g26.misc.G26m4HtmlTextBuilder;
import project.oop.g26.misc.G26m4ImageIconPool;
import project.oop.g26.panels.*;
import project.oop.g26.panels.courses.*;
import project.oop.g26.roles.G26m4ERole;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.function.Function;

public class G26oopMainSys {
    public static void main(String[] args) {

        G26Utils.registerParse(Float.class, Float::parseFloat);
        G26Utils.registerParse(Integer.class, Integer::parseInt);


        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            G26LoginUser.generateDefaultFiles();
            G26m4ERole.generateDefaultFiles();
            G26m4ImageIconPool.addImage("group", new URL("https://media.discordapp.net/attachments/653979952236199938/654016641704787988/Member2.jpg"));
            G26m4ImageIconPool.addImage("liu", new URL("https://media.discordapp.net/attachments/641642508555649024/653930412300566580/image0.jpg?width=708&height=470"));
            G26m4ImageIconPool.addImage("libby", new URL("https://media.discordapp.net/attachments/653471789971668992/654031830869082112/IMG_4237.JPG?width=613&height=567"));
            G26m4ImageIconPool.addImage("lam", new URL("https://media.discordapp.net/attachments/415882741092057088/654136248750833694/7718086048_IMG_5368.JPG?width=850&height=567"));
        } catch (Exception e) {
            e.printStackTrace();
        }


        JFrame frame = new JFrame("System Panel");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Dimension d = new Dimension(800, 580);
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
            JOptionPane.showMessageDialog(null, comboBox, "Select your Time Slot", JOptionPane.INFORMATION_MESSAGE);
            String slot = (String) comboBox.getSelectedItem();
            if (slot == null) return null;
            String remark = JOptionPane.showInputDialog("Any Remarks? ");
            remark = remark == null || remark.isEmpty() ? "-" : remark;
            return new Object[]{G26Utils.getRandomId(), g26LoginUser.getU_ID(), slot, System.currentTimeMillis(), remark};
        };

        G26Course java = G26Course.Builder.name("Java")
                .columns(header)
                .info("Java is an object-oriented, cross platform, multi-purpose programming language produced by Sun Microsystems.",
                        "First released in 1995, it was developed to be a machine independent web technology.")
                .fileName("G26M4Lam")
                .showAboutMe(component -> {
                    ImageIcon mem = G26m4ImageIconPool.getImage("lam");
                    String Intro = G26m4HtmlTextBuilder.create("A Little programmer who know java, javascript, typescript, python, C#").setFontSize(10).build();
                    JOptionPane.showMessageDialog(null, Intro, "About Me: Lam Chak Wai, Eric", JOptionPane.INFORMATION_MESSAGE, mem);
                })
                .create(createFunction).build();

        G26Course C = G26Course.Builder.name("C")
                .columns(header)
                .info("C is a general-purpose high level language that was originally developed by Dennis Ritchie for the Unix operating system.")
                .fileName("G26M3Liu")
                .showAboutMe(component -> {
                    ImageIcon mem = G26m4ImageIconPool.getImage("liu");
                    String Intro = "Hello, I am Keith Liu";
                    JOptionPane.showMessageDialog(null, Intro, "About Me: Liu Tin Nok, Keith", JOptionPane.INFORMATION_MESSAGE, mem);
                })
                .create(createFunction).build();

        G26Course Cpp = G26Course.Builder.name("C++")
                .columns(header)
                .info("C++ is an intermediate level language, as it comprises a confirmation of both high level and low level language features.",
                        "C++ is a statically typed, free form, multiparadigm, compiled general-purpose language.")
                .fileName("G26M1Ng")
                .showAboutMe(component -> {
                    ImageIcon mem = G26m4ImageIconPool.getImage("libby");
                    String Intro = "Hello, I am Libby Ng";
                    JOptionPane.showMessageDialog(null, Intro, "About Me: Ng Lai Ying, Libby", JOptionPane.INFORMATION_MESSAGE, mem);
                })
                .create(createFunction).build();

        G26Course py = G26Course.Builder.name("Python")
                .columns(header)
                .info("Python is a widely used general-purpose, high level programming language.",
                        "It was initially designed by Guido van Rossum in 1991 and developed by Python Software Foundation.")
                .fileName("G26M2Lin")
                .showAboutMe(component -> {
                    ImageIcon mem = G26m4ImageIconPool.getImage("liu");
                    String Intro = "Hello, I am Matthew Lin";
                    JOptionPane.showMessageDialog(null, Intro, "About Me: LIN Ka Hing, Matthew", JOptionPane.INFORMATION_MESSAGE, mem);
                })
                .create(createFunction).build();

        G26CoursePane.addPart("Java", G26m4Java.class);
        G26CoursePane.addPart("C", G26m3C.class);
        G26CoursePane.addPart("C++", G26m1Cpp.class);
        G26CoursePane.addPart("Python", G26m2Python.class);

        courseManager.addCourse("Java", java);
        courseManager.addCourse("C", C);
        courseManager.addCourse("C++", Cpp);
        courseManager.addCourse("Python", py);

        G26MainStream.setCourseManager(courseManager);
        G26MainStream.setPanelManger(panelManager);

        panelManager.showPanel("Login");
    }
}

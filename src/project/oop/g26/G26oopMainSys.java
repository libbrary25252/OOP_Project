package project.oop.g26;

import project.oop.g26.courses.G26Course;
import project.oop.g26.manager.G26CourseManager;
import project.oop.g26.manager.G26PanelManger;
import project.oop.g26.misc.G26Utils;
import project.oop.g26.panels.*;
import project.oop.g26.panels.courses.G26CoursePane;
import project.oop.g26.panels.courses.G26m3C;
import project.oop.g26.panels.courses.G26m4Java;
import project.oop.g26.roles.G26m4ERole;

import javax.swing.*;
import java.awt.*;
import java.net.MalformedURLException;
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
        } catch (Exception e) {
            e.printStackTrace();
        }


        JFrame frame = new JFrame("System Panel");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Dimension d = new Dimension(800, 500);
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
                .info("Java is an object-oriented, cross platform, multi-purpose programming language produced by Sun Microsystems. First released in 1995, it was developed to be a machine independent web technology.")
                .fileName("G26M1Lam")
                .showAboutMe(component -> {
                    ImageIcon mem = new ImageIcon("Member2.jpg");
                    String[] Intro = {
                            "A Little programmer who know java, javascript, typescript, python, C#",
                            "Now you’re probably thinking something like: “why exactly do I need Java of all options”? ",
                            "As told before, there will never be a programming language with unchallenged authority. ",
                    };
                    JOptionPane.showMessageDialog(null, Intro, "About Me: Eric Lam", JOptionPane.INFORMATION_MESSAGE, mem);
                })
                .create(createFunction).build();

        G26Course C = G26Course.Builder.name("C")
                .columns(header)
                .info("C is a general-purpose high level language that was originally developed by Dennis Ritchie for the Unix operating system.")
                .fileName("G26M4Liu")
                .showAboutMe(component -> {
                    try {
                        ImageIcon mem = new ImageIcon(new URL("https://media.discordapp.net/attachments/641642508555649024/653930412300566580/image0.jpg?width=708&height=470"));
                        String Intro = "Hello, I am Keith Liu";
                        JOptionPane.showMessageDialog(null, Intro, "About Me: Liu Tin Nok, Keith", JOptionPane.INFORMATION_MESSAGE, mem);
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                })
                .create(createFunction).build();

        G26CoursePane.addPart("Java", G26m4Java.class);
        G26CoursePane.addPart("C", G26m3C.class);

        courseManager.addCourse("Java", java);
        courseManager.addCourse("C", C);

        G26MainStream.setCourseManager(courseManager);
        G26MainStream.setPanelManger(panelManager);

        panelManager.showPanel("Login");
    }
}

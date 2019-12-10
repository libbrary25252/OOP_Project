package project.oop.g26.panels.courses;

import project.oop.g26.G26MainStream;
import project.oop.g26.courses.G26Course;
import project.oop.g26.misc.G26m4Permission;

import javax.swing.*;
import java.awt.*;

public final class G26m1Cpp extends G26CoursePane {

    public G26m1Cpp(G26Course g26Course) {
        super(g26Course);
    }

    @Override
    protected void initGUI() {
        JPanel panel = new JPanel();
        panel.setBackground(Color.green);
        g26Course.updateTable();
        panel.add(new JScrollPane(g26Course.getJTable()), CENTER_ALIGNMENT);
        G26MainStream stream = G26MainStream.getStream();
        if (stream.hasPermission(G26m4Permission.ADD_APPOINTMENT)) {
            JButton createButton = new JButton("Make Appointment");
            add(createButton);
            createButton.addActionListener(e -> g26Course.addRecord(stream.getLoginUser()));
        }
        if (stream.hasPermission(G26m4Permission.DELETE_APPOINTMENT)) {
            JButton delButton = new JButton("Delete Appointment");
            add(delButton);
            deletionLinkTable(delButton, g26Course.getJTable(), g26Course.getCsv());
        }
        JButton aboutMe = new JButton("About Me");
        JButton aboutCourse = new JButton("About this course");
        aboutMe.addActionListener(e -> g26Course.showAboutMe(this));
        aboutCourse.addActionListener(e -> JOptionPane.showMessageDialog(this, g26Course.getInformation(), "About this course", JOptionPane.INFORMATION_MESSAGE));
        add(aboutCourse);
        add(aboutMe);
        add(panel);
    }
}

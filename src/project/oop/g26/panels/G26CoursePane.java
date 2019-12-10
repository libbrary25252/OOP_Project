package project.oop.g26.panels;

import project.oop.g26.G26MainStream;
import project.oop.g26.courses.G26Course;
import project.oop.g26.misc.G26m4Permission;

import javax.swing.*;

public final class G26CoursePane extends G26IPanel {

    private final G26Course g26Course;

    public G26CoursePane(G26Course g26Course) {
        this.g26Course = g26Course;
    }

    @Override
    protected void initGUI() {
        JPanel panel = new JPanel();
        g26Course.updateTable();
        panel.add(new JScrollPane(g26Course.getJTable()), CENTER_ALIGNMENT);
        JTextPane operation = new JTextPane();
        G26MainStream stream = G26MainStream.getStream();
        if (stream.hasPermission(G26m4Permission.ADD_APPOINTMENT)) {
            JButton createButton = new JButton("Make Appointment");
            operation.add(createButton);
            createButton.addActionListener(e -> g26Course.addRecord(stream.getLoginUser()));
        }
        if (stream.hasPermission(G26m4Permission.DELETE_APPOINTMENT)) {
            JButton delButton = new JButton("Delete Appointment");
            operation.add(delButton);
            deletionLinkTable(delButton, g26Course.getJTable(), g26Course.getCsv());
        }
        panel.add(operation, RIGHT_ALIGNMENT);
        add(panel);
    }
}

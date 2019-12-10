package project.oop.g26.panels;

import project.oop.g26.G26MainStream;
import project.oop.g26.manager.G26CourseManager;
import project.oop.g26.misc.G26m4HtmlTextBuilder;
import project.oop.g26.panels.courses.G26CoursePane;

import javax.swing.*;


public final class G26CoursePanel extends G26IPanel {


    @Override
    protected void initGUI() {
        setLayout(null);

        JLabel Course = new JLabel(G26m4HtmlTextBuilder.create("My Course: You can view your course and make appointment").setFontSize(10).build());
        Course.setHorizontalAlignment(SwingConstants.LEFT);
        Course.setBounds(10, 10, 500, 20);

        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        tabbedPane.setBounds(10, 40, 600, 400);


        G26CourseManager manager = G26MainStream.getCourseManager();
        for (String language : manager.getLanguages()) {
            G26CoursePane pane = G26CoursePane.getPart(language, manager.getCourse(language));
            if (pane == null) continue;
            pane.initGUI();
            tabbedPane.addTab(language, null, pane, null);
        }


        JButton Back = new JButton("Back");
        Back.setBounds(620, 400, 60, 20);
        addComponents(Course, tabbedPane, Back);

        addPanelChanger(Back, "MainPanel");


    }

}

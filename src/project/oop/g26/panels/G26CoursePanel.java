package project.oop.g26.panels;

import project.oop.g26.misc.G26HtmlTextBuilder;

import javax.swing.*;


public final class G26CoursePanel extends G26IPanel {


    @Override
    protected void initGUI() {
        setLayout(null);

        JLabel Course = new JLabel(G26HtmlTextBuilder.create("My Course: You can view your course and make appointment").setFontSize(7).build());
        Course.setHorizontalAlignment(SwingConstants.LEFT);
        Course.setBounds(10, 10, 500, 20);

        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        tabbedPane.setBounds(10,40,600,400);

        JPanel panel = new JPanel();
        tabbedPane.addTab("Course Info",null, panel, null);

        //add course info based on the UID;
        //insert the JTable to Jtable
        // Jtable -> panel

        JPanel panel2 =new JPanel();
        tabbedPane.addTab("Make Appointment",null, panel2, null);
        JLabel chooseC = new JLabel(G26HtmlTextBuilder.create("Which language course you want to make appointment?").setFontSize(15).build());
        chooseC.setBounds(12,48,600,30);
        JButton Java = new JButton("Java");
        JButton Python = new JButton("Python");
        JButton C = new JButton("C");
        JButton c = new JButton("C++");// C++
        Java.setBounds(15,120,60,40);
        Python.setBounds(115,120,60,40);
        C.setBounds(15,300,60,40);
        c.setBounds(115,300,60,40);//C++
        panel2.add(Java);
        panel2.add(Python);
        panel2.add(C);
        panel2.add(c);
        panel2.add(chooseC);

        panel.setLayout(null);
        panel2.setLayout(null);
        //add(Course);
        //add(tabbedPane);
        addComponents(Course, tabbedPane);







    }

}

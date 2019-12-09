package project.oop.g26.panels;

import project.oop.g26.G26MainStream;
import project.oop.g26.misc.G26HtmlTextBuilder;

import javax.swing.*;
import java.awt.*;


public final class G26MainPanel extends G26IPanel {

    @Override
    protected void initGUI() {
        JLabel w = new JLabel();
        w.setText(G26HtmlTextBuilder.create("Welcome to the programming course system!").setFontSize(10).setColor(Color.GREEN).build());
        JButton loginRecord = new JButton(G26HtmlTextBuilder.create("My Login Record").setFontSize(15).build());
        JButton aboutUs = new JButton(G26HtmlTextBuilder.create("About us").setFontSize(15).build());
        JButton myCourse = new JButton(G26HtmlTextBuilder.create("My Course").setFontSize(15).build());
        JButton profile = new JButton(G26HtmlTextBuilder.create("My Profile").setFontSize(15).build());
        JButton logout = new JButton(G26HtmlTextBuilder.create("Logout").setFontSize(7).build());

        w.setBounds(10, 10, 500, 20);
        profile.setBounds(20, 100, 160, 120);
        myCourse.setBounds(240, 100, 160, 120);
        loginRecord.setBounds(460, 100, 160, 120);
        aboutUs.setBounds(240, 250, 160, 120);
        logout.setBounds(560, 400, 80, 20);

        addComponents(w, profile, myCourse, loginRecord, aboutUs, logout);

        addPanelChanger(profile, "Profile");
        addPanelChanger(myCourse, "MyCourse");

        aboutUs.addActionListener(e -> {
            ImageIcon mem = new ImageIcon("Member2.jpg");
            String Intro = "Member:(left to right)" + "\n" + "LIU Tin Nok" + "\n" + "NG Lai Ying" + "\n" + "LIN Ka " + "\n" + "XXX";
            JOptionPane.showMessageDialog(null, Intro, "About Us: G26, CL01, 2019-2020, OOP", JOptionPane.INFORMATION_MESSAGE, mem);
        });

        //loginRecord.addActionListener(e -> {

        //});

        logout.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Successfully Logout", "Logout Success", JOptionPane.INFORMATION_MESSAGE);
            G26MainStream.logout();
        });
    }

}

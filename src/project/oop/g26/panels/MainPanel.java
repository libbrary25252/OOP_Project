package project.oop.g26.panels;

import project.oop.g26.HtmlTextBuilder;

import javax.swing.*;


public final class MainPanel extends IPanel {

    @Override
    protected void initGUI() {
        JLabel w = new JLabel();
        w.setText("<html><font style=\"color: green\">Welcome to the programming course system!</font></html>");
        JButton loginRecord = new JButton(HtmlTextBuilder.create("My Login Record").setFontSize(15).build());
        JButton makeAppointment = new JButton(HtmlTextBuilder.create("Make Appointment").setFontSize(15).build());
        JButton aboutUs = new JButton(HtmlTextBuilder.create("About us").setFontSize(15).build());
        JButton myCourse = new JButton(HtmlTextBuilder.create("My Course").setFontSize(15).build());
        JButton profile = new JButton(HtmlTextBuilder.create("My Profile").setFontSize(15).build());
        JButton logout = new JButton(HtmlTextBuilder.create("Logout").setFontSize(7).build());

        w.setBounds(10, 10, 500, 20);
        profile.setBounds(20, 100, 160, 120);
        myCourse.setBounds(240, 100, 160, 120);
        loginRecord.setBounds(460, 100, 160, 120);
        makeAppointment.setBounds(20, 250, 160, 120);
        aboutUs.setBounds(240, 250, 160, 120);
        logout.setBounds(560, 400, 80, 20);

        addComponents(w, profile, myCourse, loginRecord, aboutUs, logout, makeAppointment);

        addPanelChanger(profile, "Profile");
        addPanelChanger(myCourse, "MyCourse");
        addPanelChanger(logout, "Login");
        addPanelChanger(makeAppointment, "Appointment");
    }

}
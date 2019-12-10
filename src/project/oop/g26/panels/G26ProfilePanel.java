package project.oop.g26.panels;

import project.oop.g26.misc.G26m4HtmlTextBuilder;

import javax.swing.*;

public final class G26ProfilePanel extends G26IPanel {
    @Override
    protected void initGUI() {

        JLabel Info = new JLabel(G26m4HtmlTextBuilder.create("My Profile").setFontSize(15).build());
        Info.setBounds(10, 10, 500, 20);

        ////add course info based on the UID;
        //        //insert the JTable to Jtable
        //        // Jtable -> panel

        JButton Back = new JButton("Back");
        Back.setBounds(620, 400, 60, 20);

        addComponents(Info, Back);
        addPanelChanger(Back, "MainPanel");


    }
}

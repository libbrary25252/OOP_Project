package project.oop.g26.panels;

import project.oop.g26.misc.G26HtmlTextBuilder;

import javax.swing.*;

public final class G26ProfilePanel extends G26IPanel {
    @Override
    protected void initGUI() {

        JLabel Info = new JLabel(G26HtmlTextBuilder.create("My Profile").setFontSize(15).build());
        Info.setBounds(10, 10, 500, 20);

        ////add course info based on the UID;
        //        //insert the JTable to Jtable
        //        // Jtable -> panel


        addComponents(Info);

    }
}

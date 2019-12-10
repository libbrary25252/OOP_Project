package project.oop.g26.panels;

import project.oop.g26.G26LoginUser;
import project.oop.g26.G26MainStream;
import project.oop.g26.csv.G26m4CSVReader;
import project.oop.g26.misc.G26m4HtmlTextBuilder;
import project.oop.g26.roles.G26IRole;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public final class G26ProfilePanel extends G26IPanel {
    @Override
    protected void initGUI() {
        File UR = G26LoginUser.getUserList();

        try (G26m4CSVReader reader = new G26m4CSVReader(UR)) {
            G26MainStream stream = G26MainStream.getStream();

            final long uid = stream.getLoginUser().getU_ID();
            final String uN = stream.getLoginUser().getU_Name();
            final G26IRole uR = stream.getLoginUser().getU_Role();
            final String uBirth = stream.getLoginUser().getYear_of_Birth();

            JLabel Info = new JLabel(G26m4HtmlTextBuilder.create("You can view your personal info").setFontSize(11).build());
            Info.setBounds(10, 10, 500, 20);

            JLabel uName = new JLabel(G26m4HtmlTextBuilder.create("User Name: ").setFontSize(10).build());
            JLabel uID = new JLabel(G26m4HtmlTextBuilder.create("User ID: ").setFontSize(10).build());
            JLabel uRole = new JLabel(G26m4HtmlTextBuilder.create("User Role: ").setFontSize(10).build());
            JLabel UBirth = new JLabel(G26m4HtmlTextBuilder.create("Year of Birth:").setFontSize(10).build());

            JLabel uNText = new JLabel(uN);
            uNText.setOpaque(true);
            uNText.setBackground(Color.getHSBColor(37, 9, 95));

            JLabel UidText = new JLabel(String.valueOf(uid));
            UidText.setOpaque(true);
            UidText.setBackground(Color.getHSBColor(37, 9, 95));

            JLabel uRText = new JLabel(uR.getName() + "(" + uR.getUserInfo() + ")");
            uRText.setOpaque(true);
            uRText.setBackground(Color.getHSBColor(37, 9, 95));

            JLabel uBText = new JLabel(uBirth);
            uBText.setOpaque(true);
            uBText.setBackground(Color.getHSBColor(37, 9, 95));


            uName.setBounds(120, 100, 100, 30);
            uID.setBounds(120, 150, 100, 30);
            uRole.setBounds(120,200, 100, 30);
            UBirth.setBounds(120, 250, 100, 30);
            uNText.setBounds(250, 100, 150, 30);
            UidText.setBounds(250, 150, 150, 30);
            uRText.setBounds(250, 200, 300, 30);
            uBText.setBounds(250, 250, 150, 30);

            JButton Back = new JButton("Back");
            Back.setBounds(620, 400, 60, 20);


            addComponents(Info, uName, uID, uRole, uNText, UBirth, UidText, uRText, uBText, Back);
            addPanelChanger(Back, "MainPanel");


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

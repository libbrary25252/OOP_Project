package project.oop.g26.panels;

import project.oop.g26.G26LoginUser;
import project.oop.g26.G26MainStream;
import project.oop.g26.csv.G26m4CSVReader;
import project.oop.g26.misc.G26m4HtmlTextBuilder;
import project.oop.g26.misc.G26m4ImageIconPool;
import project.oop.g26.misc.G26m4Permission;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.List;


public final class G26MainPanel extends G26IPanel {

    @Override
    protected void initGUI() {
        JLabel w = new JLabel();
        w.setText(G26m4HtmlTextBuilder.create("Welcome to Our Programming Learning Platform").setFontSize(10).setColor(Color.GREEN).build());
        JButton loginRecord = new JButton(G26m4HtmlTextBuilder.create("My Login Record").setFontSize(15).build());
        JButton aboutUs = new JButton(G26m4HtmlTextBuilder.create("About us").setFontSize(15).build());
        JButton myCourse = new JButton(G26m4HtmlTextBuilder.create("My Course").setFontSize(15).build());
        JButton profile = new JButton(G26m4HtmlTextBuilder.create("My Profile").setFontSize(15).build());
        JButton UserAc = new JButton(G26m4HtmlTextBuilder.create("User Account").setFontSize(15).build());
        JButton G26m4R = new JButton(G26m4HtmlTextBuilder.create("G26m4 Role Panel").setFontSize(15).build());
        JButton logout = new JButton(G26m4HtmlTextBuilder.create("Logout").setFontSize(7).build());

        w.setBounds(10, 10, 500, 20);
        profile.setBounds(20, 100, 160, 120);
        myCourse.setBounds(240, 100, 160, 120);
        loginRecord.setBounds(460, 100, 160, 120);
        aboutUs.setBounds(20, 250, 160, 120);
        UserAc.setBounds(240, 250, 160, 120);
        G26m4R.setBounds(460, 250, 160, 120);
        logout.setBounds(560, 400, 80, 20);

        addComponents(w, profile, myCourse, loginRecord, aboutUs, UserAc, G26m4R,logout);

        addPanelChanger(profile, "Profile");
        addPanelChanger(myCourse, "MyCourse");
        addPanelChanger(UserAc, "UserAccount");
        addPanelChanger(G26m4R, "RolePanel");


        aboutUs.addActionListener(e -> {
            ImageIcon mem = G26m4ImageIconPool.getImage("group");
            String Intro = ("Member:(left to right)" + "\n" + "LIU Tin Nok" + "\n" + "NG Lai Ying" + "\n" + "LIN Ka Hing" + "\n" + "LAM Chak Wai");
            JOptionPane.showMessageDialog(null, Intro, "About Us: G26, CL01, 2019-2020, OOP", JOptionPane.INFORMATION_MESSAGE, mem);
        });

        loginRecord.addActionListener(e -> {
            File LR = G26LoginUser.getLoginRecord();

            try (G26m4CSVReader reader = new G26m4CSVReader(LR)) {
                G26MainStream stream = G26MainStream.getStream();
                final long id = stream.getLoginUser().getU_ID();
                List<String[]> list = stream.hasPermission(G26m4Permission.SHOW_ALL_LOGIN_RECORD) ? reader.readAll() : reader.filter(id, 1);
                JTable table = new JTable(new DefaultTableModel(list.toArray(String[][]::new), reader.readHeader()) {
                    @Override
                    public boolean isCellEditable(int row, int column) {
                        return false;
                    }
                });
                JScrollPane pane = new JScrollPane(table);
                JPanel panel = new JPanel();
                panel.add(pane, LEFT_ALIGNMENT);
                JButton delButton = new JButton("Delete");
                if (stream.hasPermission(G26m4Permission.DELETE_LOGIN_RECORD)) {
                    panel.add(delButton, RIGHT_ALIGNMENT);
                    deletionLinkTable(delButton, table, LR);
                }
                JOptionPane.showMessageDialog(this, panel, "My Login Record", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException ex) {
                ex.printStackTrace();
            }

        });



        logout.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Successfully Logout", "Logout Success", JOptionPane.INFORMATION_MESSAGE);
            G26MainStream.logout();
        });
    }

}

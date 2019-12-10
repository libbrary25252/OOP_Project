package project.oop.g26.panels;

import project.oop.g26.G26MainStream;
import project.oop.g26.csv.G26CSVReader;
import project.oop.g26.misc.G26HtmlTextBuilder;
import project.oop.g26.misc.G26Permission;

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
        aboutUs.setBounds(20, 250, 160, 120);
        logout.setBounds(560, 400, 80, 20);

        addComponents(w, profile, myCourse, loginRecord, aboutUs, logout);

        addPanelChanger(profile, "Profile");
        addPanelChanger(myCourse, "MyCourse");


        aboutUs.addActionListener(e -> {
            ImageIcon mem = new ImageIcon("Member2.jpg");
            String Intro = ("Member:(left to right)" + "\n" + "LIU Tin Nok" + "\n" + "NG Lai Ying" + "\n" + "LIN Ka Hing" + "\n" + "LAM Chak Wai");
            JOptionPane.showMessageDialog(null, Intro, "About Us: G26, CL01, 2019-2020, OOP", JOptionPane.INFORMATION_MESSAGE, mem);
        });

        loginRecord.addActionListener(e -> {
            File userFolder = new File("UserFolder");
            File LR = new File(userFolder, "G26LoginRecord.csv");

            try (G26CSVReader reader = new G26CSVReader(LR)) {
                G26MainStream stream = G26MainStream.getStream();
                final long id = stream.getLoginUser().getU_ID();
                List<String[]> list = stream.hasPermission(G26Permission.SHOW_ALL_LOGIN_RECORD) ? reader.readAll() : reader.filter(id, 1);
                JTable table = new JTable(new DefaultTableModel(list.toArray(String[][]::new), reader.readHeader()) {
                    @Override
                    public boolean isCellEditable(int row, int column) {
                        return false;
                    }
                });
                JScrollPane pane = new JScrollPane(table);
                JOptionPane.showMessageDialog(this, pane, "Login Record", JOptionPane.INFORMATION_MESSAGE);
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

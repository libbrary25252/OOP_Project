package project.oop.g26;

import project.oop.g26.manager.PanelManger;
import project.oop.g26.panels.LoginPanel;
import project.oop.g26.panels.MainPanel;

import javax.swing.*;

public class App {
    public static void main(String[] args) {
        Utils.registerParse(Float.class, Float::parseFloat);
        Utils.registerParse(Integer.class, Integer::parseInt);
        Utils.tryParse("95", Integer.class).map(i -> i + 5).ifPresent(System.out::println);


        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }


        JFrame frame = new JFrame("Panel");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(700, 500);
        frame.setVisible(true);

        PanelManger manger = new PanelManger(frame);

        manger.addPanel("MainPanel", new MainPanel());
        manger.addPanel("Login", new LoginPanel());

        manger.setPanel("Login");


    }
}

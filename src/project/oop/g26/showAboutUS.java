import javax.swing.*;
import java.awt.*;


public class showAboutUS extends IPanel{

    public static void main(String[] args) {
        new showAboutUS();
    }

    private showAboutUS() {
        ImageIcon mem = new ImageIcon("Member2.jpg");
        String Intro = new String("Member:(left to right)" + "\n" + "LIU Tin Nok" + "\n" + "NG Lai Ying" + "\n" +"LIN Ka" + "\n" + "XXX");
        JOptionPane.showMessageDialog(null, Intro, "About Us: G26, CL01, 2019-2020, OOP", JOptionPane.INFORMATION_MESSAGE, mem);
    }
}


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class mainPanel extends JFrame {

    JLabel W;
    JButton LR, Au, c, Pf, Ma, out;

    public mainPanel() {
        super("Home");
        setSize(700,500);
        setDefaultCloseOperation ( EXIT_ON_CLOSE );
        setVisible(true);
        initGUI();
    }

    void initGUI() {

        W = new JLabel("Welcome to the programming course system!");
        LR = new JButton("My Login Record");
        Ma = new JButton("Make Appointment");
        Au = new JButton("About us");
        c = new JButton("My Course");
        Pf = new JButton("My Profile");
        out = new JButton("Logout");

        W.setBounds(10,10,500,20);
        Pf.setBounds(20,100,160,120);
        c.setBounds(240,100,160,120);
        LR.setBounds(460,100,160,120);
        Ma.setBounds(20,250,160,120);
        Au.setBounds(240,250,160,120);
        out.setBounds(560,400,80,20);

        add(W);
        add(Pf);
        add(c);
        add(LR);
        add(Au);
        add(out);

        Pf.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new accountPanel();
            }
        c.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new coursePanel();
            }
        LR.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new LoginRPanel();
                    }
        Ma.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    new aptPanel();
                }
         Au.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    new Aboutus();
                }
         out.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    setVisible(false);
                }

        setLayout(null);
    }

    public static void main(String[] args){

        SwingUtilities.invokeLater(new Runnable() { // for thread concurrency issue
            public void run() {
                JFrame frame = new JFrame("Home");
                new mainPanel().setVisible(true);
            }
        });
        System.out.println("END of main() method!");
    }
}

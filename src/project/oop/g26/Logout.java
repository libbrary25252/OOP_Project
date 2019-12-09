import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

///Login GUI
public class Logout extends JFrame {

    private JButton Loginbtn;
    private JPasswordField p;
    private JTextField ID;
    private JPanel L;
    private JLabel LIN, UN, PW;


    public Logout() {
        super("System Login");
        setSize(500,500);
        setDefaultCloseOperation ( EXIT_ON_CLOSE );
        setVisible(true);
        initGUI();
    }

    void initGUI() {
        Font font = new Font("Tw Cen MT",Font.BOLD,36);
        //LOGIN
        LIN = new JLabel("LOGIN PAGE");
        LIN.setFont(font);
        //ENTER INFO
        UN = new JLabel("User Name");
        PW = new JLabel("Password");
        ID = new JTextField();
        p = new JPasswordField();
        Loginbtn = new JButton("Login");
        LIN.setBounds(120,60,200,40);////Login
        UN.setBounds(120,120,100,20);////UserName
        ID.setBounds(120,140,200,30);////Text field for username
        PW.setBounds(120,190,100,20);////Password
        p.setBounds(120,210,200,30);////Text field for password
        Loginbtn.setBounds(220,290,100,30);
        add(LIN);
        add(UN);
        add(ID);
        add(PW);
        add(p);
        add(Loginbtn);

        Loginbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new mainPanel();
            }
        });
        setLayout(null);

    }


    public static void main(String[] args){

        SwingUtilities.invokeLater(new Runnable() { // for thread concurrency issue
            public void run() {
                JFrame frame = new JFrame("System Login");
                new Logout().setVisible(true);
            }
        });
        System.out.println("END of main() method!");
    }






}

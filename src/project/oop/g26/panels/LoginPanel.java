package project.oop.g26.panels;

import project.oop.g26.LoginUser;
import project.oop.g26.MainStream;
import project.oop.g26.misc.HtmlTextBuilder;

import javax.swing.*;

///Login GUI
public final class LoginPanel extends IPanel {

    @Override
    protected void initGUI() {
        //LOGIN
        JLabel loginTitle = new JLabel(HtmlTextBuilder.create("LOGIN PAGE").setFontSize(15).build());
        //ENTER INFO
        JLabel userNameLabel = new JLabel("User Name");
        JLabel passwordLabel = new JLabel("Password");
        JTextField userId = new JTextField();
        JPasswordField pw = new JPasswordField();
        JButton loginbtn = new JButton("Login");
        loginTitle.setBounds(120, 60, 200, 40);////Login
        userNameLabel.setBounds(120, 120, 100, 20);////UserName
        userId.setBounds(120, 140, 200, 30);////Text field for username
        passwordLabel.setBounds(120, 190, 100, 20);////Password
        pw.setBounds(120, 210, 200, 30);////Text field for password
        loginbtn.setBounds(220, 290, 100, 30);

        addComponents(loginTitle, userNameLabel, userId, passwordLabel, pw, loginbtn);

        loginbtn.addActionListener(e -> {
            LoginUser user = LoginUser.tryLogin(userId.getText(), new String(pw.getPassword()));
            if (user == null) {
                JOptionPane.showMessageDialog(this, "Username of Password not correct", "Login Failed", JOptionPane.WARNING_MESSAGE);
            } else {
                MainStream.login(user);
                userId.setText("");
                pw.setText("");
            }
        });


    }


}

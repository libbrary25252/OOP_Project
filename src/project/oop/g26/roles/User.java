package project.oop.g26.roles;

import project.oop.g26.Permission;

import javax.swing.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public abstract class User {

    static final String ADMINISTRATOR_INFO = "Group Representative";
    static final String GUSER_INFO = "Other members";
    private String passwordHashed;
    private String username;

    public User(String username, String password) {
        this.username = username;
        this.passwordHashed = hashPassword(password);
    }

    public static String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            return Base64.getEncoder().encodeToString(digest.digest(password.getBytes()));
        } catch (NoSuchAlgorithmException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return password;
        }
    }

    public void setPassword(String plainPw) {
        this.passwordHashed = hashPassword(plainPw);
    }

    public String getPasswordHashed() {
        return passwordHashed;
    }

    public String getUserName() {
        return username;
    }

    public abstract String getRoleInfo();

    public abstract Permission[] getPermissions();
}

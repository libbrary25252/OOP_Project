package project.oop.g26.roles;

import project.oop.g26.Permission;

import javax.swing.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public enum ERole implements IRole {

    ADMINISTRATOR(new Administrator()),
    GUSER(new GUser());


    private IRole role;

    ERole(IRole role) {
        this.role = role;
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

    @Override
    public String getUserInfo() {
        return role.getUserInfo();
    }

    public Permission[] getPermissions() {
        return role.getPermissions();
    }
}

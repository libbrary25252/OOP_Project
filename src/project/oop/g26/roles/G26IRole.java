package project.oop.g26.roles;

import project.oop.g26.misc.G26Permission;

import javax.swing.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public interface G26IRole {

    static String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            return bytesToHex(digest.digest(password.getBytes()));
        } catch (NoSuchAlgorithmException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return password;
        }
    }

    private static String bytesToHex(byte[] hashInBytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : hashInBytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

    String getUserInfo();

    G26Permission[] getPermissions();

}

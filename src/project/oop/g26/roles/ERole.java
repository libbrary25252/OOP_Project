package project.oop.g26.roles;

import project.oop.g26.misc.Permission;

import javax.swing.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

public enum ERole implements IRole {

    ADMINISTRATOR(new Administrator()),
    GUSER(new GUser());


    private IRole role;

    ERole(IRole role) {
        this.role = role;
    }

    private static Map<String, IRole> customRoles = new HashMap<>();

    static {
        customRoles.put(ADMINISTRATOR.toString().toLowerCase(), ADMINISTRATOR);
        customRoles.put(GUSER.toString().toLowerCase(), GUSER);
    }

    public static void addRoles(String name, IRole role) {
        customRoles.put(name, role);
    }

    public static IRole getCustomRole(String name) {
        return customRoles.get(name);
    }

    public static String hashPassword(String password) {
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

    @Override
    public String getUserInfo() {
        return role.getUserInfo();
    }

    public Permission[] getPermissions() {
        return role.getPermissions();
    }

}

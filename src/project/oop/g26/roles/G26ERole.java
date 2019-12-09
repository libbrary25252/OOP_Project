package project.oop.g26.roles;

import project.oop.g26.misc.G26Permission;

import javax.swing.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

public enum G26ERole implements G26IRole {

    ADMINISTRATOR(new G26Administrator()),
    GUSER(new G26GUser());


    private G26IRole role;

    G26ERole(G26IRole role) {
        this.role = role;
    }

    private static Map<String, G26IRole> customRoles = new HashMap<>();

    static {
        customRoles.put(ADMINISTRATOR.toString().toLowerCase(), ADMINISTRATOR);
        customRoles.put(GUSER.toString().toLowerCase(), GUSER);
    }

    public static void addRoles(String name, String info, G26Permission... permissions) {
        customRoles.put(name, new G26IRole() {
            @Override
            public String getUserInfo() {
                return info;
            }

            @Override
            public G26Permission[] getPermissions() {
                return permissions;
            }
        });
    }

    public static G26IRole getCustomRole(String name) {
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

    public G26Permission[] getPermissions() {
        return role.getPermissions();
    }

}

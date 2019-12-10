package project.oop.g26.roles;

import project.oop.g26.G26LoginUser;
import project.oop.g26.csv.G26m4CSVModifier;
import project.oop.g26.csv.G26m4CSVReader;
import project.oop.g26.csv.G26m4CSVWriter;
import project.oop.g26.misc.G26m4Permission;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum G26m4ERole implements G26IRole {

    ADMINISTRATOR(new G26Administrator()),
    GUSER(new G26GUser());


    private G26IRole role;

    private static File roleList;

    G26m4ERole(G26IRole role) {
        this.role = role;
    }

    public static void generateDefaultFiles() throws IOException {
        File userFolder = new File("UserFolder");
        userFolder.mkdir();
        roleList = new File(userFolder, "G26User.csv");
        if (roleList.createNewFile()) {
            try (G26m4CSVWriter writer = new G26m4CSVWriter(roleList)) {
                writer.writeHeader(true, "Name", "Info", "Permissions");
            }
        }
    }

    public static String addRoles(String name, String info, G26m4Permission... permissions) {
        try (G26m4CSVModifier modifier = new G26m4CSVModifier(roleList)) {
            boolean contain = modifier.getCachesClone().stream().anyMatch(s -> s[0].equalsIgnoreCase(name));
            if (!contain) {
                modifier.append(name.toUpperCase(), info, Arrays.stream(permissions).map(G26m4Permission::getNode).collect(Collectors.joining("//")));
                modifier.writeAll();
                return null;
            } else {
                return "The role of that name already exist.";
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "Error: " + e.getMessage();
        }
    }

    public static void deleteRole(String name) {
        try (G26m4CSVModifier modifier = new G26m4CSVModifier(roleList);
             G26m4CSVModifier userModifier = new G26m4CSVModifier(G26LoginUser.getUserList())) {
            modifier.remove(0, name);
            modifier.writeAll();
            List<String[]> str = userModifier.getCachesClone();
            for (int i = 0; i < str.size(); i++) {
                String[] arr = str.get(i);
                if (arr[3].equalsIgnoreCase(name)) {
                    arr[3] = G26m4ERole.GUSER.toString();
                    str.set(i, arr);
                }
            }
            userModifier.writeAll(str);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static G26IRole getCustomRole(String name) {
        try (G26m4CSVReader reader = new G26m4CSVReader(roleList)) {
            String[] role = reader.read(name.toUpperCase(), 0);
            return new G26IRole() {
                @Override
                public String getUserInfo() {
                    return role[1];
                }

                @Override
                public G26m4Permission[] getPermissions() {
                    return Arrays.stream(role[2].split("//")).map(G26m4Permission::getPermission).toArray(G26m4Permission[]::new);
                }
            };
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
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

    public G26m4Permission[] getPermissions() {
        return role.getPermissions();
    }

}

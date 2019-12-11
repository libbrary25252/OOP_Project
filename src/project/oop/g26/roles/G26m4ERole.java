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
        if (!userFolder.exists()) userFolder.mkdir();
        roleList = new File(userFolder, "G26m1Role.csv");
        if (roleList.createNewFile()) {
            try (G26m4CSVWriter writer = new G26m4CSVWriter(roleList)) {
                writer.writeHeader(true, "Name", "Info", "Permissions");
                writer.write(G26m4ERole.ADMINISTRATOR, G26m4ERole.ADMINISTRATOR.getUserInfo(), toPermissionCSVString(G26m4ERole.ADMINISTRATOR.getPermissions()));
                writer.write(G26m4ERole.GUSER, G26m4ERole.GUSER.getUserInfo(), toPermissionCSVString(G26m4ERole.GUSER.getPermissions()));
            }
        }
    }

    private static String toPermissionCSVString(G26m4Permission[] permissions) {
        return Arrays.stream(permissions).map(G26m4Permission::getNode).collect(Collectors.joining("//"));
    }

    public static File getRoleList() {
        return roleList;
    }

    public static String changeRoleInfo(String name, String info) {
        try (G26m4CSVModifier modifier = new G26m4CSVModifier(roleList)) {
            int pos = modifier.getPos(0, name);
            if (pos == -1) return "Cannot find that role";
            modifier.modify(pos, 1, info);
            modifier.writeAll();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return "Error: " + e.getMessage();
        }
    }

    public static String changePermissions(String name, G26m4Permission... permissions) {
        try (G26m4CSVModifier modifier = new G26m4CSVModifier(roleList)) {
            int pos = modifier.getPos(0, name);
            if (pos == -1) return "Cannot find that role";
            modifier.modify(pos, 2, toPermissionCSVString(permissions));
            modifier.writeAll();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return "Error: " + e.getMessage();
        }
    }

    public static String addRole(String name, String info, G26m4Permission... permissions) {
        try (G26m4CSVModifier modifier = new G26m4CSVModifier(roleList)) {
            boolean contain = modifier.getCachesClone().stream().anyMatch(s -> s[0].equalsIgnoreCase(name));
            if (!contain) {
                modifier.append(name.toUpperCase(), info, toPermissionCSVString(permissions));
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
            int pos = userModifier.getPos(3, name);
            userModifier.modify(pos, 3, GUSER.toString());
            userModifier.writeAll();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static G26IRole getCustomRole(String name) {
        try (G26m4CSVReader reader = new G26m4CSVReader(roleList)) {
            String[] role = reader.read(name.toUpperCase(), 0);
            if (role == null) return null;
            return new G26IRole() {
                @Override
                public String getName() {
                    return name.toUpperCase();
                }

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
    public String getName() {
        return role.getName();
    }

    @Override
    public String getUserInfo() {
        return role.getUserInfo();
    }

    public G26m4Permission[] getPermissions() {
        return role.getPermissions();
    }

}

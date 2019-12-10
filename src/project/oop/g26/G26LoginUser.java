package project.oop.g26;

import project.oop.g26.csv.G26m4CSVModifier;
import project.oop.g26.csv.G26m4CSVReader;
import project.oop.g26.csv.G26m4CSVUtils;
import project.oop.g26.csv.G26m4CSVWriter;
import project.oop.g26.misc.G26Utils;
import project.oop.g26.roles.G26IRole;
import project.oop.g26.roles.G26m4ERole;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class G26LoginUser {

    private static File userList;
    private static File loginRecord;

    private G26LoginUser(String[] strings) {
        this(Long.parseLong(strings[0]), strings[1], strings[2], G26m4ERole.getCustomRole(strings[3]), strings[4]);
    }

    public static File getUserList() {
        return userList;
    }

    public static void generateDefaultFiles() throws IOException {
        File userFolder = new File("UserFolder");
        userFolder.mkdir();
        userList = new File(userFolder, "G26User.csv");
        if (userList.createNewFile()) {
            G26Utils.debug("Successfully generated user file");
            try (G26m4CSVWriter writer = new G26m4CSVWriter(userList)) {
                writer.writeHeader(true, "U_ID", "Encrypted_Password", "U_Name", "U_Role", "Year_Of_Birth");
                writer.write(G26Utils.getRandomId(), G26m4ERole.hashPassword("a"), "admin", G26m4ERole.ADMINISTRATOR, "12-09-1990");
                writer.write(G26Utils.getRandomId(), G26m4ERole.hashPassword("g"), "Default", G26m4ERole.GUSER, "30-06-2000");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        loginRecord = new File(userFolder, "G26LoginRecord.csv");
        if (loginRecord.createNewFile()) {
            G26Utils.debug("Successfully generated login record file");
            try (G26m4CSVWriter writer = new G26m4CSVWriter(loginRecord)) {
                writer.writeHeader(true, "LR_ID", "U_ID", "Login_Time", "Remarks");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private G26IRole U_Role;

    private G26LoginUser(long u_ID, String encrypted_Password, String u_Name, G26IRole u_Role, String year_of_Birth) {
        U_ID = u_ID;
        Encrypted_Password = encrypted_Password;
        U_Name = u_Name;
        U_Role = u_Role;
        Year_of_Birth = year_of_Birth;
    }

    private long U_ID;
    private String Encrypted_Password;
    private String U_Name;

    public static void removeUser(long... u_ID) {
        try (G26m4CSVModifier modifier = new G26m4CSVModifier(userList)) {
            modifier.remove(u_ID);
            modifier.writeAll();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String Year_of_Birth;

    public static String toCSV(long u_ID, String u_Name, String encrypted_Password, G26IRole u_Role, String year_of_Birth) {
        return new G26LoginUser(u_ID, u_Name, encrypted_Password, u_Role, year_of_Birth).toString();
    }

    public static G26LoginUser tryLogin(String name, String password) {
        String hPw = G26m4ERole.hashPassword(password);
        List<String[]> strr = G26m4CSVUtils.fastRead(userList);
        for (String[] str : strr) {
            boolean pass = str[2].equalsIgnoreCase(name) && str[1].equals(hPw);
            if (pass) {
                G26LoginUser user = new G26LoginUser(str);
                user.recordLogin();
                return user;
            }
        }
        return null;
    }

    public void recordLogin() {
        if (G26MainStream.isLogged()) throw new IllegalStateException("Already Logged in");
        try (G26m4CSVWriter writer = new G26m4CSVWriter(loginRecord, true);
             G26m4CSVReader reader = new G26m4CSVReader(loginRecord)) {
            boolean firstLogin = reader.filter(U_ID).isEmpty();
            writer.write(G26Utils.getRandomId(), U_ID, System.currentTimeMillis(), firstLogin ? "First Login" : "-");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public long getU_ID() {
        return U_ID;
    }

    public String getEncrypted_Password() {
        return Encrypted_Password;
    }

    public String getU_Name() {
        return U_Name;
    }

    public G26IRole getU_Role() {
        return U_Role;
    }

    public String getYear_of_Birth() {
        return Year_of_Birth;
    }

    @Override
    public String toString() {
        return String.join(",", new String[]{this.U_ID + "", this.Encrypted_Password, this.U_Name, this.U_Role.toString(), this.Year_of_Birth});
    }
}

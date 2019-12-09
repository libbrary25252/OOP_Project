package project.oop.g26;

import project.oop.g26.misc.G26Utils;
import project.oop.g26.roles.G26ERole;
import project.oop.g26.roles.G26IRole;

import java.io.*;

public class G26LoginUser {

    private static File userList;
    private static File loginRecord;

    static {
        File userFolder = new File("UserFolder");
        if (!userFolder.exists()) userFolder.mkdir();
        userList = new File(userFolder, "G26User.csv");
        if (!userList.exists()) {
            try {
                if (userList.createNewFile()) {
                    try (PrintWriter writer = new PrintWriter(new FileOutputStream(userList))) {
                        writer.println(String.join(",", new String[]{"U_ID", "Encrypted_Password", "U_Name", "U_Role", "Year_Of_Birth"}));
                        writer.println(toCSV(G26Utils.getRandomId(), "Root", G26ERole.hashPassword("a"), G26ERole.ADMINISTRATOR, "????"));
                        writer.println(toCSV(G26Utils.getRandomId(), "Default", G26ERole.hashPassword("g"), G26ERole.GUSER, "????"));
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        loginRecord = new File(userFolder, "G26LoginRecord.csv");
        if (!loginRecord.exists()) {
            try {
                if (loginRecord.createNewFile()) {
                    try (PrintWriter writer = new PrintWriter(new FileOutputStream(loginRecord))) {
                        writer.println("LR_ID,U_ID,Login_Time,Remarks");
                    }
                }
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

    private G26LoginUser(String[] strings) {
        this(Long.parseLong(strings[0]), strings[1], strings[2], G26ERole.valueOf(strings[3]), strings[4]);
    }

    private String Year_of_Birth;

    public static String toCSV(long u_ID, String u_Name, String encrypted_Password, G26IRole u_Role, String year_of_Birth) {
        return new G26LoginUser(u_ID, u_Name, encrypted_Password, u_Role, year_of_Birth).toString();
    }

    public static G26LoginUser tryLogin(String name, String password) {
        String hPw = G26ERole.hashPassword(password);
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(userList)))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] str = line.split(",");
                boolean pass = str[2].equalsIgnoreCase(name) && str[1].equals(hPw);
                if (pass) {
                    G26LoginUser user = new G26LoginUser(str);
                    user.recordLogin();
                    return user;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void recordLogin() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(loginRecord, true));
             BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(loginRecord)))) {
            boolean firstLogin = reader.lines().noneMatch(s -> s.split(",")[1].equals(U_ID + ""));
            writer.println(String.format("%s,%d,%d,%s", G26Utils.getRandomId(), U_ID, System.currentTimeMillis(), firstLogin ? "First Login" : "-"));
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

package project.oop.g26;

import project.oop.g26.roles.ERole;
import project.oop.g26.roles.IRole;

public class LoginUser {

    private long U_ID;
    private String Encrypted_Password;
    private String U_Name;
    private IRole U_Role;
    private String Year_of_Birth;

    private LoginUser(long u_ID, String u_Name, String encrypted_Password, IRole u_Role, String year_of_Birth) {
        U_ID = u_ID;
        U_Name = u_Name;
        U_Role = u_Role;
        Encrypted_Password = encrypted_Password;
        Year_of_Birth = year_of_Birth;
    }

    public static LoginUser tryLogin(String u_ID, String password) {
        // do sth from file

        return new LoginUser(Integer.parseInt(u_ID), "abc", ERole.hashPassword(password), ERole.ADMINISTRATOR, "dawidhaiwdhawi");
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

    public IRole getU_Role() {
        return U_Role;
    }

    public String getYear_of_Birth() {
        return Year_of_Birth;
    }
}

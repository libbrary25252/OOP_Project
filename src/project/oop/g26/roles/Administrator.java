package project.oop.g26.roles;

import project.oop.g26.Permission;

public class Administrator extends User {


    public Administrator(String username, String password) {
        super(username, password);
    }

    @Override
    public String getRoleInfo() {
        return User.ADMINISTRATOR_INFO;
    }

    @Override
    public Permission[] getPermissions() {
        return Permission.values(); // administrator has all permissions
    }
}

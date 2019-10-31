package project.oop.g26.roles;

import project.oop.g26.Permission;

public class GUser extends User {

    public GUser(String username, String password) {
        super(username, password);
    }

    @Override
    public String getRoleInfo() {
        return User.GUSER_INFO;
    }

    @Override
    public Permission[] getPermissions() {
        return new Permission[0];
    }

}

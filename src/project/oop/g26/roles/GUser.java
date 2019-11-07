package project.oop.g26.roles;

import project.oop.g26.Permission;

public class GUser extends User {

    private Permission[] permissions;

    public GUser(String username, String password) {
        super(username, password);
        this.permissions = new Permission[]{
                Permission.SHOW_ABOUT_US,
        };
    }

    @Override
    public String getRoleInfo() {
        return User.GUSER_INFO;
    }

    @Override
    public Permission[] getPermissions() {
        return permissions;
    }

}

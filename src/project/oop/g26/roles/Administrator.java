package project.oop.g26.roles;

import project.oop.g26.misc.Permission;

final class Administrator implements IRole {

    @Override
    public String getUserInfo() {
        return "admin info";
    }

    @Override
    public Permission[] getPermissions() {
        return Permission.values(); // administrator has all permissions
    }
}

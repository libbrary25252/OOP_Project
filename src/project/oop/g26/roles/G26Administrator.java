package project.oop.g26.roles;

import project.oop.g26.misc.G26m4Permission;

final class G26Administrator implements G26IRole {

    @Override
    public String getUserInfo() {
        return "admin info";
    }

    @Override
    public G26m4Permission[] getPermissions() {
        return G26m4Permission.values(); // administrator has all permissions
    }
}

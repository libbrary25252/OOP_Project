package project.oop.g26.roles;

import project.oop.g26.misc.G26Permission;

final class G26Administrator implements G26IRole {

    @Override
    public String getUserInfo() {
        return "admin info";
    }

    @Override
    public G26Permission[] getPermissions() {
        return G26Permission.values(); // administrator has all permissions
    }
}

package project.oop.g26.roles;

import project.oop.g26.misc.G26m4Permission;

final class G26GUser implements G26IRole {

    private G26m4Permission[] permissions;

    public G26GUser() {
        this.permissions = new G26m4Permission[]{
                G26m4Permission.ADD_APPOINTMENT,
                G26m4Permission.DELETE_APPOINTMENT,
        };
    }

    @Override
    public String getUserInfo() {
        return "user info";
    }

    @Override
    public G26m4Permission[] getPermissions() {
        return permissions;
    }
}

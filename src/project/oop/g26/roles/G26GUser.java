package project.oop.g26.roles;

import project.oop.g26.misc.G26Permission;

final class G26GUser implements G26IRole {

    private G26Permission[] permissions;

    public G26GUser() {
        this.permissions = new G26Permission[]{
                G26Permission.SHOW_ABOUT_US,
        };
    }

    @Override
    public String getUserInfo() {
        return "user info";
    }

    @Override
    public G26Permission[] getPermissions() {
        return permissions;
    }
}

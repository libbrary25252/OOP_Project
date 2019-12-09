package project.oop.g26.roles;

import project.oop.g26.Permission;

final class GUser implements IRole {

    private Permission[] permissions;

    public GUser() {
        this.permissions = new Permission[]{
                Permission.SHOW_ABOUT_US,
        };
    }

    @Override
    public String getUserInfo() {
        return "user info";
    }

    @Override
    public Permission[] getPermissions() {
        return permissions;
    }
}

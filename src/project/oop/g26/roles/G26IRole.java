package project.oop.g26.roles;

import project.oop.g26.misc.G26Permission;

public interface G26IRole {

    String getUserInfo();

    G26Permission[] getPermissions();

}

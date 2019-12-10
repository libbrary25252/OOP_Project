package project.oop.g26.roles;

import project.oop.g26.misc.G26m4Permission;

public interface G26IRole {

    String getName();

    String getUserInfo();

    G26m4Permission[] getPermissions();

}

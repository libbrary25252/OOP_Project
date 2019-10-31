package project.oop.g26;

import java.util.Arrays;

public enum Permission {
    CREATE_USER_ACCOUNT,
    DELETE_USER_ACCOUNT,
    CREATE_ADMIN_ACCOUNT,
    DELETE_ADMIN_ACCOUNT,
    CREATE_COURSE,
    DELETE_COURSE,
    SHOW_ABOUT_US,
    DISPLAY_USER_ACCOUNTS,
    EDIT_PASSWORD,
    ADD_RECORD,
    DELETE_RECORD;

    private String node;

    Permission() {
        node = this.name().replaceAll("_", ".").toLowerCase();
    }

    public static Permission getPermission(String node) {
        return Arrays.stream(values()).filter(p -> p.node.equalsIgnoreCase(node)).findAny().orElse(null);
    }

    public String getNode() {
        return node;
    }
}

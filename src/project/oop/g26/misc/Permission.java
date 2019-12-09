package project.oop.g26.misc;

import java.util.Arrays;

public enum Permission {
    /*
    Account
     */
    CREATE_USER_ACCOUNT,
    DELETE_USER_ACCOUNT,
    EDIT_OTHER_PASSWORD,
    /*
    main
     */
    SHOW_ABOUT_US,
    EDIT_PASSWORD,
    /*
    Course
     */
    ADD_RECORD,
    DELETE_RECORD,
    SHOW_RECORD;

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

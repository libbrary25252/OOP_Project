package project.oop.g26.misc;

import java.util.Arrays;

public enum G26Permission {
    /*
    Account management
     */
    MODIFY_USER_ACCOUNT,
    MODIFY_ROLE,
    /*
    main
     */
    DELETE_LOGIN_RECORD,
    SHOW_ALL_LOGIN_RECORD,
    /*
    Course
     */
    ADD_APPOINTMENT,
    DELETE_APPOINTMENT,
    SHOW_ALL_APPOINTMENT;

    private String node;

    G26Permission() {
        node = this.name().replaceAll("_", ".").toLowerCase();
    }

    public static G26Permission getPermission(String node) {
        return Arrays.stream(values()).filter(p -> p.node.equalsIgnoreCase(node)).findAny().orElse(null);
    }

    public String getNode() {
        return node;
    }
}

package project.oop.g26;

import project.oop.g26.courses.Course;
import project.oop.g26.roles.User;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainStream {

    private static Map<Permission, String> coursePanel = new HashMap<>();
    private static Map<Permission, String> accountPanel = new HashMap<>();
    private static Map<Permission, String> mainPanel = new HashMap<>();

    static {
        coursePanel.put(Permission.ADD_RECORD, "1. add record");
        coursePanel.put(Permission.SHOW_RECORD, "2. list records");
        coursePanel.put(Permission.DELETE_RECORD, "3. delete record");
        accountPanel.put(Permission.CREATE_USER_ACCOUNT, "1. create account");
        accountPanel.put(Permission.DELETE_USER_ACCOUNT, "2. delete account");
        accountPanel.put(Permission.EDIT_OTHER_PASSWORD, "3. edit other password");
        mainPanel.put(Permission.SHOW_ABOUT_US, "1. show about us");
        mainPanel.put(Permission.EDIT_PASSWORD, "2. edit password");
    }

    private final User login;
    private final List<Permission> permissions;
    private CourseManager courseManager;

    private MainStream(User login, CourseManager courseManager) {
        this.login = login;
        this.permissions = Arrays.asList(login.getPermissions());
        this.courseManager = courseManager;

    }

    public static MainStream login(User login, CourseManager manager) {
        return new MainStream(login, manager);
    }

    public void getInto(String courseName) {
        Course course = this.courseManager.getCourse(courseName);
        if (course == null) {
            System.out.println("Error: This course " + courseName + " does not exist!");
        } else {
            coursePanel(course);
        }
    }

    private void mainPanel() {

    }

    private void showAboutUs() {
        System.out.println("This is show about us");
        mainPanel();
    }

    private void editPassword() {
        System.out.println("enter pw");
    }

    private void accountPanel() {

    }

    private void coursePanel(Course course) {

    }

    private void logout() {

    }


}

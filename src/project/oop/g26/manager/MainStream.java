package project.oop.g26.manager;

import project.oop.g26.LoginUser;
import project.oop.g26.Permission;
import project.oop.g26.courses.Course;

import java.util.Arrays;
import java.util.List;

public class MainStream {

    private final LoginUser login;
    private final List<Permission> permissions;
    private CourseManager courseManager;

    private MainStream(LoginUser login, CourseManager courseManager) {
        this.login = login;
        this.permissions = Arrays.asList(login.getU_Role().getPermissions());
        this.courseManager = courseManager;
    }

    public static MainStream login(LoginUser login, CourseManager manager) {
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

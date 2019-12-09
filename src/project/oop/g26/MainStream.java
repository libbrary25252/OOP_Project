package project.oop.g26;

import project.oop.g26.courses.Course;
import project.oop.g26.manager.CourseManager;
import project.oop.g26.manager.PanelManger;
import project.oop.g26.misc.Permission;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class MainStream {

    private static MainStream stream;
    private static CourseManager courseManager;
    private static PanelManger panelManger;

    private final LoginUser login;
    private final List<Permission> permissions;


    private MainStream(LoginUser login) {
        this.login = login;
        this.permissions = Arrays.asList(login.getU_Role().getPermissions());
    }

    public static void login(LoginUser login) {
        stream = new MainStream(login);
        System.out.println("Successfully login for " + login.getU_Name());
        panelManger.showPanel("MainPanel");
    }

    public static void logout() {
        stream = null;
        panelManger.showPanel("Login");
    }

    public static CourseManager getCourseManager() {
        return courseManager;
    }

    static void setCourseManager(CourseManager courseManager) {
        MainStream.courseManager = courseManager;
    }

    public static PanelManger getPanelManger() {
        return panelManger;
    }

    static void setPanelManger(PanelManger panelManger) {
        MainStream.panelManger = panelManger;
    }

    public static MainStream getStream() {
        return Optional.ofNullable(stream).orElseThrow(() -> new IllegalStateException("User Not Login"));
    }

    public boolean hasPermission(Permission permission) {
        return this.permissions.contains(permission);
    }

    public boolean hasPermission(String node) {
        return hasPermission(Permission.getPermission(node));
    }

    public Course getCourse(String course) {
        return courseManager.getCourse(course);
    }


}

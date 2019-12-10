package project.oop.g26;

import project.oop.g26.courses.G26Course;
import project.oop.g26.manager.G26CourseManager;
import project.oop.g26.manager.G26PanelManger;
import project.oop.g26.misc.G26Permission;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class G26MainStream {

    private static G26MainStream stream;
    private static G26CourseManager courseManager;
    private static G26PanelManger panelManger;

    private final G26LoginUser login;
    private final List<G26Permission> permissions;


    private G26MainStream(G26LoginUser login) {
        this.login = login;
        this.permissions = Arrays.asList(login.getU_Role().getPermissions());
    }

    public static void login(G26LoginUser login) {
        stream = new G26MainStream(login);
        System.out.println("Successfully login for " + login.getU_Name());
        panelManger.showPanel("MainPanel");
    }

    public static void logout() {
        stream = null;
        panelManger.showPanel("Login");
    }

    public static boolean isLogged() {
        return stream != null;
    }

    public static G26CourseManager getCourseManager() {
        return courseManager;
    }

    static void setCourseManager(G26CourseManager courseManager) {
        G26MainStream.courseManager = courseManager;
    }

    public static G26PanelManger getPanelManger() {
        return panelManger;
    }

    static void setPanelManger(G26PanelManger panelManger) {
        G26MainStream.panelManger = panelManger;
    }

    public static G26MainStream getStream() {
        return Optional.ofNullable(stream).orElseThrow(() -> new IllegalStateException("User Not Login"));
    }

    public G26LoginUser getLoginUser() {
        return login;
    }

    public boolean hasPermission(G26Permission permission) {
        return this.permissions.contains(permission);
    }

    public boolean hasPermission(String node) {
        return hasPermission(G26Permission.getPermission(node));
    }

    public G26Course getCourse(String course) {
        return courseManager.getCourse(course);
    }


}

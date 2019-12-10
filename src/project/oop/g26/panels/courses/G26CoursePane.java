package project.oop.g26.panels.courses;

import project.oop.g26.courses.G26Course;
import project.oop.g26.panels.G26IPanel;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

public abstract class G26CoursePane extends G26IPanel {

    private static Map<String, Class<? extends G26CoursePane>> individualParts = new HashMap<>();
    protected final G26Course g26Course;

    public G26CoursePane(G26Course g26Course) {
        if (g26Course == null) throw new IllegalStateException("Course is null");
        this.g26Course = g26Course;
    }

    public static <T extends G26CoursePane> void addPart(String courseName, Class<T> pane) {
        individualParts.put(courseName, pane);
    }

    public static G26CoursePane getPart(String language, G26Course course) {
        Class<? extends G26CoursePane> cls = individualParts.get(language);
        if (cls == null) return null;
        try {
            Constructor<? extends G26CoursePane> constructor = cls.getConstructor(G26Course.class);
            constructor.setAccessible(true);
            return constructor.newInstance(course);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}

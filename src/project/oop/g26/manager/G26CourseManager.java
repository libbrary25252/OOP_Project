package project.oop.g26.manager;

import project.oop.g26.courses.G26Course;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class G26CourseManager {

    private final Map<String, G26Course> courseMap = new ConcurrentHashMap<>();

    public void addCourse(String string, G26Course course) {
        this.courseMap.putIfAbsent(string, course);
    }

    public G26Course getCourse(String courseName) {
        return this.courseMap.get(courseName);
    }

    public Set<String> getLanguages() {
        return courseMap.keySet();
    }

}

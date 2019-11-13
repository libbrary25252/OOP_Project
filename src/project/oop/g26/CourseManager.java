package project.oop.g26;

import project.oop.g26.courses.Course;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CourseManager {

    private final Map<String, Course> courseMap = new ConcurrentHashMap<>();

    public void addCourse(String string, Course course) {
        this.courseMap.putIfAbsent(string, course);
    }

    public Course getCourse(String courseName) {
        return this.courseMap.get(courseName);
    }

}

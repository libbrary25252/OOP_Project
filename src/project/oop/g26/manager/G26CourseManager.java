package project.oop.g26.manager;

import project.oop.g26.courses.G26Course;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class G26CourseManager {

    private final Map<String, G26Course> courseMap = new ConcurrentHashMap<>();
    private G26Course currentCourse;

    public void addCourse(String string, G26Course course) {
        this.courseMap.putIfAbsent(string, course);
    }

    public G26Course getCourse(String courseName) {
        if (this.currentCourse != null) this.currentCourse.outPutRecords();
        this.currentCourse = this.courseMap.get(courseName);
        this.currentCourse.loadRecords();
        return currentCourse;
    }

}

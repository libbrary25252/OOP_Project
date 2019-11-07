package project.oop.g26;

import project.oop.g26.courses.Course;

import java.sql.Date;
import java.time.Instant;

public class App {
    public static void main(String[] args) {
        Utils.registerParse(Float.class, Float::parseFloat);
        Utils.registerParse(Integer.class, Integer::parseInt);
        Utils.tryParse("95", Integer.class).map(i -> i + 5).ifPresent(System.out::println);

        Course.Builder.name("Java").info("This is a java course", "which teach you java").create(gUser -> {
            String uid = gUser.getUserName();
            String time = Date.from(Instant.now()).toString();
            String course = "First";
            return new String[]{uid, time, course};
        });
    }
}

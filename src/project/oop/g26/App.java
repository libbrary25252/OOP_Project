package project.oop.g26;

import project.oop.g26.courses.Course;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Utils.registerParse(Float.class, Float::parseFloat);
        Utils.registerParse(Integer.class, Integer::parseInt);
        Utils.tryParse("95", Integer.class).map(i -> i + 5).ifPresent(System.out::println);

        Course course = Course.Builder.name("Java").info("This is a java course", "which teach you java").create(gUser -> {
            Scanner scanner = new Scanner(System.in);
            return new String[0];
        }).build();




    }
}

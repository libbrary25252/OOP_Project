package project.oop.g26;

public class App {
    public static void main(String[] args) {
        Utils.registerParse(Float.class, Float::parseFloat);
        Utils.registerParse(Integer.class, Integer::parseInt);
        Utils.tryParse("95", Integer.class).map(i -> i + 5).ifPresent(System.out::println);
    }
}

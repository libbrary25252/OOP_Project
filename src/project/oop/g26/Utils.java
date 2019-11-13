package project.oop.g26;

import java.util.*;
import java.util.function.Function;

public class Utils {
    private static Map<Class<? extends Number>, Function<String, ? extends Number>> parseMap = new HashMap<>();

    private Utils() {
    }

    public static <T extends Number> void registerParse(Class<T> type, Function<String, T> parse) {
        parseMap.put(type, parse);
    }

    @SuppressWarnings("unchecked")
    public static <T extends Number> Optional<T> tryParse(String str, Class<T> type) {
        try {
            Function<String, ? extends Number> parse = parseMap.get(type);
            if (type == null) {
                System.out.println("Cannot get this type of parser.");
                return Optional.empty();
            }
            return Optional.ofNullable((T) parse.apply(str));
        } catch (NumberFormatException e) {
            System.out.println("Parse failed");
            return Optional.empty();
        }
    }

    public static <T extends Number> boolean test(String str, Class<T> type) {
        return tryParse(str, type).isPresent();
    }

    public static void debug(String message) {
        System.out.println("[DEBUG] " + message);
    }

    public static boolean notNull(Object... nonNull) {
        return Arrays.stream(nonNull).allMatch(Objects::nonNull);
    }
}

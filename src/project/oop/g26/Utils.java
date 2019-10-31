package project.oop.g26;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
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
}

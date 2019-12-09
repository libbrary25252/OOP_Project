package project.oop.g26;

import project.oop.g26.roles.ERole;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Utils {

    public static int U_ID = 0;

    private static Map<Class<? extends Number>, Function<String, ? extends Number>> parseMap = new HashMap<>();

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

    public static void generateDefaultUser() throws IOException {
        File folder = new File("UserFolder");
        if (!folder.exists()) folder.mkdir();
        File userList = new File(folder, "G26User.csv");
        if (!userList.exists()) {
            userList.createNewFile();
            try (PrintWriter writer = new PrintWriter(new FileOutputStream(userList))) {
                writer.println(Arrays.stream(LoginUser.class.getDeclaredFields()).map(Field::getName).collect(Collectors.joining(",")));
                writer.println(String.join(",", List.of(++U_ID + "", ERole.hashPassword("a"), "Lam Chak Wai", ERole.ADMINISTRATOR.toString(), "2000")));
            }
        }

    }
}

package project.oop.g26.misc;

import javax.swing.*;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class G26m4ImageIconPool {
    private static Map<String, ImageIcon> iconMap = new HashMap<>();

    public static void addImage(String name, URL url) {
        iconMap.putIfAbsent(name, new ImageIcon(url));
    }

    public static ImageIcon getImage(String name) {
        return iconMap.get(name);
    }
}

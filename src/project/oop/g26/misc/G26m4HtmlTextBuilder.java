package project.oop.g26.misc;

import java.awt.*;

public class G26m4HtmlTextBuilder {
    private final String text;
    private Color color = Color.BLACK;
    private int size = 10;
    private String family = "Microsoft Jhenghei";

    private G26m4HtmlTextBuilder(String text) {
        this.text = text;
    }

    public static G26m4HtmlTextBuilder create(String text) {
        return new G26m4HtmlTextBuilder(text);
    }

    public G26m4HtmlTextBuilder setColor(Color color) {
        this.color = color;
        return this;
    }

    public G26m4HtmlTextBuilder setFontSize(int size) {
        this.size = size;
        return this;
    }

    public G26m4HtmlTextBuilder setFontFamily(String family) {
        this.family = family;
        return this;
    }

    public String build() {
        return "<html><font style=\"color: " + color.toString().toLowerCase() + "; font-size: " + size + "px; font-family: " + family + "\">" + text + "</font></html>";
    }

}

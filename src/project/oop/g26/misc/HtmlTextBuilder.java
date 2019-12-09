package project.oop.g26.misc;

import java.awt.*;

public class HtmlTextBuilder {
    private final String text;
    private Color color = Color.BLACK;
    private int size = 10;
    private String family = "Microsoft Jhenghei";

    private HtmlTextBuilder(String text) {
        this.text = text;
    }

    public static HtmlTextBuilder create(String text) {
        return new HtmlTextBuilder(text);
    }

    public HtmlTextBuilder setColor(Color color) {
        this.color = color;
        return this;
    }

    public HtmlTextBuilder setFontSize(int size) {
        this.size = size;
        return this;
    }

    public HtmlTextBuilder setFontFamily(String family) {
        this.family = family;
        return this;
    }

    public String build() {
        return "<html><font style=\"color: " + color.toString().toLowerCase() + "; font-size: " + size + "px; font-family: " + family + "\">" + text + "</font></html>";
    }

}

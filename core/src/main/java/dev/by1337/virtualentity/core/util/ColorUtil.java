package dev.by1337.virtualentity.core.util;

import org.bukkit.Color;

public class ColorUtil {
    public static int asARGB(Color color, int alpha) {
        return (0xff & alpha) << 24 | color.getRed() << 16 | color.getGreen() << 8 | color.getBlue();
    }

    public static Color fromARGB(int argb) {
        return Color.fromARGB(argb);
    }
}

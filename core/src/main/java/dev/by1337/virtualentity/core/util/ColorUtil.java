package dev.by1337.virtualentity.core.util;

import dev.by1337.virtualentity.core.annotations.ASM;
import org.bukkit.Color;
import org.by1337.blib.util.Version;

public class ColorUtil {
    public static int asARGB(Color color, int alpha) {
        return (0xff & alpha) << 24 | color.getRed() << 16 | color.getGreen() << 8 | color.getBlue();
    }

    public static Color fromARGB(int argb) {
        if (Version.VERSION.newerThanOrEqual(Version.V1_19_4)) {
            return fromARGB0(argb);
        } else {
            return Color.fromRGB(argb >> 16 & 0xff, argb >> 8 & 0xff, argb & 0xff);
        }
    }

    @ASM
    private static Color fromARGB0(int argb) {
        String asm = """
                A:
                    iload 0
                    invokestatic org/bukkit/Color fromARGB (I)Lorg/bukkit/Color;
                    areturn
                B:
                """;
        System.out.println(asm);
        throw new IllegalStateException("ASM is not applied");
    }
}

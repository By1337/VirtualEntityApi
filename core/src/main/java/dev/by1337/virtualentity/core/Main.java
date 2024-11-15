package dev.by1337.virtualentity.core;

import dev.by1337.virtualentity.core.annotations.ASM;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    @Override
    @ASM
    public void onLoad() {
        String asm = """
                A:
                    getstatic java/lang/System out Ljava/io/PrintStream;
                    ldc Hello world!
                    invokevirtual java/io/PrintStream println (Ljava/lang/String;)V
                    return
                """;
        System.out.println(asm); // чтобы компилятор точно не вырезал мою строку
        System.out.println(asm);
    }
}

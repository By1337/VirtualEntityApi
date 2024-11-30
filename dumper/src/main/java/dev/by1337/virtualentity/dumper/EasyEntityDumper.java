package dev.by1337.virtualentity.dumper;

import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.world.entity.EntityType;
import org.by1337.blib.text.MessageFormatter;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class EasyEntityDumper {

    public static void dump(File dataFolder) throws Throwable {
        Set<Class<?>> set = new HashSet<>();
        Set<Class<?>> entity = new HashSet<>();
        for (Field field : EntityType.class.getDeclaredFields()) {
            field.setAccessible(true);
            if (field.getType() != EntityType.class) continue;
            //EntityType<?> type = (EntityType<?>) field.get(null);
            Class<?> clazz = Class.forName(((ParameterizedType) field.getGenericType()).getActualTypeArguments()[0].getTypeName());
            String packageName = clazz.getCanonicalName().substring(0, clazz.getCanonicalName().lastIndexOf(".") + 1).replace(".", "/");
            File file1 = new File(dataFolder, packageName);
            file1.mkdirs();
            String className = clazz.getCanonicalName().substring(clazz.getCanonicalName().lastIndexOf(".") + 1);
            System.out.println("dump " + className);
            Files.writeString(file1.toPath().resolve("Virtual".concat(className.concat("Impl.java"))), dump(clazz, null));

            set.add(clazz);
            entity.add(clazz);

            Class<?> superClass = clazz.getSuperclass();
            do {
                if (Arrays.stream(superClass.getDeclaredFields()).anyMatch(f -> f.getType() == EntityDataAccessor.class)) {
                    set.add(superClass);
                }
                superClass = superClass.getSuperclass();
            } while (superClass != Object.class);
        }
        set.removeAll(entity);
        for (Class<?> clazz : set) {
            String packageName = clazz.getCanonicalName().substring(0, clazz.getCanonicalName().lastIndexOf(".") + 1).replace(".", "/");
            File file1 = new File(dataFolder, packageName);
            file1.mkdirs();
            System.out.println("dump " + clazz.getName());
            String className = clazz.getCanonicalName().substring(clazz.getCanonicalName().lastIndexOf(".") + 1);
            Files.writeString(file1.toPath().resolve("Virtual".concat(className.concat("Impl.java"))), dump(clazz, null));
        }
    }

    private static String dump(Class<?> clazz, @Nullable String type) throws Throwable {
        StringBuilder sb = new StringBuilder();
        String packageName = clazz.getCanonicalName().substring(0, clazz.getCanonicalName().lastIndexOf("."));
        sb.append("package dev.by1337.virtualentity.core.virtual").append(packageName.replace("net.minecraft.world.entity", "")).append(";\n\n");
        sb.append("import dev.by1337.virtualentity.api.entity.VirtualEntityType;\n");
        sb.append("import dev.by1337.virtualentity.core.mappings.Mappings;\n");
        sb.append("import dev.by1337.virtualentity.core.syncher.EntityDataAccessor;\n\n");
        String className = clazz.getCanonicalName().substring(clazz.getCanonicalName().lastIndexOf(".") + 1);
        String superclassName;
        Class<?> superClass = clazz.getSuperclass();
        do {
            if (superClass == null) {
                superClass = clazz;
                break;
            }
            if (Arrays.stream(superClass.getDeclaredFields()).anyMatch(f -> f.getType() == EntityDataAccessor.class)) {
                break;
            }
            superClass = superClass.getSuperclass();
        } while (superClass != Object.class && superClass != null);
        if (superClass == null) {
            superClass = clazz;
        }
        superclassName = superClass.getCanonicalName().substring(superClass.getCanonicalName().lastIndexOf(".") + 1);
        ;

        sb.append(MessageFormatter.apply("public class Virtual{}Impl extends Virtual{}Impl {\n", className, superclassName));

        StringBuilder staticBlock = new StringBuilder();
        staticBlock.append("    static {\n");

        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            if (field.getType() != EntityDataAccessor.class) continue;
            //parameterizedType.getActualTypeArguments()[0].getTypeName().replace("java.util.", "")
            sb.append(MessageFormatter.apply(
                    "    private static final EntityDataAccessor<{}> {};\n",
                    ((ParameterizedType) field.getGenericType()).getActualTypeArguments()[0]
                            .getTypeName()
                            .replace("java.util.", "")
                            .replace("java.lang.", "")
                    ,
                    field.getName()
            ));
            staticBlock.append(MessageFormatter.apply(
                    "        {} = Mappings.findAccessor(\"{}\", \"{}\");\n",
                    field.getName(),
                    className,
                    field.getName()
            ));
        }
        staticBlock.append("    }\n");

        sb.append("\n");
        if (type != null) {
            sb.append(MessageFormatter.apply(
                    """
                                public Virtual{}Impl() {
                                    super(VirtualEntityType.{});
                                }
                            """,
                    className,
                    type
            ));
        } else {
            sb.append(MessageFormatter.apply(
                    """
                                public Virtual{}Impl(VirtualEntityType type) {
                                    super(type);
                                }
                            """,
                    className
            ));
        }

        sb.append("\n");
        sb.append(staticBlock);
        sb.append("}\n");

        return sb.toString();
    }
}

package dev.by1337.virtualentity.api.annotations;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.SOURCE)
@Target({ElementType.FIELD, ElementType.TYPE, ElementType.METHOD})
public @interface RemovedInMinecraftVersion {
    String value();
}

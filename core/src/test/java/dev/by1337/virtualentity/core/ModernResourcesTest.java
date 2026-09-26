package dev.by1337.virtualentity.core;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class ModernResourcesTest {
    @Test
    void containsOnlyRetainedMappings() {
        ClassLoader loader = getClass().getClassLoader();
        for (int protocol = 754; protocol < 774; protocol++) {
            assertNull(loader.getResource("entity/" + protocol + ".nbt"));
            assertNull(loader.getResource("packets/" + protocol + ".json"));
        }
        for (int protocol = 774; protocol <= 776; protocol++) {
            assertNotNull(loader.getResource("entity/" + protocol + ".nbt"));
            assertNotNull(loader.getResource("packets/" + protocol + ".json"));
        }
    }
}

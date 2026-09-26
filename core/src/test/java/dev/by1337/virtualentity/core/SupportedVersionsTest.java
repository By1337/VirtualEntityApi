package dev.by1337.virtualentity.core;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SupportedVersionsTest {
    @Test
    void rejectsAllRemovedProtocols() {
        for (int protocol = 754; protocol < 774; protocol++) {
            int removed = protocol;
            assertThrows(IllegalStateException.class, () -> SupportedVersions.requireProtocol(removed));
        }
    }

    @Test
    void acceptsTheNewMinimumAndLaterProtocols() {
        for (int protocol = 774; protocol <= 777; protocol++) {
            int retained = protocol;
            assertDoesNotThrow(() -> SupportedVersions.requireProtocol(retained));
        }
    }
}

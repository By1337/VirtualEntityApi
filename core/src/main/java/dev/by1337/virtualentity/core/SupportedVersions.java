package dev.by1337.virtualentity.core;

public final class SupportedVersions {
    public static final int MIN_PROTOCOL = 774;

    private SupportedVersions() {
    }

    public static void requireProtocol(int protocol) {
        if (protocol < MIN_PROTOCOL) {
            throw new IllegalStateException("VirtualEntityApi requires Paper 1.21.11 or newer (protocol 774+), got " + protocol);
        }
    }
}

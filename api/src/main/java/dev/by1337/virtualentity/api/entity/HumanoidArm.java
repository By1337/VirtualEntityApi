package dev.by1337.virtualentity.api.entity;

public enum HumanoidArm {
    LEFT((byte) 0),
    RIGHT((byte) 1);
    private final byte id;

    HumanoidArm(byte id) {
        this.id = id;
    }

    public byte getId() {
        return id;
    }
}

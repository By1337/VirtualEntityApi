package dev.by1337.virtualentity.api.entity;

public enum SalmonVariant {
    SMALL("small"),
    MEDIUM("medium"),
    LARGE("large");
    private final String id;

    SalmonVariant(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}

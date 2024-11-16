package dev.by1337.virtualentity.core.syncher;

public record EntityDataAccessor<T>(int id, EntityDataSerializer<T> serializer) {

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o != null && this.getClass() == o.getClass()) {
            EntityDataAccessor<?> var2 = (EntityDataAccessor<?>) o;
            return this.id == var2.id;
        } else {
            return false;
        }
    }

    public int hashCode() {
        return this.id;
    }

    public String toString() {
        return "<entity data: " + this.id + ">";
    }
}

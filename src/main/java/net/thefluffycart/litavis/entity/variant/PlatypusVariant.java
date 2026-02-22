package net.thefluffycart.litavis.entity.variant;

import java.util.Arrays;
import java.util.Comparator;

public enum PlatypusVariant {
    BASE(0),
    PERRY(1);

    private static final PlatypusVariant[] BY_ID = Arrays.stream(values()).sorted(Comparator.comparingInt(
            PlatypusVariant::getId)).toArray(PlatypusVariant[]::new);
    private final int id;

    PlatypusVariant(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public static PlatypusVariant byId(int id) {
        return BY_ID[id % BY_ID.length];
    }
}

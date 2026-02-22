package net.thefluffycart.litavis.entity.variant;

import java.util.Arrays;
import java.util.Comparator;

public enum CacklewaryVariant {

    BASE(0),
    PILLAGED(1);

    private static final CacklewaryVariant[] BY_ID = Arrays.stream(values()).sorted(Comparator.comparingInt(
        CacklewaryVariant::getId)).toArray(CacklewaryVariant[]::new);
        private final int id;

    CacklewaryVariant(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public static CacklewaryVariant byId(int id) {
        return BY_ID[id % BY_ID.length];
    }
}

package com.nhnacademy.starcraft.unit.tribe;

public enum Tribe {
    TERRARN("Terran"),
    PROTOS("Protos"),
    ZERG("Zerg");

    private final String tribeName;
    Tribe(String name) {
        tribeName = name;
    }

    public String getTribeName() {
        return tribeName;
    }
}

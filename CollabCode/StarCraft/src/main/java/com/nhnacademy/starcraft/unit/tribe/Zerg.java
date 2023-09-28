package com.nhnacademy.starcraft.unit.tribe;

import com.nhnacademy.starcraft.unit.Unit;

public abstract class Zerg extends Unit {

    public Zerg(String name, int attack, int armor) {
        super(name, attack, armor, Tribe.ZERG);
    }
}

package com.nhnacademy.starcraft.unit.tribe;

import com.nhnacademy.starcraft.unit.Unit;

public class Protos extends Unit {

    public Protos(String name, int attack, int armor, boolean isFly) {
        super(name, attack, armor, Tribe.PROTOS, isFly);
    }
}

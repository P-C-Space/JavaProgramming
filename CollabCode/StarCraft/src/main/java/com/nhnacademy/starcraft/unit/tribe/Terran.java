package com.nhnacademy.starcraft.unit.tribe;

import com.nhnacademy.starcraft.unit.Unit;

public class Terran extends Unit {

    public Terran(String name, int attack, int armor, boolean isFly) {
        super(name, attack, armor, Tribe.TERRARN,isFly);
    }
}

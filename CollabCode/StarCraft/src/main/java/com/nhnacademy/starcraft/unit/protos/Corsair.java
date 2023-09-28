package com.nhnacademy.starcraft.unit.protos;

import com.nhnacademy.starcraft.ability.CanFly;
import com.nhnacademy.starcraft.unit.tribe.Protos;

public final class Corsair extends Protos implements CanFly {
    public Corsair() {
        super("Corsair", 4, 12);
    }
}

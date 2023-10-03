package com.nhnacademy.starcraft.unit.protos;

import com.nhnacademy.starcraft.ability.SpecialAttack;
import com.nhnacademy.starcraft.unit.tribe.Protos;

public final class Dragoon extends Protos implements SpecialAttack {
    public Dragoon() {
        super("Dragoon", 3, 15, false);
        canFlyAttack();
    }

    @Override
    public void canFlyAttack() {
        this.setFlyAttackAble(true);
    }
}

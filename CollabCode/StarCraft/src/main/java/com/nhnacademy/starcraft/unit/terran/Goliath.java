package com.nhnacademy.starcraft.unit.terran;

import com.nhnacademy.starcraft.ability.SpecialAttack;
import com.nhnacademy.starcraft.unit.tribe.Terran;

public final class Goliath extends Terran implements SpecialAttack {
    public Goliath() {
        super("Goliath", 5, 15, false);
        canFlyAttack();
    }

    @Override
    public void canFlyAttack() {
        this.setFlyAttackAble(true);
    }
}

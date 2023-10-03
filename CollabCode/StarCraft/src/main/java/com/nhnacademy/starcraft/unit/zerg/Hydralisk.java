package com.nhnacademy.starcraft.unit.zerg;

import com.nhnacademy.starcraft.ability.SpecialAttack;
import com.nhnacademy.starcraft.unit.tribe.Zerg;

public final class Hydralisk extends Zerg implements SpecialAttack {
    public Hydralisk() {
        super("Hydralisk", 3, 7, false);
        canFlyAttack();
    }


    @Override
    public void canFlyAttack() {
        this.setFlyAttackAble(true);
    }
}

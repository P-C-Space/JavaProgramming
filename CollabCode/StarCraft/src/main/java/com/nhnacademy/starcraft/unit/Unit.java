package com.nhnacademy.starcraft.unit;

import com.nhnacademy.starcraft.unit.tribe.Tribe;

public abstract class Unit {
    private String name;
    private int attack;
    private int armor;
    private Tribe type;

    private boolean isFly;
    private boolean flyAttackAble;

    public Unit(String name,int attack, int armor,Tribe type, boolean isFly) {
        this.name = name;
        this.attack = attack;
        this.armor = armor;
        this.type = type;
        this.isFly = isFly;

        if (this.getIsFly()) {
            this.setFlyAttackAble(true);
        } else {
            this.setFlyAttackAble(false);
        }
    }

    /*
    Setter
     */
    public void setFlyAttackAble(boolean flyAttackAble){
        this.flyAttackAble = flyAttackAble;
    }

    public void setArmor(int attack) {
        this.armor -= attack;
    }

    /*
        Getter
         */
    public String getName() {
        return name;
    }

    public int getAttack() {
        return attack;
    }

    public int getArmor() {
        return armor;
    }

    public boolean getIsFly() {
        return isFly;
    }

    public boolean getFlyAttackAble(){
        return this.flyAttackAble;
    }

}

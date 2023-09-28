package com.nhnacademy.starcraft.unit;

import com.nhnacademy.starcraft.unit.tribe.Tribe;

public abstract class Unit {
    String name;
    int attack;
    int armor;
    Tribe type;


    public Unit(String name,int attack, int armor,Tribe type) {
        this.name = name;
        this.attack = attack;
        this.armor = armor;
        this.type = type;
    }

    /*
    Setter
     */
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


}

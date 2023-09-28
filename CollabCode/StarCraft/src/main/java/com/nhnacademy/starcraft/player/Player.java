package com.nhnacademy.starcraft.player;

import com.nhnacademy.starcraft.unit.tribe.Tribe;
import com.nhnacademy.starcraft.unit.Unit;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private Tribe type;
    private List<Unit> unitList;

    public Player(Tribe type) {
        this.type = type;
        unitList = new ArrayList<>();
    }

    public void getDamageMyUnit(int index, int damage){
        unitList.get(index).setArmor(damage);
        if(unitList.get(index).getArmor() <= 0){
            unitList.remove(index);
        }
    }
    
    public Unit getUnit(int index){
        return this.unitList.get(index);
    }

    public void addUnit(Unit unit) {
        unitList.add(unit);
    }

    public Tribe getType() {
        return type;
    }
    
    public int getUnitListSize(){
        return this.unitList.size();
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        int count = 0;
        stringBuilder.append(this.getType().getTribeName() + "\n");
        for (Unit unit : this.unitList) {
            stringBuilder.append((count++) + ". " + unit.getName()
                    + " (현재 방어력: " + unit.getArmor() + ")\n");
        }
        return stringBuilder.toString();
    }
}

package com.example.project3;

public class Enemy extends Character {
    int AttackValue;
    int Defense;

    public Enemy(int atk, int def, String name){
        super(name);
        AttackValue = atk;
        Defense = def;
    }

    void setAttackValue(int atk){
        AttackValue = atk;
    }
    int getAttackValue(){
        return AttackValue;
    }
    void setDefense(int def){
        Defense = def;
    }
    int getDefense(){
        return Defense;
    }
}

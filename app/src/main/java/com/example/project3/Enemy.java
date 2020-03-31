package com.example.project3;

public class Enemy extends Character {
    int AttackValue;
    int Defense;

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

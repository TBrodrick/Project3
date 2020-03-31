package com.example.project3;

public class Character {
    private String Name;
    private int Gold;
    private int Health;
    private int Experience;


    void setName(String name){
        Name = name;
    }
    String getName(){
        return Name;
    }
    void setGold(int gold){
        Gold = gold;
    }
    int getGold(){
        return Gold;
    }
    void setHealth(int health){
        Health = health;
    }
    int getHealth(){
        return Health;
    }
    void setExperience(int xp){
        Experience = xp;
    }

    void takeDamage(int damage){
        Health -= damage;
    }
    void heal(int health){
        Health += health;
    }

    void gainGold(int gold){
        Gold += gold;
    }
    boolean payGold(int total){
        if(total < Gold){
            Gold -= total;
            return true;
        }
        else
            return false;
    }

    void gainXP(int xp){
        Experience += xp;
    }
}

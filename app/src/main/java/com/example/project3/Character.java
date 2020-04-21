package com.example.project3;

public class Character {
    private String Name;
    private int Health;
    private int Experience;
    private int atkStat;
    private int defStat;

    public Character()
    {
        Name = "Placeholder";
        Health = 10;
        Experience = 0;
        atkStat = 1;
        defStat = 0;
    }

    /**
     *
     * @param name is the name
     */
    public Character(String name){
        Name = name;
        Health = 10;
        Experience = 0;
        atkStat = 1;
        defStat = 0;
    }

    /**
     *
     * @return the defense stat
     */
    int getAtkStat()
    {
        return atkStat;
    }

    /**
     *
     * @return the defense stat
     */
    int getDefStat()
    {
        return defStat;
    }

    /**
     *
     * @param stat is the stat to set for offense
     */
    void setAtkStat(int stat)
    {
        atkStat = stat;
    }

    /**
     *
     * @param stat is the stat to set for defense
     */
    void setDefStat(int stat)
    {
        defStat = stat;
    }

    /**
     *
     * @param name is the name
     */
    void setName(String name){
        Name = name;
    }

    /**
     *
     * @return the name
     */
    String getName(){
        return Name;
    }

    /**
     *
     * @param health is the amount of health
     */
    void setHealth(int health){
        Health = health;
    }

    /**
     *
     * @return the health
     */
    int getHealth(){
        return Health;
    }

    /**
     *
     * @param damage is the damage taken
     */
    void takeDamage(int damage){
        Health -= damage;
    }

    /**
     *
     * @param health is the health healed
     */
    void heal(int health){
        Health += health;
    }

    /**
     *
     * @param xp is the experience points gained
     */
    void gainXP(int xp){
        Experience += xp;
    }

    /**
     *
     * @return the amount of experience
     */
    int getExperience()
    {
        return Experience;
    }
}

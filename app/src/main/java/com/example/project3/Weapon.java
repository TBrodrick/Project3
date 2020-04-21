package com.example.project3;

public class Weapon extends Item {

    private Item base;
    private int atkVal;
    private int classVal;

    public Weapon()
    {
        atkVal = 1;
        classVal = 0;
    }

    /**
     @param n name
     @param d description
     @param c cost
     @param r rarity
     @param av attack value
     @param cv class index
     */
    public Weapon(String n, String d, int c, int r, int et, int ev, int av, int cv)
    {
        base.setName(n);
        base.setDescription(d);
        base.setCost(c);
        base.setRarity(r);
        base.setType(et);
        base.setEval(ev);
        atkVal = av;
        classVal = cv;
    }

    /**
     *
     * @param input represents class index
     */
    public void setClassVal(int input)
    {
        classVal = input;
    }

    /**
     *
     * @param input represents attack value
     */
    public void setValue(int input)
    {
        atkVal = input;
    }

    /**
     *
     * @return attack value
     */
    public int getValue()
    {
        return atkVal;
    }

    /**
     *
     * @return class index
     */
    public int getClassVal()
    {
        return classVal;
    }
}

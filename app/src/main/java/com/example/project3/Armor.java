package com.example.project3;

public class Armor extends com.example.project3.Item {

    private Item base;
    private int defVal;
    private int classVal;

    public Armor()
    {
        defVal = 1;
        classVal = 0;
    }

    /**
     @param n name
     @param d description
     @param c cost
     @param r rarity
     @param et effect type
     @param ev effect value
     @param dv defense value
     @param cv class index
     */
    public Armor(String n, String d, int c, int r, int et, int ev, int dv, int cv)
    {
        base.setName(n);
        base.setDescription(d);
        base.setCost(c);
        base.setRarity(r);
        base.setType(et);
        base.setEval(ev);
        defVal = dv;
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
     * @param input represents defense value
     */
    public void setValue(int input)
    {
        defVal = input;
    }

    /**
     *
     * @return defense value
     */
    public int getValue()
    {
        return defVal;
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

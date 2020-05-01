package com.example.project3;

/**
 *
 * @author Ryan Johnson
 */
public class Item {

    private String name;
    private String description;
    private int cost;
    private int rarity;
    private int effType; //replacing itemFunctions
    private int effValue; //replacing itemFunctions

    public Item()
    {
        name = "Placeholder";
        description = "Placeholder description.";
        cost = 1;
        rarity = 1;
        effType = 0;
        effValue = 0;
    }

     /**
     * 
     * @param c the cost
     * @param et the effect type (0 = health, 1 = shield, 2 = attack
     * @param ev the effect value
     */
    public Item(int c, int et, int ev){
        name = "Placeholder";
        description = "Placeholder description.";
        cost = c;
        rarity = 1;
        effType = et;
        effValue = ev;
    }

    /**
     @param n name
     @param d description
     @param c cost
     @param r rarity
     @param et effect type
     @param ev effect value
     */
    public Item(String n, String d, int c, int r, int et, int ev)
    {
        name = n;
        description = d;
        cost = c;
        rarity = r;
        effType = et;
        effValue = ev;
    }

    /**
     *
     * @return string representing name
     */
    public String getName()
    {
        return name;
    }

    /**
     *
     * @return string representing description
     */
    public String getDescription()
    {
        return description;
    }


    /**
     *
     * @return int representing cost
     */
    public int getCost()
    {
        return cost;
    }


    /**
     *
     * @return int representing rarity
     */
    public int getRarity()
    {
        return rarity;
    }

    /**
     *
     * @return int representing effect type
     */
    public int getType()
    {
        return effType;
    }

    /**
     *
     * @return int representing effect value
     */
    public int getEval()
    {
        return effValue;
    }

    /**
     *
     * @param input represents input for name
     */
    public void setName(String input)
    {
        name = input;
    }

    /**
     *
     * @param input represents input for description
     */
    public void setDescription(String input)
    {
        description = input;
    }

    /**
     *
     * @param input represents input for cost
     */
    public void setCost(int input)
    {
        cost = input;
    }

    /**
     *
     * @param input represents input for rarity
     */
    public void setRarity(int input)
    {
        rarity = input;
    }

    /**
     *
     * @param input represents input for effect type
     */
    public void setType(int input) { effType = input; }

    /**
     *
     * @param input represents input for effect value
     */
    public void setEval(int input) { effValue = input; }
}

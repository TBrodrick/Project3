package com.example.project3;
import android.content.ClipData;

import java.util.ArrayList;

public class ShopFloor extends Floors{
    //variables
    private Item [] items;
    private String [] names= {"Scrap","Repair Kit","Sword.exe","Bow.exe","Staff.exe","Axe.exe","Shield.exe","Armor.exe"};
    private String [] descriptions = {"Replenishes Health", "Increase Attack", "Increase Defence"};
    private int gold;

    //default constructor
    public ShopFloor()
    {
        super(0,1);
    }

    /**
     *
     * @param n floor number
     * @param g total gold held by player
     */
    public ShopFloor(int n, int g)
    {
        super(n,1);
        gold = g;
        items = genItems(n);
    }

    /**
     *
     * @param fn floor number
     * @return array of items avaliable in shop
     */
    private Item[] genItems(int fn)
    {
        Item[] retArr = null;
        int pick;

        Item newItem = new Item();
        for(int i = 0; i<10; i++)
        {
            pick = (int)(Math.random()*3);
            if(pick==0){                    //Item that will increase Attack
                newItem = attackItem(fn);
            }
            else if(pick==1){               //Item that will increase Defence
                newItem = defenceItem(fn);
            }
            else{                           //Item that will heal
                newItem = healthItem(fn);
            }
            //assert false;
            retArr[i] =newItem;
        }
        return retArr;
    }

    /**
     *
     * @param fn floor number
     * @return item that will increase character attack
     */
    private Item attackItem(int fn)
    {
        int rand = (int)(Math.random()*4)+2;

        String name  = names[rand];

        String desc = descriptions[1];
        int cost = fn * ((int) (Math.random()*100));
        int rarity  = fn * ((int) (Math.random()*100));
        int effT = 0;
        int effV = fn * ((int) (Math.random()*20));

        return new Item(name,desc,cost,rarity,effT,effV);
    }

    /**
     *
     * @param fn floor number
     * @return item that will increase character defence
     */
    private Item defenceItem(int fn)
    {
        int rand = (int)(Math.random()*2)+6;

        String name  = names[rand];

        String desc = descriptions[2];
        int cost = fn * ((int) (Math.random()*100));
        int rarity  = fn * ((int) (Math.random()*100));
        int effT = 1;
        int effV = fn * ((int) (Math.random()*20));

        return new Item(name,desc,cost,rarity,effT,effV);
    }

    /**
     *
     * @param fn floor number
     * @return item that will replenish character health
     */
    private Item healthItem(int fn)
    {
        int rand = (int)(Math.random()*2);

        String name  = names[rand];

        String desc = descriptions[0];
        int cost = fn * ((int) (Math.random()*100));
        int rarity  = fn * ((int) (Math.random()*100));
        int effT = 0;
        int effV = fn * ((int) (Math.random()*50));

        return new Item(name,desc,cost,rarity,effT,effV);
    }

    /**
     *
     * @param i item player attempts to buy
     * @param fn floor number
     * @return tue or false based on success
     */
    boolean buyItems(Item i, int fn)
    {
        if(i.getCost()>gold)
            return false;
        else {
            gold -= i.getCost();
            return true;
        }
    }
}

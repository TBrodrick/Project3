package com.example.project3;
import android.content.ClipData;
import android.widget.Toast;

import java.util.ArrayList;

public class ShopFloor extends Floors{
    //variables
    private Item [] items = new Item[3];
    //private String [] names= {"Scrap","Repair Kit","Sword.exe","Bow.exe","Staff.exe","Axe.exe","Shield.exe","Armor.exe"};
    private String [] descriptions = {"Replenishes Health", "Increase Attack", "Increase Defence"};
    private int gold;

    //default constructor
    public ShopFloor()
    {
        super(0,1);
    }

    /**
     * @param n floor number
     * @param g total gold held by player
     */
    public ShopFloor(int n, int g)
    {
        super(n,1);
        gold = g;
        items = genItems(n);
    }

    public ShopFloor(int[] item, int g){
        gold = g;

        for(int a = 0; a < 3; a++) {
            Item it = new Item();
            int i = a*3;

            it.setCost(item[i]);
            it.setType(item[(i+1)]);
            it.setEval(item[(i+2)]);

            if(it.getType() == 0)
                it.setName("Repair Kit +"+ it.getEval());
            else if(it.getType() == 1)
                it.setName("Shield.exe +"+ it.getEval());
            else
                    it.setName("Sworrd.exe +"+ it.getEval());
            items[a] = it;
        }
    }

    /**
     *
     * @param fn floor number
     * @return array of items avalible in shop
     */
    private Item[] genItems(int fn)
    {
        if(fn == 0)
            fn=1;

        Item[] retArr = new Item[3];
        int pick;

        for(int i = 0; i<3; i++)
        {
            Item newItem = new Item();
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

    Item getItem(int index){return items[index];}

    /**
     *
     * @param fn floor number
     * @return item that will increase character attack
     */
    private Item attackItem(int fn)
    {
        int rand = (int)(Math.random()*4)+2;

        //String name  = names[rand];

        String desc = descriptions[1];

        int effT = 2;
        int effV = (int) Math.ceil(1.5* (((Math.random()*41)+80)/100)*(((Math.random()*5)+1)*(fn/((Math.random()*fn)+1))));
        int cost = effV * effT*((int) (Math.random()*10)+1)*10;
        int rarity  = effV*effT+cost;

        return new Item("Sword.exe +"+ effV,desc,cost,rarity,effT,effV);
    }

    /**
     *
     * @param fn floor number
     * @return item that will increase character defence
     */
    private Item defenceItem(int fn)
    {
        int rand = (int)(Math.random()*2)+6;

        //String name  = names[rand];

        String desc = descriptions[2];

        int effT = 1;
        int effV = (int) Math.ceil(1.5* (((Math.random()*41)+80)/100)*(((Math.random()*5)+1)*(fn/((Math.random()*fn)+1))));
        int cost = effV * effT*((int) (Math.random()*10)+1)*10;
        int rarity  = effV*effT+cost;

        return new Item("Shield.exe +"+ effV,desc,cost,rarity,effT,effV);
    }

    /**
     *
     * @param fn floor number
     * @return item that will replenish character health
     */
    private Item healthItem(int fn)
    {
        int rand = (int)(Math.random()*2);

        //String name  = names[rand];

        String desc = descriptions[0];

        int effT = 0;
        int effV = (int) Math.ceil(1.5* (((Math.random()*5)+1)*(fn/((Math.random()*fn)+1))));
        int cost = effV * ((int) (Math.random()*10)+1)*10;  //might change * to /
        int rarity  = effV+cost;

        return new Item("Repair Kit +"+ effV,desc,cost,rarity,effT,effV);
    }

    /**
     *
     * @param index index of item player attempts to buy
     * @param
     * @return tue or false based on success
     */
    boolean buyItems(int index, Character c)
    {

        if(items[index].getCost()>gold)
            return false;
        else {
            gold -= items[index].getCost();
            c.equipItem(items[index]);
            return true;
        }
    }
    
    void setGold(int g){
        gold = g;   
    }
   
    int getGold(){
        return gold;   
    }
}

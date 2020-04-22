package com.example.project3;

import java.util.ArrayList;
import java.util.Random;

public class BattleFloor extends  Floors {

    //variables
    private ArrayList<Enemy> e;          //an array of all enemies on this floor needs a generate enemy or get enemy
    private ArrayList<Ally> a;         //an array of all allies (moved from floor to floor)(get allies?)
    private int numEnemies;

    //functions
    //default constructor
    BattleFloor(){
        setFloorNum(0);
        setFloorType(0);

    }

    /**
     *
     * @param n floor number
     * @param t list of ally characters moving from floor to floor
     */
    BattleFloor(int n,ArrayList<Ally> t){//n is floor number
        super(0,n);
        generateEnemy(n);
        a = t;
    }

    /**
     *
     * @param a ally making attack
     * @param numT number of enemies targeted
     * @param i index of first enemy targeted
     */
    public void targetEnemy(Ally a,int numT,int i){
        if(numT<1)
            return;
        else if (numT==1) {
            e.get(i).takeDamage(e.get(i).getDefense()-a.getStr());  //may need to add attack function based on class
            if(e.get(i).getHealth()<=0)
                e.remove(i);
        }
        else
        {
            for(int j = i; j<numT;j++){
                e.get(j).takeDamage(e.get(j).getDefense()-a.getStr());  //may need to add attack function based on class
                if(e.get(j).getHealth()<=0)
                    e.remove(j);
            }
        }

    }

    //generates enemies based on floor level

    /**
     *
     * @param fn floor number used to determine enemy difficulty
     */
    private void generateEnemy(int fn){

        if(fn%10==0)        //boss floor
        {
            Enemy boss = new Enemy(0,0,"Boss");     //place holder we need to go over how to generate attack
            e.add(boss);
            for(int i = 1; i<3;i++){
                Enemy newEnemy = new Enemy(0,0,"Enemy");
                //randomly determine attack here
                e.add(newEnemy);
            }
        }
        else if(fn<10)      //4 weak enemies
        {
            for(int i = 0; i<4;i++){
                Enemy newEnemy = new Enemy(0,0,"Enemy");
                //randomly determine attack here
                e.add(newEnemy);
            }
        }
        else//6 medium to difficult enemies
        {
            for(int i = 0; i<6;i++){
                Enemy newEnemy = new Enemy(0,0,"Enemy");
                //randomly determine attack here based on level
                e.add(newEnemy);
            }
        }
    }
}

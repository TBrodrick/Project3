package com.example.project3;

import java.util.ArrayList;

public class Floors {
    //variables
    private int floorType;      //0 for combat, 1 for shop, 2 for rest, 3 event floors, 4 rest floors
    private int floorNum;
    private Enemy[] e = {null,null,null,null};          //an array of all enemies on this floor needs a generate enemy or get enemy
    private Ally[] a;         //an array of all allies (moved from floor to floor)(get allies?)
    private int numEnemies;

    //default constructor
    public Floors(){
        floorType = 0;
        floorNum = 0;
    }

    /**
     *
     * @param n floor number
     * @param f floor type
     */
    public Floors(int n, int f){
        floorNum =n;
        floorType = f;
    }

    /**
     *
     * @param n floor type
     * @param f floor number
     * @param a1 array of allies
     */
    public Floors(int n, int f, Ally[] a1){ //consider including ally array
        floorNum = n;
        floorType = f;
        a = a1;
    }

    //copy Constructor

    public Floors(Floors a) {
        floorNum = a.getFloorNum(); //should I use this?
        floorType = a.getFloorType();
    }

    /**
     *
     * @param f integer floor type
     */
    void setFloorType(int f) {
        floorType = f;
    }
    int getFloorType(){
        return floorType;
    }

    /**
     *
     * @param n integer floor number
     */
    void setFloorNum(int n){floorNum = n;}
    void incFloorNum(){floorNum++;}
    int getFloorNum(){return floorNum;}

}

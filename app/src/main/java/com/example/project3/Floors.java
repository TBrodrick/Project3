package com.example.project3;

public class Floors {
    //variables
    private int floorType;      //0 for combat, 1 for shop, 2 for rest, 3 for boss floors, 4 for event floors (I think)
    private int floorNum;


    //default constructor
    public Floors(){
        floorType = 0;
        floorNum = 0;
    }

    /**
     *
     * @param n floor type
     * @param f floor number
     */
    public Floors(int n, int f){
        floorNum = n;
        floorType = f;
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

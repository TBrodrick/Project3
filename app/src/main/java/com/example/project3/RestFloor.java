package com.example.project3;

public class RestFloor extends Floors {

    //default Constructor
    RestFloor(){
        super(0,4);
    }

    //Constructor

    /**
     *
     * @param n floor number
     * @param a array of allies
     */
    RestFloor(int n, Ally[] a)
    {
        super(n,4,a);
    }

    /**
     *
     * @param a an array of allies
     */
    void rest(Ally[] a)
    {
        for(int i = 0; i< 4; i++){
            a[i].setHealth(a[i].getMaxHealth());      //change to set health max
        }
    }
}

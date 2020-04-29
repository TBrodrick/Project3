package com.example.project3;

public class RestFloor extends Floors {

    private Character[] a = new Character[4];
    //default Constructor
    RestFloor(){
        super(0,4);
    }

    //Constructor

    /**
     *
     * @param allies array of allies
     */
    RestFloor(Character[] allies)
    {
        a = allies;
    }

    /**
     *
     * Heals the party
     */
    void rest()
    {
        for(int i = 0; i< 4; i++){
            a[i].setHealth(a[i].getMaxHealth());      //change to set health max
        }
    }
}

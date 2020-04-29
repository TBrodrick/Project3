package com.example.project3;
import java.util.Random;
import java.math.*;
public class EventFloor extends Floors {
    //variables
    int numMines;
    //functions
    //default constructor
    public EventFloor()
    {
        super(0,3);
        numMines = (int)(Math.random()*3)+1;
    }
    public EventFloor(int num){
        super(0,3);
        numMines = num;
    }

    public int getNumMines(){
        return numMines;
    }
    public int mine() {
        if(numMines > 0)
            return (int)(Math.random() * 50) + 50;
        numMines--;
        return 0;
    }
}

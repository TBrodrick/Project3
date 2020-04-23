package com.example.project3;
import java.util.Random;
import java.math.*;
public class EventFloor extends Floors {
    //variables

    //functions
    //default constructor
    EventFloor()
    {
        super(0,3);
    }

    //constructor
    EventFloor(int n, Ally[] a)
    {
        super(n,3,a);
    }

    //alternative constructor
    EventFloor(int n)
    {
        super(n,3);
    }

    int mine() {
        return (int)(Math.random() * 50) + 50;
    }
}

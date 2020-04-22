package com.example.project3;
import android.content.ClipData;

import java.util.ArrayList;

public class ShopFloor extends Floors{
    //variables
    private ArrayList<Item> items;
    private int gold;

    //default constructor
    public ShopFloor()
    {
        super(0,1);
    }

    public ShopFloor(int n, int g)
    {
        super(n,1);
        gold = g;
        items = genItems(n);
    }

    private ArrayList<Item> genItems(int fn)
    {
        ArrayList<Item> retArr = null;
        return retArr;
    }

    boolean buyItems(Item i)
    {
        if(i.getCost()>gold)
            return false;
        else
        {

        }
        return true;
    }
}

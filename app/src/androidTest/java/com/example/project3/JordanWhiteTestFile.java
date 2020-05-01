package com.example.project3;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Testing Floors item and Enemy generation
 */
@RunWith(AndroidJUnit4.class)
public class JordanWhiteTestFile{
    @Test
    public void testItemGen() {
        Item [] a = new Item [3];
        ShopFloor b = new ShopFloor(10,1000);
        a[0] = b.getItem(0);
        a[1] = b.getItem(1);
        a[2] = b.getItem(2);

        assertNotEquals(a[0].getEval(),a[1].getEval());
        assertNotEquals(a[1].getEval(),a[2].getEval());
        assertNotEquals(a[2].getEval(),a[0].getEval());
    }

    //tests enemy random generation functions in character class
    @Test
    public void testEnemyStatGen()
    {
        Enemy test = new Enemy(0,0,"testing123");
        Enemy test1 = new Enemy(0,0,"testing123");
        test.setBase(10);
        test.addVar();
        test1.setBase(10);
        test1.addVar();
        assertNotEquals(test1.getAttackValue(),test.getAttackValue());
        assertNotEquals(test1.getDefStat(),test.getDefStat());
        assertNotEquals(test1.getMaxHealth(),test.getMaxHealth());
    }

    @Test
    public void goldSpendTest(){
        ShopFloor a =new ShopFloor(10,10000);
        Character b = new Character();
        int test = a.getItem(0).getCost();
        a.buyItems(0,b);

        assertEquals(a.getGold(),10000-test);
    }
}

package com.example.project3;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Testing of the Character and Item classes, implemented by Ryan Johnson.
 */
@RunWith(AndroidJUnit4.class)
public class RyanJohnsonTestFile {

    Character ally = new Character("TestDummy", 20, 10, 10);

    //Test attack functionality.
    @Test
    public void characterGetsHit() {
        Character enemy = new Character();
        ally.setHealth(3);
        ally.setDefStat(1);
        enemy.setAtkStat(2);
        ally.takeDamage(enemy.getAtkStat() - ally.getDefStat());

        assertEquals(2, ally.getHealth());
    }

    //Test item type 0 functionality.
    @Test
    public void testItem0Equip()
    {
        ally.setMaxHealth(20);
        ally.setHealth(10);

        //Item type 0 is the health, to increase max health by 5 and bring current health to full.
        Item item0 = new Item();
        item0.setType(0);
        item0.setEval(5);
        ally.equipItem(item0);

        assertEquals(ally.getHealth(), 25);
        assertEquals(ally.getMaxHealth(), 25);
    }

    //Test item type 1 functionality.
    @Test
    public void testItem1Equip()
    {
        ally.setDefStat(10);

        //Item type 1 is the shield, to increase def stat by value of 10.
        Item item1 = new Item();
        item1.setType(1);
        item1.setEval(10);
        ally.equipItem(item1);

        assertEquals(ally.getDefStat(), 20);
    }

    //Test item type 2 functionality.
    @Test
    public void testItem2Equip()
    {
        Character ally = new Character();
        ally.setAtkStat(10);

        //Item type 2 is the weapon, to increase atk stat by value of 15.
        Item item2 = new Item();
        item2.setType(2);
        item2.setEval(15);
        ally.equipItem(item2);

        assertEquals(ally.getAtkStat(), 25);
    }
}

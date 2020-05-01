package com.example.project3;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {

    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();

        assertEquals("com.example.project3", appContext.getPackageName());
    }

    /**
     * Tests Moving floors
     */
    @Test
    public void moveFloorTest(){
        ArrayList<int[]> FloorList = new ArrayList<int[]>();
        int[] t = {1,2,3,4};
        int[] t2 = {4, 3, 2, 1};
        FloorList.add(t);
        FloorList.add(t2);
        boolean up = true;
        int currentFloor = 1;
        int arrayPos = 0;
        int floodValue = 1;
        int floodHeight = 0;
        if(up){
            currentFloor++;
            arrayPos++;
            if(arrayPos >= FloorList.size()) {
                assertArrayEquals(t2, FloorList.get(arrayPos));
            }
            else{
                assertEquals(1, arrayPos);
            }
        }
        else {
            if (currentFloor != 0) {
                arrayPos--;
                currentFloor--;
                if (currentFloor <= floodHeight || arrayPos == -1) {
                    assertArrayEquals(t, FloorList.get(arrayPos));
                }
                else {
                    assertEquals(-1, arrayPos);
                }
            }
        }
        if(floodValue % 10 > 2){
            floodHeight++;
        }
        floodValue++;
    }

    /**
     * Tests Battle Floor saves
     */
    @Test
    public void testBattleFloorSaves(){
        ArrayList<int[]> FloorList = new ArrayList<int[]>();
        int[] t = {1,2,3,4};
        int[] t2 = {4, 3, 2, 1};
        FloorList.add(t);
        FloorList.add(t2);
        int arrayPos = 2;
        BattleFloor floor = new BattleFloor();
        int[] enemies = new int[13];
        for(int a = 0; a < 4; a++) {
            for (int i = 0; i < 12; i += 3) {
                if(floor.getEnemy(a).getHealth() <= 0)
                    enemies[i] = 0;
                else
                    enemies[i] = floor.getEnemy(a).getMaxHealth();

                enemies[i + 1] = floor.getEnemy(a).getAtkStat();
                enemies[i + 2] = floor.getEnemy(a).getDefStat();
            }
        }
        enemies[enemies.length - 1] = 0;

        if(arrayPos >= FloorList.size())
            FloorList.add(enemies);
        else
            FloorList.set(arrayPos, enemies);

        assertEquals(enemies, FloorList.get(2));
    }

    /**
     * Tests saving the shop floor
     */
    @Test
    public void testShopSaves(){
        ArrayList<int[]> FloorList = new ArrayList<int[]>();
        int[] t = {1,2,3,4};
        int[] t2 = {4, 3, 2, 1};
        FloorList.add(t);
        FloorList.add(t2);
        int arrayPos = 2;
        ShopFloor shop = new ShopFloor();
        int[] items = new int[13];

        for(int a = 0; a < 3; a++) {
            int i = a*3;
            items[i] = shop.getItem(a).getCost();
            items[(i+1)] = shop.getItem(a).getType();
            items[(i+2)] = shop.getItem(a).getEval();
        }

        items[items.length - 1] = 1;
        if(arrayPos == FloorList.size() || FloorList.size() == 0)
            FloorList.add(items);
        else
            FloorList.set(arrayPos, items);

        assertEquals(items, FloorList.get(2));
    }
}

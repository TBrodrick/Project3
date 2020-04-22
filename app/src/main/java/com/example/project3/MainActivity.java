package com.example.project3;

import androidx.appcompat.app.AppCompatActivity;
import java.math.*;
import java.util.ArrayList;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    private Ally Allies[];
    int gold;
    private int floodValue ;
    private ArrayList<int[]> FloorList;
    private int arrayPos;
    int floodHeight;
    int floorNumber;
    int currentFloor;

    private int currentScore;
    private int highScore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * Generates the next floor.
     * Every 10 floors is a boss, every 6th is a rest (unless it's a boss), for every other floor 6/9 chance of battle, 2/9 chance of shop, 1/9 chance of event
     * Then adds floor data to database
     */
    private void genFloor(){
        int select = (int)Math.random()*9;
        floorNumber++;
        arrayPos++;
        if(floorNumber % 10 == 0){
            //generate boss floor
        }
        else if(floorNumber % 6 == 0){
            //generates rest floor
        }
        else{
            if(floorNumber == 9){
                //generate rest floor
            }
            if(select <= 6){
                //generate Battle
            }
            else if(select <= 8){
                //generate shop
            }
            else{
                //generate event
            }
        }
    }

    /**
     * Moves the player up or down floors
     * @param up true if moving upwards, false if going down
     */
    private void moveFloor(boolean up){
        if(up){
            if(arrayPos == FloorList.size()) {
                genFloor();
                currentFloor++;
            }
            arrayPos++;
        }
        else{
            if(currentFloor == floodHeight || arrayPos == 0)
                gameOver();
            else{
                arrayPos--;
                //gen previous floor
            }
        }
        if(floodValue % 10 > 2){
            FloorList.remove(0);
        }
    }

    /**
     * Gives you average party level
     * @param level of party
     * @return a new generated Enemy
     */
    private Enemy genEnemy(int level){
        int atk = (int)(Math.random()*3)*getLevel();
        int def = (int)(Math.random()*3)*getLevel();
        String name = "";
        int select = (int)Math.random()*4;

        switch (select){
            case 1:
                name = "Skeleton";
                break;
            case 2:
                name = "Zombie";
                break;
            case 3:
                name = "Beast";
                break;
            case 4:
                name = "Dave";
                break;
        }
        Enemy enemy = new Enemy(atk, def, name);
        return enemy;
    }

    private int getLevel(){
        int total = 0;
        for(int i = 0; i < 4; i++){
            total += Allies[i].getLevel();
        }
        total /= 4;
        return total;
    }

    private void gameOver(){

    }
}

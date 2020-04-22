package com.example.project3;

import androidx.appcompat.app.AppCompatActivity;
import java.math.*;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    Ally Allies[] = new Ally[4];
    int gold;
    int floorsSinceRest;
    int lastFloor;
    int floorNumber;
    int currentFloorType = 0;
    Floors CurrentFloor = new Floors(floorNumber, currentFloorType,Allies);

    private void genFloor(){
        lastFloor = currentFloorType;
        if(lastFloor != 2)
            floorsSinceRest++;
        if(floorsSinceRest > 4)
            currentFloorType = 1;
        else{
            do {
                currentFloorType = (int) (Math.random() * 4);
            }while(currentFloorType == lastFloor);
        }
    }

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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}

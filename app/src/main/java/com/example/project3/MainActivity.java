package com.example.project3;

import androidx.appcompat.app.AppCompatActivity;
import java.math.*;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    Ally Allies[] = new Ally[4];
    int gold;
    int floorsSinceRest;
    int lastFloor;
    int floor;

    private void genFloor(){
        lastFloor = floor;
        if(floor != 1)
            floorsSinceRest++;
        if(floorsSinceRest > 4)
            floor = 1;
        else{
            floor = (int)(Math.random() * 4);
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

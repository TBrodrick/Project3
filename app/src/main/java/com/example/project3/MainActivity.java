package com.example.project3;

import androidx.appcompat.app.AppCompatActivity;
import java.math.*;
import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.util.Log;
import android.widget.Toast;

import com.example.project3.R;


public class MainActivity extends AppCompatActivity {
    private Character Allies[] = new Character[4];
    int gold;
    private int floodValue ;
    private ArrayList<int[]> FloorList;
    private int arrayPos;
    int floodHeight;
    int floorNumber;
    int currentFloor;

    private int currentScore;
    private int highScore;


    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    public void init()
    {
        Button newRunButton = (Button) findViewById(R.id.newRun);
        newRunButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startNewGame();
            }
        });
    }

    public void startNewGame()
    {
        Intent intent = new Intent(this, CreateNewGame.class);
        startActivity(intent);
    }




    /**
     * Moves the player up or down floors
     * @param up true if moving upwards, false if going down
     */
    private void moveFloor(boolean up){
        if(up){
            if(arrayPos == FloorList.size()) {
                //genFloor();
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
        int atk = (int)(Math.random()*3)*(floorNumber/10);
        int def = (int)(Math.random()*3)*(floorNumber/10);
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

    /*private int getLevel(){
        int total = 0;
        for(int i = 0; i < 4; i++){
            total += Allies[i].getLevel();
        }
        total /= 4;
        return total;
    }*/

    private void gameOver(){

    }
}

package com.example.project3;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project3.R;

import java.util.ArrayList;

public class CreateNewGame extends AppCompatActivity {
    private Character Allies[] = new Character[4];
    int gold;
    private int floodValue;
    private ArrayList<int[]> FloorList;
    private int arrayPos;
    int floodHeight;
    int floorNumber = 0;
    int currentFloor = 0;

    private int currentScore;
    private int highScore;

    int loop = 0;
    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Allies[0] = new Character("Tim");
        Allies[1] = new Character("Bob");
        Allies[2] = new Character("Henry");
        Allies[3] = new Character("Dave");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_game);
        final Button Victory = (Button)findViewById(R.id.Victory);
        Victory.setVisibility(View.INVISIBLE);
        final Button start = (Button) findViewById(R.id.Start);
        final Button attack = (Button) findViewById(R.id.Attack);
        //final Button abilities = (Button) findViewById(R.id.Abilities);
        //final Button items = (Button) findViewById(R.id.Items);
        final Button flee = (Button) findViewById(R.id.Flee);
        final Button shop = (Button) findViewById(R.id.ShopTest);
        final Button event = (Button) findViewById(R.id.EventTest);
        final TextView intro = (TextView) findViewById(R.id.Introduction);
        final TextView PlayerNum = (TextView) findViewById(R.id.PlayerNum);
        //final TextView stat1 = (TextView) findViewById(R.id.Attack1);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startBattle(start, intro, attack, flee, PlayerNum);
            }
        });
        final String[] players = {"Player 1", "Player 2", "Player 3", "Player 4"};
        attack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PlayerNum.setText(players[loop]);
                loop++;
                if(loop == 4)
                {
                    loop = 0;
                }
            }
        });
        shop.setOnClickListener(new View.OnClickListener() {    //The below two methods will be altered drastically when the code is merged
            @Override
            public void onClick(View v) {
                openShop();
            }
        });
        event.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newEvent();
            }
        });

    }


    public void startBattle(Button start, TextView intro, final Button attack, final Button flee, TextView PlayerNum)
    {
        final Button Victory = (Button)findViewById(R.id.Victory);
        Victory.setVisibility(View.INVISIBLE);
        //Defines the battle taking place
        currentFloor = 1;
        final BattleFloor Current = new BattleFloor(currentFloor, Allies);

        for(int i = 0; i < 4; i++)
            Current.getEnemy(i).setDefStat(0);
        //Ally Health set to appropriate text views
        final TextView p1Health = (TextView)findViewById(R.id.Ally1HP);
        final TextView p2Health = (TextView)findViewById(R.id.Ally2HP);
        final TextView p3Health = (TextView)findViewById(R.id.Ally3HP);
        final TextView p4Health = (TextView)findViewById(R.id.Ally4HP);

        //Ally attack and defense set to appropriate text views
        final TextView p1Stats = (TextView)findViewById(R.id.AllyAttack1);
        final TextView p2Stats = (TextView)findViewById(R.id.AllyAttack2);
        final TextView p3Stats = (TextView)findViewById(R.id.AllyAttack3);
        final TextView p4Stats = (TextView)findViewById(R.id.AllyAttack4);

        //Display Allied Stats at battle start
        p1Health.setText(Allies[0].getMaxHealth() + " / " + Allies[0].getHealth());
        p2Health.setText(Allies[1].getMaxHealth() + " / " + Allies[1].getHealth());
        p3Health.setText(Allies[2].getMaxHealth() + " / " + Allies[2].getHealth());
        p4Health.setText(Allies[3].getMaxHealth() + " / " + Allies[3].getHealth());
        p1Stats.setText(Allies[0].getAtkStat() + " / " + Allies[0].getDefStat());
        p2Stats.setText(Allies[1].getAtkStat() + " / " + Allies[1].getDefStat());
        p3Stats.setText(Allies[2].getAtkStat() + " / " + Allies[2].getDefStat());
        p4Stats.setText(Allies[3].getAtkStat() + " / " + Allies[3].getDefStat());

        //Enemy Health set to appropriate text views
        final TextView E1Health = (TextView)findViewById(R.id.Enemy1Health);
        final TextView E2Health = (TextView)findViewById(R.id.Enemy2Health);
        final TextView E3Health = (TextView)findViewById(R.id.Enemy3Health);
        final TextView E4Health = (TextView)findViewById(R.id.Enemy4Health);

        //Enemy attack and defense set to appropriate text views
        final TextView E1Stats = (TextView)findViewById(R.id.Enemy1Stats);
        final TextView E2Stats = (TextView)findViewById(R.id.Enemy2Stats);
        final TextView E3Stats = (TextView)findViewById(R.id.Enemy3Stats);
        final TextView E4Stats = (TextView)findViewById(R.id.Enemy4Stats);

        //Display Enemy Health at start of combat
        E1Health.setText(Current.getEnemy(0).getHealth() + " / " + Current.getEnemy(0).getMaxHealth());
        E2Health.setText(Current.getEnemy(1).getHealth() + " / " + Current.getEnemy(1).getMaxHealth());
        E3Health.setText(Current.getEnemy(2).getHealth() + " / " + Current.getEnemy(2).getMaxHealth());
        E4Health.setText(Current.getEnemy(3).getHealth() + " / " + Current.getEnemy(3).getMaxHealth());

        //Display Enemy stats on combat start
        E1Stats.setText(Current.getEnemy(0).getAtkStat() + " / " + Current.getEnemy(0).getDefStat());
        E2Stats.setText(Current.getEnemy(1).getAtkStat() + " / " + Current.getEnemy(1).getDefStat());
        E3Stats.setText(Current.getEnemy(2).getAtkStat() + " / " + Current.getEnemy(2).getDefStat());
        E4Stats.setText(Current.getEnemy(3).getAtkStat() + " / " + Current.getEnemy(3).getDefStat());

        start.setVisibility(View.INVISIBLE);
        intro.setVisibility(View.INVISIBLE);
        attack.setVisibility(View.VISIBLE);
        //abilities.setVisibility(View.VISIBLE);
        //items.setVisibility(View.VISIBLE);
        flee.setVisibility(View.VISIBLE);
        PlayerNum.setVisibility(View.VISIBLE);
        attack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Current.attackRound();

                //Display Ally Health after attack round
                p1Health.setText(Allies[0].getHealth() + " / " + Allies[0].getMaxHealth());
                p2Health.setText(Allies[1].getHealth() + " / " + Allies[1].getMaxHealth());
                p3Health.setText(Allies[2].getHealth() + " / " + Allies[2].getMaxHealth());
                p4Health.setText(Allies[3].getHealth() + " / " + Allies[3].getMaxHealth());

                //Display Enemy Health after attack round
                E1Health.setText(Current.getEnemy(0).getHealth() + " / " + Current.getEnemy(0).getMaxHealth());
                E2Health.setText(Current.getEnemy(1).getHealth() + " / " + Current.getEnemy(1).getMaxHealth());
                E3Health.setText(Current.getEnemy(2).getHealth() + " / " + Current.getEnemy(2).getMaxHealth());
                E4Health.setText(Current.getEnemy(3).getHealth() + " / " + Current.getEnemy(3).getMaxHealth());

                if(Allies[0].getHealth() <= 0 && Allies[1].getHealth() <= 0 && Allies[2].getHealth() <= 0 && Allies[3].getHealth() <= 0){
                    //game over
                }
                else if(Current.getEnemy(0).getHealth() <= 0 && Current.getEnemy(1).getHealth() <= 0 && Current.getEnemy(2).getHealth() <= 0 && Current.getEnemy(3).getHealth() <= 0){
                    attack.setVisibility(View.INVISIBLE);
                    flee.setVisibility(View.INVISIBLE);
                    Victory.setVisibility(View.VISIBLE);
                }

            }
        });
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

        if(floorNumber % 6 == 0){
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

    public void openShop()
    {
        ShopFloor Current = new ShopFloor();
        Intent intent = new Intent(this, com.example.project3.shop.class);
        startActivity(intent);
    }

    public void newEvent()
    {
        Intent intent = new Intent(this, com.example.project3.SpecialEvent.class);
        startActivity(intent);
    }


}



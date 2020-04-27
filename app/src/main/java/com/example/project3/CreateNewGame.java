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
    int gold = 10000;
    private int floodValue;
    private ArrayList<int[]> FloorList = new ArrayList<int[]>();
    private int arrayPos = 0;
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
        setContentView(R.layout.start_page);
        final Button Skip = (Button) findViewById(R.id.Skip);
        final Button Next = (Button) findViewById(R.id.Next);
        final TextView intro = (TextView) findViewById(R.id.Introduction);
        Skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openShop(new ShopFloor(currentFloor, gold));
            }
        });

    }


    public void startBattle(final BattleFloor Current)
    {
        setContentView(R.layout.activity_create_new_game);
        final Button MoveOn = (Button)findViewById(R.id.Victory);
        final Button GoBack = (Button)findViewById(R.id.GoBack);
        final Button flee = (Button) findViewById(R.id.Flee);
        final Button attack = (Button) findViewById(R.id.Attack);
        final Button shop = (Button) findViewById(R.id.ShopTest);
        final Button event = (Button) findViewById(R.id.EventTest);
        shop.setOnClickListener(new View.OnClickListener() {    //The below two methods will be altered drastically when the code is merged
            @Override
            public void onClick(View v) {
                openShop(new ShopFloor(floorNumber, gold));
            }
        });
        event.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newEvent();
            }
        });
        //Defines the battle taking place
        SaveBattleFloor(Current);

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
        p1Health.setText(Allies[0].getHealth() + " / " + Allies[0].getMaxHealth());
        p2Health.setText(Allies[1].getHealth() + " / " + Allies[1].getMaxHealth());
        p3Health.setText(Allies[2].getHealth() + " / " + Allies[2].getMaxHealth());
        p4Health.setText(Allies[3].getHealth() + " / " + Allies[3].getMaxHealth());
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

        //Display Enemy stats on combat start
        E1Stats.setText(Current.getEnemy(0).getAtkStat() + " / " + Current.getEnemy(0).getDefStat());
        E2Stats.setText(Current.getEnemy(1).getAtkStat() + " / " + Current.getEnemy(1).getDefStat());
        E3Stats.setText(Current.getEnemy(2).getAtkStat() + " / " + Current.getEnemy(2).getDefStat());
        E4Stats.setText(Current.getEnemy(3).getAtkStat() + " / " + Current.getEnemy(3).getDefStat());

        //Display Enemy Health at start of combat
        E1Health.setText(Current.getEnemy(0).getHealth() + " / " + Current.getEnemy(0).getMaxHealth());
        E2Health.setText(Current.getEnemy(1).getHealth() + " / " + Current.getEnemy(1).getMaxHealth());
        E3Health.setText(Current.getEnemy(2).getHealth() + " / " + Current.getEnemy(2).getMaxHealth());
        E4Health.setText(Current.getEnemy(3).getHealth() + " / " + Current.getEnemy(3).getMaxHealth());

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
                    MoveOn.setVisibility(View.VISIBLE);
                    GoBack.setVisibility(View.VISIBLE);
                    MoveOn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            moveFloor(true);
                        }
                    });
                    GoBack.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            moveFloor(false);
                        }
                    });
                }

            }
        });
        final Toast toast = Toast.makeText(getApplicationContext(), "Press", Toast.LENGTH_SHORT);
        flee.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //toast.show();
                moveFloor(false);
            }
        });
    }

    public void openShop(final ShopFloor Current)
    {
        final ImageView[] Images = new ImageView[3];
        final Button[] Buttons = new Button[3];
        final TextView[] Text = new TextView[6];

        SaveShopFloor(Current);

        setContentView(R.layout.activity_shop);
        //Intent intent = new Intent(this, com.example.project3.shop.class);
        //startActivity(intent);

        //Non Item Related TextViews
        final TextView Gold = (TextView)findViewById(R.id.gold);
        final TextView BackBox = (TextView)findViewById(R.id.Transparency);
        Gold.setText("Gold: " + gold);

        //Setup Items
        final ImageView Item1Pic = (ImageView)findViewById(R.id.Item1Pic);
        final ImageView Item2Pic = (ImageView)findViewById(R.id.Item2Pic);
        final ImageView Item3Pic = (ImageView)findViewById(R.id.Item3Pic);
        Images[0] = Item1Pic;
        Images[1] = Item2Pic;
        Images[2] = Item3Pic;

        final Button MoveOn = (Button)findViewById(R.id.MoveOn);
        final Button GoBack = (Button)findViewById(R.id.GoBack);

        final Button Buy1 = (Button)findViewById(R.id.Buy1);
        final Button Buy2 = (Button)findViewById(R.id.Buy2);
        final Button Buy3 = (Button)findViewById(R.id.Buy3);

        final TextView Item1 = (TextView)findViewById(R.id.Item1);
        final TextView Item2 = (TextView)findViewById(R.id.Item2);
        final TextView Item3 = (TextView)findViewById(R.id.Item3);

        final TextView Price1 = (TextView)findViewById(R.id.Price1);
        final TextView Price2 = (TextView)findViewById(R.id.Price2);
        final TextView Price3 = (TextView)findViewById(R.id.Price3);

        Item1.setText(Current.getItem(0).getName());
        Item2.setText(Current.getItem(1).getName());
        Item3.setText(Current.getItem(2).getName());

        Text[0] = Item1;
        Text[1] = Item2;
        Text[2] = Item3;
        Text[3] = Price1;
        Text[4] = Price2;
        Text[5] = Price3;

        Price1.setText("Cost: " + Current.getItem(0).getCost());
        Price2.setText("Cost: " + Current.getItem(1).getCost());
        Price3.setText("Cost: " + Current.getItem(2).getCost());

        for(int i = 0; i < 3; i++){
            if(Text[i].getText().equals("Sword.exe")){
                Images[i].setImageResource(R.drawable.weapon);
            }
            else if(Text[i].getText().equals("Axe.exe")){
                Images[i].setImageResource(R.drawable.axe);
            }
            else if(Text[i].getText().equals("Staff.exe")){
                Images[i].setImageResource(R.drawable.staff);
            }
            else if(Text[i].getText().equals("Bow.exe")){
                Images[i].setImageResource(R.drawable.bow);
            }
            else if(Text[i].getText().equals("Scrap")){
                Images[i].setImageResource(R.drawable.scrap);
            }
            else if(Text[i].getText().equals("Armor.exe")){
                Images[i].setImageResource(R.drawable.armor);
            }
            else if(Text[i].getText().equals("Shield.exe")){
                Images[i].setImageResource(R.drawable.shield);
            }
            else if(Text[i].getText().equals("Repair Kit")){
                Images[i].setImageResource(R.drawable.health);
            }
        }

        Buy1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Current.buyItems(0, currentFloor);
                Item1Pic.setVisibility(View.INVISIBLE);
                Buy1.setVisibility(View.INVISIBLE);
                Item1.setVisibility(View.INVISIBLE);
                Price1.setVisibility(View.INVISIBLE);
            }
        });
        Buy2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Current.buyItems(1, currentFloor);
                Item2Pic.setVisibility(View.INVISIBLE);
                Buy2.setVisibility(View.INVISIBLE);
                Item2.setVisibility(View.INVISIBLE);
                Price2.setVisibility(View.INVISIBLE);
            }
        });
        Price1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Current.buyItems(2, currentFloor);
                Item3Pic.setVisibility(View.INVISIBLE);
                Buy3.setVisibility(View.INVISIBLE);
                Item3.setVisibility(View.INVISIBLE);
                Price3.setVisibility(View.INVISIBLE);
            }
        });
        MoveOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SaveShopFloor(Current);
                moveFloor(true);
            }
        });
        GoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SaveShopFloor(Current);
                moveFloor(false);
            }
        });
    }

    public void newEvent()
    {
        setContentView(R.layout.activity_special_event);
    }

    public void restFloor()
    {
        setContentView(R.layout.start_page);
    }

    private void gameOver(){
        Toast toast = Toast.makeText(getApplicationContext(), "Game Over", Toast.LENGTH_SHORT);
        toast.show();
    }

    /**
     * Moves the player up or down floors
     * @param up true if moving upwards, false if going down
     */
    private void moveFloor(boolean up){
        if(up){
            arrayPos++;
            if(arrayPos == FloorList.size() ) {
                genFloor();
                currentFloor++;
            }
            else{
                currentFloor++;
                if(FloorList.get(arrayPos)[FloorList.get(arrayPos).length - 1] == 0)
                    startBattle(new BattleFloor(Allies, FloorList.get(arrayPos)));
                else if(FloorList.get(arrayPos)[FloorList.get(arrayPos).length - 1] == 1)
                    openShop(new ShopFloor(FloorList.get(arrayPos), gold));
            }
        }
        else {
            if (currentFloor != 0) {
                if (currentFloor == floodHeight || arrayPos == 0)
                    gameOver();

                else {
                    arrayPos--;
                    currentFloor--;
                    if (FloorList.get(arrayPos)[FloorList.get(arrayPos).length - 1] == 0)
                        startBattle(new BattleFloor(Allies, FloorList.get(arrayPos)));
                    else if (FloorList.get(arrayPos)[FloorList.get(arrayPos).length - 1] == 1)
                        openShop(new ShopFloor(FloorList.get(arrayPos), gold));
                }
            }
        }
        if(floodValue % 10 > 2){
            FloorList.remove(0);
        }
    }

    /**
     * Adds the floor to FloorList if it's a new floor or sets the location at FloorList to the correct variabels
     * @param floor The battle floor being saved
     */
    private void SaveBattleFloor(BattleFloor floor){
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

        if(arrayPos == FloorList.size())
            FloorList.add(enemies);
        else
            FloorList.set(arrayPos, enemies);
    }

    private void SaveShopFloor(ShopFloor shop){
        int[] items = new int[10];

        for(int a = 0; a < 3; a++) {
            for (int i = a*3; i < (a*3)+2; i++) {
                items[i] = shop.getItem(a).getCost();
                items[i+1] = shop.getItem(a).getType();
                items[i+2] = shop.getItem(a).getEval();
            }
        }
        items[items.length - 1] = 1;
        if(arrayPos == FloorList.size() || FloorList.size() == 0)
            FloorList.add(items);
        else
            FloorList.set(arrayPos, items);
    }
    /**
     * Generates the next floor.
     * Every 10 floors is a boss, every 6th is a rest (unless it's a boss), for every other floor 6/9 chance of battle, 2/9 chance of shop, 1/9 chance of event
     * Then adds floor data to database
     */
    private void genFloor(){
        int select = (int)(Math.random()*9);
        Toast toast = Toast.makeText(getApplicationContext(), "" + select, Toast.LENGTH_SHORT);
        toast.show();
        floorNumber++;

        if(floorNumber % 6 == 0){
            //generates rest floor
        }
        else{
            if(floorNumber == 9){
                //generate rest floor
            }
            if(select <= 5){
                startBattle(new BattleFloor(currentFloor, Allies));
            }
            else if(select <= 8){
                openShop(new ShopFloor(floorNumber, gold));
            }
            else{
                newEvent();
            }
        }
    }
}



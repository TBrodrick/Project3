package com.example.project3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project3.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CreateNewGame extends AppCompatActivity {
    //private static final String conString = "jdbc:oracle:thin:mcs1009/cMEY1Myo@adcsdb01.csueastbay.edu:1521/mcspdb.ad.csueastbay.edu";
    private Character Allies[] = new Character[4];
    int gold = 1000;
    private ArrayList<int[]> FloorList = new ArrayList<int[]>();
    private int arrayPos = 0;
    int floodHeight = -1;
    int floodValue = 0;
    int currentFloor = 0;
    String userName;
    Bundle instanceState;
    private DatabaseReference mDatabase;
    private GameData gameData;

    private long currentScore = 0;
    private long HighScore = 0;

    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Allies[0] = new Character("Tim");
        Allies[1] = new Character("Bob");
        Allies[2] = new Character("Henry");
        Allies[3] = new Character("Dave");

        mDatabase = FirebaseDatabase.getInstance().getReference();

        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.child(userName).exists())
                    HighScore = (long)dataSnapshot.child(userName).getValue();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



        super.onCreate(savedInstanceState);
        instanceState = savedInstanceState;
        Intent intent = getIntent();
        userName = intent.getStringExtra("UserName");
        setContentView(R.layout.start_page);
        final Button Skip = (Button) findViewById(R.id.Skip);
        final TextView intro = (TextView) findViewById(R.id.Introduction);
        intro.setText("You are a group of robots escaping a world that is flooding." +
                "You find a tower full of fish who are embracing the new world order." +
                "They will attempt to fight or help you on your way up the tower.\n Buy items in shops, rest when you can, and survive the ambushes on your way to safety");
        Skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openShop(new ShopFloor(arrayPos, gold));
            }
        });
    }

    /**
     * Saves high score to the database attached to the username you entered
     */
    public void saveHighScore(){
        mDatabase.child(userName).setValue(currentScore);
    }

    /**
     * Sets GUI and runs functionality for the Battle floor
     * @param Current The battle floor being loaded either a new one or one passed from Floors List
     */
    public void startBattle(final BattleFloor Current)
    {
        setContentView(R.layout.activity_create_new_game);
        final Button MoveOn = (Button)findViewById(R.id.Victory);
        final Button GoBack = (Button)findViewById(R.id.GoBack);
        final Button flee = (Button) findViewById(R.id.Flee);
        final Button attack = (Button) findViewById(R.id.Attack);
        final TextView title = (TextView)findViewById(R.id.battleTitle);
        title.setText("Ambush!");

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

        if(Current.getEnemy(0).getHealth() <= 0 && Current.getEnemy(1).getHealth() <= 0 && Current.getEnemy(2).getHealth() <= 0 && Current.getEnemy(3).getHealth() <= 0){
            attack.setVisibility(View.INVISIBLE);
            flee.setVisibility(View.INVISIBLE);
            MoveOn.setVisibility(View.VISIBLE);
            GoBack.setVisibility(View.VISIBLE);
            title.setText("Victory!");
            MoveOn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SaveBattleFloor(Current);
                    moveFloor(true);
                }
            });
            GoBack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SaveBattleFloor(Current);
                    moveFloor(false);
                }
            });
        }

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
                    gameOver();
                }
                else if(Current.getEnemy(0).getHealth() <= 0 && Current.getEnemy(1).getHealth() <= 0 && Current.getEnemy(2).getHealth() <= 0 && Current.getEnemy(3).getHealth() <= 0){
                    for(int i = 0; i < Allies.length; i++){
                        if(Allies[i].getHealth() > 0)
                            currentScore++;
                    }
                    title.setText("Victory!");
                    attack.setVisibility(View.INVISIBLE);
                    flee.setVisibility(View.INVISIBLE);
                    MoveOn.setVisibility(View.VISIBLE);
                    GoBack.setVisibility(View.VISIBLE);
                    MoveOn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            SaveBattleFloor(Current);
                            moveFloor(true);
                        }
                    });
                    GoBack.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            SaveBattleFloor(Current);
                            moveFloor(false);
                        }
                    });
                }

            }
        });
        flee.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                moveFloor(false);
            }
        });
    }

    /**
     * Runs UI and some functionality of the shop floor
     * @param Current The shop floor being loaded either a new one or one passed from Floors List
     */
    public void openShop(final ShopFloor Current)
    {
        final ImageView[] Images = new ImageView[3];
        final Button[] Buttons = new Button[5];
        final TextView[] Text = new TextView[6];

        SaveShopFloor(Current);

        setContentView(R.layout.activity_shop);
        //Intent intent = new Intent(this, com.example.project3.shop.class);
        //startActivity(intent);

        //Non Item Related TextViews
        final TextView Gold = (TextView)findViewById(R.id.gold);
        final TextView BackBox = (TextView)findViewById(R.id.Transparency);
        BackBox.setText("");

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

        if(currentFloor == 0)
            GoBack.setVisibility(View.INVISIBLE);

        final Button Buy1 = (Button)findViewById(R.id.Buy1);
        final Button Buy2 = (Button)findViewById(R.id.Buy2);
        final Button Buy3 = (Button)findViewById(R.id.Buy3);

        Buttons[0] = Buy1;
        Buttons[1] = Buy2;
        Buttons[2] = Buy3;
        Buttons[3] = GoBack;
        Buttons[4] = MoveOn;

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
            if(Current.getItem(i).getCost() == -1){
                Buttons[i].setVisibility(View.INVISIBLE);
                Images[i].setVisibility(View.INVISIBLE);
                Text[i].setVisibility(View.INVISIBLE);
                Text[i+3].setVisibility(View.INVISIBLE);
            }
        }

        for(int i = 0; i < 3; i++){
            if(Current.getItem(i).getType() == 2){
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
            else if(Current.getItem(i).getType() == 1){
                Images[i].setImageResource(R.drawable.shield);
            }
            else if(Current.getItem(i).getType() == 0){
                Images[i].setImageResource(R.drawable.health);
            }
        }

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
        Buy1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(gold >= Current.getItem(0).getCost()) {
                    gold -= Current.getItem(0).getCost();
                    BuyItem(Buttons, Text, Images, 0, Current);
                }
            }
        });
        Buy2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(gold >= Current.getItem(1).getCost()) {
                    gold -= Current.getItem(1).getCost();
                    BuyItem(Buttons, Text, Images, 1, Current);
                }
            }
        });
        Buy3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(gold >= Current.getItem(2).getCost()) {
                    gold -= Current.getItem(2).getCost();
                    BuyItem(Buttons, Text, Images, 2, Current);
                }
            }
        });
    }

    /**
     * Manages UI changes for buying an item
     * @param Buttons Array of Buttons on the shop floor
     * @param Texts Array of TextViews on the shop floor
     * @param Images Array of Item images on the shop floor
     * @param index index of the item being bought
     * @param Current The shop floor being occupied
     */
    public void BuyItem(Button[] Buttons, TextView[] Texts, ImageView[] Images, final int index, final ShopFloor Current){
        final TextView back = (TextView)findViewById(R.id.Transparency);
        back.setText("Click the bot you wish to give the item to.");

        for(int i = 0; i < Buttons.length; i++){
            Buttons[i].setVisibility(View.INVISIBLE);
        }
        for(int i = 0; i < Texts.length; i++){
            Texts[i].setVisibility(View.INVISIBLE);
        }
        for(int i = 0; i < Images.length; i++){
            Images[i].setVisibility(View.INVISIBLE);
        }

        final ImageView char1 = (ImageView)findViewById(R.id.imageView);
        final ImageView char2 = (ImageView)findViewById(R.id.imageView2);
        final ImageView char3 = (ImageView)findViewById(R.id.imageView3);
        final ImageView char4 = (ImageView)findViewById(R.id.imageView4);

        char1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Current.buyItems(index, Allies[0]);
                Current.getItem(index).setCost(-1);
                SaveShopFloor(Current);
                openShop(Current);
            }
        });
        char2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Current.buyItems(index, Allies[1]);
                Current.getItem(index).setCost(-1);
                SaveShopFloor(Current);
                openShop(Current);
            }
        });
        char3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Current.buyItems(index, Allies[2]);
                Current.getItem(index).setCost(-1);
                SaveShopFloor(Current);
                openShop(Current);
            }
        });
        char4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Current.buyItems(index, Allies[3]);
                Current.getItem(index).setCost(-1);
                SaveShopFloor(Current);
                openShop(Current);
            }
        });
    }

    /**
     * Runs UI for the Event floor
     * @param event The Event floor being loaded either a new one or one passed from Floors List
     */
    public void newEvent(EventFloor event)
    {
        final EventFloor Current = event;
        setContentView(R.layout.activity_special_event);
        final Button goBack = (Button)findViewById(R.id.Choice1);
        final Button goForward = (Button)findViewById(R.id.Choice3);
        final Button mine = (Button)findViewById(R.id.Choice2);
        final TextView untilFlood = (TextView)findViewById(R.id.untilFlood);
        untilFlood.setText("Floors until flood: " + (arrayPos - floodHeight));
        final int[] floor = new int[13];
        floor[floor.length - 1] = 2;
        if(arrayPos == FloorList.size())
            FloorList.add(floor);

        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveFloor(false);
            }
        });
        goForward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveFloor(true);
            }
        });
        mine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                floodHeight++;
                if(floodHeight >= arrayPos)
                    gameOver();
                untilFlood.setText("Floors until flood: " + (arrayPos - floodHeight));
                gold += Current.mine();
            }
        });
    }

    /**
     * Runs UI for the rest floor. No variables needed to be saved
     * due to the nature of teh floor.
     */
    public void restFloor()
    {
        final RestFloor Current = new RestFloor(Allies);
        setContentView(R.layout.activity_rest);
        Button moveOn = (Button)findViewById(R.id.Continue);
        Button goBack = (Button)findViewById(R.id.Back);
        Button rest = (Button)findViewById(R.id.Rest);
        final int[] floor = new int[13];
        floor[floor.length - 1] = 2;
        if(arrayPos == FloorList.size())
            FloorList.add(floor);

        moveOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    moveFloor(true);
            }
        });
        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    moveFloor(false);
            }
        });
        rest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Current.rest();
            }
        });
    }
    /**
     * Called and moves to the game over screen, gives the user the option to restart
     */
    private void gameOver(){
        setContentView(R.layout.activity_game_over);
        final Button restart = (Button)findViewById(R.id.Restart);
        final TextView score = (TextView)findViewById(R.id.score);
        final TextView highscore1 = (TextView)findViewById(R.id.highScore1);
        final TextView highscore2 = (TextView)findViewById(R.id.highScore2);
        final TextView highscore3 = (TextView)findViewById(R.id.highScore3);
        final TextView highscore4 = (TextView)findViewById(R.id.highScore4);
        final TextView highscore5 = (TextView)findViewById(R.id.highScore5);

        TextView[] highScores = new TextView[5];
        highScores[0] = highscore1;
        highScores[1] = highscore2;
        highScores[2] = highscore3;
        highScores[3] = highscore4;
        highScores[4] = highscore5;

        if(currentScore > HighScore){
            saveHighScore();
        }

        mDatabase.orderByValue().limitToLast(5).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int i = 4;
                for (DataSnapshot childDataSnapshot : dataSnapshot.getChildren()) {
                    highScores[i].setText(childDataSnapshot.getKey() + ": " + childDataSnapshot.getValue());
                    i--;
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        score.setText("Your Score: " + currentScore);
        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }

    /**
     * Moves the player up or down floors
     * @param up true if moving upwards, false if going down
     */
    private void moveFloor(boolean up) {
        floodValue++;
        if(floodValue % 10 > 2){
            floodHeight++;
        }
        if(up){
            currentFloor++;
            arrayPos++;
            if(arrayPos >= FloorList.size()) {
                genFloor();
            }
            else{
                if(FloorList.get(arrayPos)[FloorList.get(arrayPos).length - 1] == 0)
                    startBattle(new BattleFloor(Allies, FloorList.get(arrayPos)));
                else if(FloorList.get(arrayPos)[FloorList.get(arrayPos).length - 1] == 1)
                    openShop(new ShopFloor(FloorList.get(arrayPos), gold));
                else if(FloorList.get(arrayPos)[FloorList.get(arrayPos).length - 1] == 2)
                    newEvent(new EventFloor());
            }
        }
        else {
            if (arrayPos != 0) {
                arrayPos--;
                if (arrayPos <= floodHeight || arrayPos == -1) {
                    gameOver();
                }
                else {
                    if (FloorList.get(arrayPos)[FloorList.get(arrayPos).length - 1] == 0)
                        startBattle(new BattleFloor(Allies, FloorList.get(arrayPos)));
                    else if (FloorList.get(arrayPos)[FloorList.get(arrayPos).length - 1] == 1)
                        openShop(new ShopFloor(FloorList.get(arrayPos), gold));
                    else if(FloorList.get(arrayPos)[FloorList.get(arrayPos).length - 1] == 2)
                        newEvent(new EventFloor());
                }
            }
        }
    }

    /**
     * Adds the floor to FloorList if it's a new floor or sets the location at FloorList to the correct variables
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

        if(arrayPos >= FloorList.size())
            FloorList.add(enemies);
        else
            FloorList.set(arrayPos, enemies);
    }

    /**
     * Adds the floor to FloorList if it's a new floor or sets the location at FloorList to the correct variables
     * @param shop The shop floor being saved
     */
    private void SaveShopFloor(ShopFloor shop){
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
    }
    /**
     * Generates the next floor.
     * Every 10 floors is a boss, every 6th is a rest (unless it's a boss), for every other floor 6/9 chance of battle, 2/9 chance of shop, 1/9 chance of event
     * Then adds floor data to database
     */
    private void genFloor(){
        int select = (int)(Math.random()*9);

        if(currentFloor % 6 == 0){
            restFloor();
        }
        else{
            if(select <= 5){
                startBattle(new BattleFloor(currentFloor, Allies));
            }
            else if(select <= 7){
                openShop(new ShopFloor(currentFloor, gold));
            }
            else{
                newEvent(new EventFloor());
            }
        }
    }
}



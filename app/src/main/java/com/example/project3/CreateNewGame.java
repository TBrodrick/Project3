package com.example.project3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project3.R;

public class CreateNewGame extends AppCompatActivity {

    int loop = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_game);
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


    public void startBattle(Button start, TextView intro, Button attack, Button flee, TextView PlayerNum)
    {
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
                Toast.makeText(getApplicationContext(), "You dealt 5 damage to the enemy!", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void openShop()
    {
        Intent intent = new Intent(this, com.example.project3.shop.class);
        startActivity(intent);
    }

    public void newEvent()
    {
        Intent intent = new Intent(this, com.example.project3.SpecialEvent.class);
        startActivity(intent);
    }


}



package com.example.project3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.project3.CreateNewGame;
import com.example.project3.R;

public class GameOver extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);
        final TextView gameOver = (TextView) findViewById(R.id.GameOver);
        final Button restart = (Button) findViewById(R.id.Restart);
        gameOver.animate().alpha(1).setDuration(5000);
        restart.animate().alpha(1).setDuration(5000);
        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startNewGame();
            }
        });
    }

    private void startNewGame()
    {
        Intent intent = new Intent(this, CreateNewGame.class);
        startActivity(intent);
    }

}

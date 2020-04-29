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
import android.widget.EditText;
import android.widget.Toast;

import com.example.project3.R;


public class MainActivity extends AppCompatActivity {

    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    public void init() {
        Button newRunButton = (Button) findViewById(R.id.newRun);
        Button cont = (Button)findViewById(R.id.continueRun);

        newRunButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startNewGame();
            }
        });
        cont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                continueGame();
            }
        });
    }

    public void continueGame(){
        setContentView(R.layout.login);

        final EditText usrName = (EditText)findViewById(R.id.usr);
        final EditText pw = (EditText)findViewById(R.id.pass);
        Button logIn = (Button)findViewById(R.id.logIn);

        final Intent intent = new Intent(this, CreateNewGame.class);
        intent.putExtra("Continue", true);

        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(usrName.getText() != null && pw.getText() != null){
                    intent.putExtra("UserName", usrName.getText().toString());
                    intent.putExtra("Password", pw.getText().toString());
                    startActivity(intent);
                }
                else{
                    Toast toast = Toast.makeText(getApplicationContext(), "Please Enter a username or password", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });
    }
    public void startNewGame() {
        Intent intent = new Intent(this, CreateNewGame.class);
        intent.putExtra("Continue", false);
        startActivity(intent);
    }
}